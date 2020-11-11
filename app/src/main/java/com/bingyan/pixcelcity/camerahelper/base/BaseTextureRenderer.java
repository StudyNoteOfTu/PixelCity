package com.bingyan.pixcelcity.camerahelper.base;

import android.content.Context;
import android.graphics.SurfaceTexture;

public abstract class BaseTextureRenderer {

    private Context mContext;
    private Integer mOESTextureId;

    public BaseTextureRenderer(Context context, Integer OESTextureId){
        this.mContext = context;
        this.mOESTextureId = OESTextureId;
    }

    protected Context getContext(){
        return mContext;
    }

    protected Integer getOESTextureId(){
        return mOESTextureId;
    }

    /**
     * Surface创建时候
     */
    abstract public void onSurfaceCreated();

    /**
     * 横竖屏切换时候
     */
    abstract public void onSurfaceChanged(int width, int height);

    /**
     * 绘制帧
     * 若要切换渲染器Program，可在此方法内切换
     * To Use New GLES30.glUseProgram()
     */
    abstract public void onDrawFrame(SurfaceTexture surfaceTexture);

}
