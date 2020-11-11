package com.bingyan.pixcelcity.camerahelper.renderers;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.Matrix;
import android.util.Log;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.camerahelper.base.BaseTextureRenderer;
import com.bingyan.pixcelcity.camerahelper.utils.PixcelDataUtils;
import com.bingyan.pixcelcity.camerahelper.utils.ResReadUtils;
import com.bingyan.pixcelcity.camerahelper.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class NormalTextureRenderer extends BaseTextureRenderer {

    private static final String TAG = "CameraTextureRenderer";
    private FloatBuffer mVertexBuffer;



    /**
     * 程序
     */
    private int mShaderProgram = -1;

    private int aPositionLocation = -1;
    private int aTextureCoordLocation = -1;
    private int uTextureMatrixLocation = -1;
    private int uTextureSamplerLocation = -1;

    private int mHeightLocation = -1;
    private int mWidthLocation = -1;

    private int mMosSquareLocation = -1;


    public static final String POSITION_ATTRIBUTE = "aPosition";
    public static final String TEXTURE_COORD_ATTRIBUTE = "aTextureCoord";
    public static final String TEXTURE_MATRIX_UNIFORM = "uTextureMatrix";
    public static final String TEXTURE_SAMPLER_UNIFORM = "uTextureSampler";
    private static final int POSITION_SIZE = 2;

    private static final int TEXTURE_SIZE = 2;

    private static final int STRIDE = (POSITION_SIZE + TEXTURE_SIZE) * 4;

    /**
     * 前两个为顶点坐标
     * 后两个为纹理坐标
     * 只有三角形 正方形要两个三角形画
     */
    private static final float[] VERTEX_DATA = {
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 0.0f, 1.0f,
            -1.0f, -1f, 0.0f, 0.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 0f, 0.0f,
            1.0f, -1.0f, 1.0f, 0.0f
    };

    /**
     * 变换矩阵
     */
    private float[] transformMatrix = new float[16];

    public NormalTextureRenderer(Context context, Integer OESTextureId) {
        super(context,OESTextureId);
        this.mVertexBuffer = loadVertexBuffer(VERTEX_DATA);
    }

    public FloatBuffer loadVertexBuffer(float[] vertexData) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(vertexData.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        buffer.put(vertexData, 0, vertexData.length).position(0);
        return buffer;
    }

    @Override
    public void onSurfaceCreated() {
        int vertexShader = ShaderUtils.compileVertexShader(ResReadUtils.readRawTextFile(getContext(), R.raw.vertex_texture_shader));
        int fragmentShader = ShaderUtils.compileFragmentShader(ResReadUtils.readRawTextFile(getContext(),R.raw.fragment_mosaic_texture_shader));
        mShaderProgram = ShaderUtils.linkProgram(vertexShader, fragmentShader);
        //开始使用程序
        GLES30.glUseProgram(mShaderProgram);


        aPositionLocation = GLES30.glGetAttribLocation(mShaderProgram, NormalTextureRenderer.POSITION_ATTRIBUTE);
        aTextureCoordLocation = GLES30.glGetAttribLocation(mShaderProgram, NormalTextureRenderer.TEXTURE_COORD_ATTRIBUTE);
        uTextureMatrixLocation = GLES30.glGetUniformLocation(mShaderProgram, NormalTextureRenderer.TEXTURE_MATRIX_UNIFORM);
        uTextureSamplerLocation = GLES30.glGetUniformLocation(mShaderProgram, NormalTextureRenderer.TEXTURE_SAMPLER_UNIFORM);


        mHeightLocation = GLES30.glGetUniformLocation(mShaderProgram,"u_tex_height");
        mWidthLocation = GLES30.glGetUniformLocation(mShaderProgram,"u_tex_width");
        mMosSquareLocation = GLES30.glGetUniformLocation(mShaderProgram,"u_mos_square");
    }


    int width ;
    int height ;
    public void onSurfaceWidthAndHeightGet(int width, int height) {
        this.width= width;
        this.height = height;
        Log.d("2323TAG","width = "+width+" height = "+height);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES30.glViewport(0, 0, width, height);


        final float aspectRatio = width > height ?
                (float) width / (float) height :
                (float) height / (float) width;
        if (width > height) {
            //横屏
            Matrix.orthoM(transformMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        } else {
            //竖屏
            Matrix.orthoM(transformMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    @Override
    public void onDrawFrame(SurfaceTexture surfaceTexture) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        surfaceTexture.updateTexImage();

        //获得纹理矩阵
        surfaceTexture.getTransformMatrix(transformMatrix);

        //绑定Texture
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, getOESTextureId());

        GLES30.glUniform1i(uTextureSamplerLocation, 0);
        GLES30.glUniformMatrix4fv(uTextureMatrixLocation, 1, false, transformMatrix, 0);


        float f_height = (float)height;
        float f_width = (float)width;
        float f_square = (float)(width * 0.008);

        Log.d("2323TAG","height = "+f_height+" fwidth = "+f_width);
        GLES30.glUniform1f(mHeightLocation,f_height);
        GLES30.glUniform1f(mWidthLocation,f_width);
        GLES30.glUniform1f(mMosSquareLocation,f_square);



        mVertexBuffer.position(0);
        GLES30.glEnableVertexAttribArray(aPositionLocation);
        GLES30.glVertexAttribPointer(aPositionLocation, 2, GLES30.GL_FLOAT, false, STRIDE, mVertexBuffer);

        mVertexBuffer.position(2);
        GLES30.glEnableVertexAttribArray(aTextureCoordLocation);
        GLES30.glVertexAttribPointer(aTextureCoordLocation, 2, GLES30.GL_FLOAT, false, STRIDE, mVertexBuffer);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6);

        ifNeedCapture(width,height);
    }


    public void sendNeed(){
        need = true;
    }
    private boolean need = false;
    private void ifNeedCapture(final int width,final int height) {
        if (need) {

            //高耗时操作

            ByteBuffer buf = ByteBuffer.allocateDirect(width * height * 4).order(ByteOrder.LITTLE_ENDIAN);
            //用的是当前Program的上下文
            GLES30.glReadPixels(0, 0, width, height, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, buf);
            final int[] data = new int[width * height];
            buf.asIntBuffer().get(data);
            need = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PixcelDataUtils.calculateFingerprint(data,width,height);
                }
            }).start();
        }
    }
}
