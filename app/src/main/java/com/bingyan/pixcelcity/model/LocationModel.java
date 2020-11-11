package com.bingyan.pixcelcity.model;

import com.bingyan.pixcelcity.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class LocationModel {

    //搜索
    public void search(HttpUtils.HttpCallback callback, String key, String token) {
        String url = HttpUtils.baseUrl + "/post?key=" + key;
        HttpUtils.auth_put(callback, url, new HashMap<String, Object>(), token);
    }

    //搜索列表
    //type 1:默认搜索 2：城市搜索 3：地点搜索
    public void searchList(HttpUtils.HttpCallback callback, int type, String city, String location,String token) {
        String url;
        switch (type) {//默认搜索
            case 2:
                //城市搜索
                url = HttpUtils.baseUrl + "/post?type=2&city=" + city;
                break;
            case 3:
                //地点搜索
                url = HttpUtils.baseUrl + "/post?type=3&location=" + location;
                break;
            default:
                url = HttpUtils.baseUrl + "/post?type=1";
                break;
        }

        HttpUtils.auth_get(callback,url,token);
    }

    //发布地点
    public void postLocation(HttpUtils.HttpCallback callback,String city,String country,double latitude,double longitude,
                             String location_name,String location_picture,String text,String feature_info,String token){

        String url = HttpUtils.baseUrl+"/post";
        Map<String,Object> map = new HashMap<>();
        map.put("city",city);
        map.put("country",country);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        map.put("location_name",location_name);
        map.put("location_picture",location_picture);
        map.put("text",text);
        map.put("feature_info",feature_info);
        HttpUtils.auth_post(callback,url,map,token);
    }

    //想去地点
    public void putWant(HttpUtils.HttpCallback callback,String userId,String token){
        String url = HttpUtils.baseUrl +"/post/"+userId;
        HttpUtils.auth_put(callback,url,new HashMap<String, Object>(),token);
    }

    //探索成功
    public void exploreSuccess(HttpUtils.HttpCallback callback,String locationId,String token){
        String url = HttpUtils.baseUrl+"/post/"+locationId;
        HttpUtils.auth_post(callback,url,new HashMap<String, Object>(),token);
    }

    //探索排名
    public void exploreRank(HttpUtils.HttpCallback callback,String locationId,String token){
        String url = HttpUtils.baseUrl+"/post/"+locationId;
        HttpUtils.auth_get(callback,url,token);
    }

    //探索状态
    public void exploreStatus(HttpUtils.HttpCallback callback,String locationId,String token){
        String url = HttpUtils.baseUrl+"/post/"+locationId+"/status";
        HttpUtils.auth_get(callback,url,token);
    }




}
