package com.bingyan.pixcelcity.camerahelper.camera;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.bingyan.pixcelcity.camerahelper.renderers.NormalTextureRenderer;
import com.bingyan.pixcelcity.camerahelper.utils.TextureUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraHelper implements TextureView.SurfaceTextureListener{

    private static final String TAG = "CameraPick";

    private static final SparseIntArray ORIENTATION = new SparseIntArray();

    static {
        ORIENTATION.append(Surface.ROTATION_0, 90);
        ORIENTATION.append(Surface.ROTATION_90, 0);
        ORIENTATION.append(Surface.ROTATION_180, 270);
        ORIENTATION.append(Surface.ROTATION_270, 180);
    }

    //初始化需要的TextureView
    private TextureView mTextureView;

    private TextureEGLHelper mTextureEglHelper;

    private Class mRendererClz;

    //相机Id
    private String mCameraId;

    //预览Size
    private Size mPreviewSize;
    private Size mCaptureSize;

    //相机线程
    private HandlerThread mCameraThread;
    //相机Handler
    private Handler mCameraHandler;
    //相机设备
    private CameraDevice mCameraDevice;
    //图像读取
    private ImageReader mImageReader;
    //拍照工具
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mCameraCaptureSession;

    //上下文环境
    private Context mContext;

    private WindowManager mWindowManager;

    public CameraHelper(Context mContext) {
        this.mContext = mContext;
        //获取windowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Service.WINDOW_SERVICE);
    }

    public CameraHelper(Context mContext,TextureView textureView) {
        this(mContext,null,textureView);
    }

    public CameraHelper(Context mContext,Class renderClz,TextureView textureView) {
        this.mContext = mContext;
        //获取windowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Service.WINDOW_SERVICE);
        setRenderer(renderClz == null ? NormalTextureRenderer.class : renderClz);
        bindTextureView(textureView);
    }

    public void bindTextureView(TextureView textureView){
        this.mTextureView = textureView;
        mTextureEglHelper = new TextureEGLHelper(mContext);
        mTextureEglHelper.setRenderer(mRendererClz);
        mTextureView.setSurfaceTextureListener(this);
    }


    public void setRenderer(Class clz){
        this.mRendererClz = clz;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mTextureEglHelper.onSurfaceWidthAndHeightGet(width,height);
        //加载OES纹理ID
        final int textureId = TextureUtils.loadOESTexture();
        //初始化操作
        mTextureEglHelper.initEgl(mTextureView,textureId);
        //setupCamera
        setupCamera(width,height,CameraCharacteristics.LENS_FACING_BACK);
        openCamera();

    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        mTextureEglHelper.onSurfaceChanged(width, height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCameraCaptureSession != null) {
            mCameraCaptureSession.close();
            mCameraCaptureSession = null;
        }
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
        if (mImageReader != null) {
            mImageReader.close();
            mImageReader = null;
        }
        if (mCameraHandler == null || mCameraThread == null) return true;
        mCameraHandler.removeCallbacksAndMessages(null);
        mCameraThread.getLooper().quit();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    public void onDestroy(){
        if (mTextureEglHelper != null) {
            mTextureEglHelper.onDestroy();
        }
    }

    /**
     * 建立Camera
     */
    private void setupCamera(int width, int height, int lensFacing) {
        //获取摄像头的挂你留着CameraManager
        CameraManager manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                //跳过不是想要的lensFacing
                if (facing != null && facing == (lensFacing == CameraCharacteristics.LENS_FACING_BACK ? CameraCharacteristics.LENS_FACING_FRONT : CameraCharacteristics.LENS_FACING_BACK)) {
                    continue;
                }
                //获取StreamConfigurationMap，它管理摄像头支持的所有输出格式和尺寸
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                //根据TextureView的尺寸设置预览尺寸
                mPreviewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class), width, height);
                //获取相机支持的最大拍照尺寸
                mCaptureSize = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new Comparator<Size>() {
                    @Override
                    public int compare(Size lhs, Size rhs) {
                        return Long.signum(lhs.getWidth() * lhs.getHeight() - rhs.getHeight() * rhs.getWidth());
                    }
                });
                //此ImageReader用于拍照
                setupImageReader();
                mCameraId = cameraId;
                break;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开摄像机
     */
    private void openCamera() {
        CameraManager manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        try {

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            manager.openCamera(mCameraId, mStateCallback, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 开启预览
     */
    private void startPreview(){
        //获得SurfaceTexture
        SurfaceTexture surfaceTexture = mTextureEglHelper.loadOESTexture();
        surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(),mPreviewSize.getHeight());
        Surface previewSurface = new Surface(surfaceTexture);
        try {
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.addTarget(previewSurface);
            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface,mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    try {
                        mCaptureRequest = mCaptureRequestBuilder.build();
                        mCameraCaptureSession = session;
                        mCameraCaptureSession.setRepeatingRequest(mCaptureRequest, null, mCameraHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            },mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setupImageReader() {
        //2代表ImageReader中最多可以获取两帧图像流.数字越小越好
        //This should be as small as possible to limit memory use
        mImageReader = ImageReader.newInstance(mCaptureSize.getWidth(), mCaptureSize.getHeight(),
                ImageFormat.JPEG, 2);

        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {

            @Override
            public void onImageAvailable(ImageReader reader) {

                Log.d("TAG223","onImageAvailable");


//                //获取最新的一阵Image
//                Image image = reader.acquireLatestImage();
//                //因为是ImageFormat.JPEG格式，所以 image.getPlanes()返回的数组只有一个，也就是第0个
//                ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
//                byte[] bytes = new byte[byteBuffer.remaining()];
//                byteBuffer.get(bytes);
//                //ImageFormat.JPEG格式直接转化为Bitmap格式
//                Bitmap temp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                //因为摄像机数据默认是横着的，所以需要旋转90度
//                //Bitmap newBitmap = BitmapUtil.rotateBitmap(temp,90);
//                //抛出去展示或者储存图片数据
//                mOnGetBitmapInterface.getABitmap(temp);
//                //一定要close，否则不会受到新的image回调
//                image.close();

//                Image image = reader.acquireLatestImage();
//                if (image == null) {
//                    return;
//                }
//                int width = image.getWidth(), height = image.getHeight();
//                byte[] i420bytes = CameraUtil.getDataFromImage(image, COLOR_FormatI420);
////                BitmapUtil.dumpFile("mnt/sdcard/1.yuv", i420bytes);
//                byte[] i420RorateBytes = BitmapUtil.rotateYUV420Degree90(i420bytes, width, height);
//                byte[] nv21bytes = BitmapUtil.I420Tonv21(i420RorateBytes, height, width);
//                Bitmap bitmap = BitmapUtil.getBitmapImageFromYUV(nv21bytes, height, width);
//                if (mOnGetBitmapInterface != null) {
//                    mOnGetBitmapInterface.getABitmap(bitmap);
//                }
//                image.close();


            }
        }, mCameraHandler);
    }





    //选择sizeMap中大于并且最接近width和height的size
    private Size getOptimalSize(Size[] sizeMap, int width, int height) {
        List<Size> sizeList = new ArrayList<>();
        for (Size option : sizeMap) {
            if (width > height) {
                if (option.getWidth() > width && option.getHeight() > height) {
                    sizeList.add(option);
                }
            } else {
                if (option.getWidth() > height && option.getHeight() > width) {
                    sizeList.add(option);
                }
            }
        }
        if (sizeList.size() > 0) {
            return Collections.min(sizeList, new Comparator<Size>() {
                @Override
                public int compare(Size lhs, Size rhs) {
                    return Long.signum(lhs.getWidth() * lhs.getHeight() - rhs.getWidth() * rhs.getHeight());
                }
            });
        }
        return sizeMap[0];
    }


    private void lockFocus() {
        try {
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
            mCameraCaptureSession.capture(mCaptureRequestBuilder.build(), mCaptureCallback, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void unLockFocus() {
        try {
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
            //mCameraCaptureSession.capture(mCaptureRequestBuilder.build(), null, mCameraHandler);
            mCameraCaptureSession.setRepeatingRequest(mCaptureRequest, null, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void capture() {
        try {
            final CaptureRequest.Builder mCaptureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            int rotation = mWindowManager.getDefaultDisplay().getRotation();
            mCaptureBuilder.addTarget(mImageReader.getSurface());
            mCaptureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION.get(rotation));
            CameraCaptureSession.CaptureCallback CaptureCallback = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    unLockFocus();
                }
            };
            mCameraCaptureSession.stopRepeating();
            mCameraCaptureSession.capture(mCaptureBuilder.build(), CaptureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }



    private void startCameraThread() {
        mCameraThread = new HandlerThread("CameraThread");
        mCameraThread.start();
        mCameraHandler = new Handler(mCameraThread.getLooper());
    }

    //------------------------- Camera interface --------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            startPreview();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            camera.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            camera.close();
            mCameraDevice = null;
        }
    };


    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
        }
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            capture();
        }
    };


}
