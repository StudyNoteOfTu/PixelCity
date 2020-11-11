package com.bingyan.pixcelcity.model;

import com.bingyan.pixcelcity.utils.HttpUtils;

public class OtherUserModel {

    //用户资料
    public void getUserProfile(HttpUtils.HttpCallback callback,String userId,String token){
        String url = HttpUtils.baseUrl+"/user/"+userId+"/profile";
        HttpUtils.auth_get(callback,url,token);
    }

    //用户发布
    public void getUserPosts(HttpUtils.HttpCallback callback,String userId,String token){
        String url = HttpUtils.baseUrl +"/user/"+userId+"/posts";
        HttpUtils.auth_get(callback,url,token);
    }

    //用户探索
    public void getUserExplores(HttpUtils.HttpCallback callback,String userId,String token){
        String url = HttpUtils.baseUrl+"/user/"+userId+"/explores";
        HttpUtils.auth_get(callback,url,token);
    }

    //用户想去
    public void getUserWants(HttpUtils.HttpCallback callback,String userId,String token){
        String url = HttpUtils.baseUrl+"/user/"+userId+"/wants";
        HttpUtils.auth_get(callback,url,token);
    }

}
