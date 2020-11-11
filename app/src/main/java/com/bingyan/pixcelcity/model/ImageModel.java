package com.bingyan.pixcelcity.model;

import com.bingyan.pixcelcity.utils.HttpUtils;

import java.util.HashMap;

public class ImageModel {

    //上传图片
    public void postIamage(HttpUtils.HttpCallback callback,String filename,String filePath){
        String url = HttpUtils.baseUrl+"/picture";
        HttpUtils.postFile(callback,url,filename,filePath,new HashMap<String, String>());
    }
}
