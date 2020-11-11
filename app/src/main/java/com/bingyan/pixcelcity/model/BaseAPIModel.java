package com.bingyan.pixcelcity.model;

import com.bingyan.pixcelcity.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseAPIModel {

    //获取验证码
    public static void getVerifyCode(HttpUtils.HttpCallback callback,String telephone){
        String url = HttpUtils.baseUrl+"/code/"+telephone;
        HttpUtils.get(callback,url);
    }

    //用户注册
    public void registerNewUser(HttpUtils.HttpCallback callback,String telephone,String username,String avatar,String city){
        String url = HttpUtils.baseUrl+"/register";
        Map<String,Object> postMap = new HashMap<>();
        postMap.put("telephone",telephone);
        postMap.put("username",username);
        postMap.put("avatar",avatar);
        postMap.put("city",city);
        HttpUtils.post(callback,url,postMap);
    }

    //用户登录
    public static void loginUser(HttpUtils.HttpCallback callback,String telephone,String verify_code){
        String url = HttpUtils.baseUrl+"/login";
        Map<String,Object> map = new HashMap<>();
        map.put("telephone",telephone);
        map.put("verify_code",verify_code);
        HttpUtils.post(callback,url,map);
    }

}
