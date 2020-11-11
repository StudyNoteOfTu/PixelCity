package com.bingyan.pixcelcity.model;

import com.bingyan.pixcelcity.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class UserModel {


    //我的资料
    public void getMyProfile(HttpUtils.HttpCallback callback,String token){
        String url = HttpUtils.baseUrl+"/me/profile";
        HttpUtils.auth_get(callback,url,token);
    }

    //更新我的资料
    public void updateMyProfile(HttpUtils.HttpCallback callback, String telephone,String username,String avatar, String city,String token){
        String url = HttpUtils.baseUrl+"/me/profile";
        Map<String,Object> map = new HashMap<>();
        map.put("telephone",telephone);
        map.put("username",username);
        map.put("avatar",avatar);
        map.put("city",city);
        HttpUtils.auth_put(callback,url,map,token);
    }

    //我的统计
    public void getMyStatic(HttpUtils.HttpCallback callback,String token){
        String url = HttpUtils.baseUrl+"/me/statistic";
        HttpUtils.auth_get(callback,url,token);
    }

    //我的设置
    public void getMyOption(HttpUtils.HttpCallback callback,String token){
        String url = HttpUtils.baseUrl+"/me/option";
        HttpUtils.auth_get(callback,url,token);
    }

    //更新设置
    public void updateMyOption(HttpUtils.HttpCallback callback,int option_type,boolean badge_get,boolean badge_progress,boolean be_wanted,
            boolean be_explored,boolean explore_more,boolean post_visible,boolean explore_visible,boolean want_visible,String token){
        String url = HttpUtils.baseUrl+"/me/option";

        Map<String,Object> map = new HashMap<>();
        map.put("option_type",option_type);
        map.put("badge_get",badge_get);
        map.put("badge_progress",badge_progress);
        map.put("be_wanted",be_wanted);
        map.put("be_explored",be_explored);
        map.put("explore_more",explore_more);
        map.put("post_visible",post_visible);
        map.put("explore_visible",explore_visible);
        map.put("want_visible",want_visible);
        HttpUtils.auth_put(callback,url,map,token);
    }


    //获取徽章
    public void getMyBadges(HttpUtils.HttpCallback callback,String token){
        String url = HttpUtils.baseUrl +"/me/badges";
        HttpUtils.auth_get(callback,url,token);
    }






}
