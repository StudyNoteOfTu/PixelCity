package com.bingyan.pixcelcity.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpUtils {

    public static String baseUrl = "https://pixel-city.dev.hust.online/api/v1";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //------------------------------ BASE --------------------------------------
    public static void get(HttpCallback callback,String url){
        callback.onSending();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    public static void post(HttpCallback callback,String url,Map<String,Object> postMap){
        callback.onSending();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, map2json(postMap));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    public static void put(HttpCallback callback,String url,Map<String,Object> putMap){
        callback.onSending();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, map2json(putMap));
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    public static void postFile(HttpCallback callback, String url, String fileName, String filePath, Map<String,String> formMap){
        callback.onSending();
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder mBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File file = new File(filePath);
        if (!file.exists()){
            callback.onError("file doesn't exist !");
            return;
        }

        String fileMimeType = "image/jpeg";

        MediaType mediaType = MediaType.parse(fileMimeType);
        RequestBody fileBody = RequestBody.create(mediaType,file);
        mBody.addFormDataPart("file",fileName,fileBody);

        //添加其他信息
        for (Map.Entry<String,String> info : formMap.entrySet()){
            mBody.addFormDataPart(String.valueOf(info.getKey()),String.valueOf(info.getValue()));
        }

        RequestBody requestBody = mBody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    //------------------------------ AUTH --------------------------------------
    public static void auth_get(HttpCallback callback,String url,String token){
        callback.onSending();
        final String basic = bearer(token);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",basic)
                .get()
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    public static void auth_post(HttpCallback callback,String url,Map<String,Object> postMap,String token){
        callback.onSending();
        final String basic = bearer(token);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, map2json(postMap));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",basic)
                .post(body)
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    public static void auth_put(HttpCallback callback,String url,Map<String,Object> putMap,String token){
        callback.onSending();
        final String basic = bearer(token);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, map2json(putMap));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",basic)
                .put(body)
                .build();
        enqueueCall(callback,okHttpClient,request);
    }

    //------------------------------ TOOL --------------------------------------
    public static String map2json(Map<String,Object> map){
        String json;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String,Object> info : map.entrySet()){
            //String fold_name, int pic_num, boolean hasRead, String title, String description, String file_path, String time
//            mBody.addFormDataPart(String.valueOf(info.getKey()),String.valueOf(info.getValue()));
            if (info.getValue() instanceof String){
                sb.append("\"").append(info.getKey()).append("\":").append("\"").append(info.getValue()).append("\"");
            }else{
                sb.append("\"").append(info.getKey()).append("\":").append(info.getValue());
            }
            sb.append(",");
        }
        json = sb.substring(0,sb.length()-1);
        return json+"}";
    }




    //抽取相同代码
    private static void enqueueCall(final HttpCallback callback, OkHttpClient okHttpClient, Request request){
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String error = e.getMessage().toString();
                callback.onError("error---"+error);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rtn = response.body().string();
                callback.getResult(rtn);
            }
        });
    }

    /**
     * 异步
     */
    public interface HttpCallback {

        /**
         * 正在发送请求
         */
        void onSending();

        /**
         * 获得结果
         * @param result 结果
         */
        void getResult(String result);

        /**
         * 请求错误
         */
        void onError(String error);

    }

    public static String bearer(String token) {
        return "Bearer " + token;
    }
}