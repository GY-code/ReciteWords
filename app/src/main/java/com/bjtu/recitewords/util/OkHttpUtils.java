package com.usts.englishlearning.util;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {
    //提供一个静态方法，当别的地方需要发起网络请求时，简单的调用这个方法即可
    //请求实例
    //OKHttp请求
    //callback是okhttp自带的回调接口，这里写的是使用GET方式获取服务器数据
    private static int TimeOut = 120;
    //单例获取ohttp3对象
    private static OkHttpClient client = null;

    private static synchronized OkHttpClient getInstance() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .readTimeout(TimeOut, TimeUnit.SECONDS)
                    .connectTimeout(TimeOut*10, TimeUnit.SECONDS)
                    .writeTimeout(TimeOut*10, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    public static void sendOkHttpRequest(final String address, final Callback callback){
        client=getInstance();
        Request request=new Request.Builder()
                .url(address)
                .build();
        //enqueue方法内部已经帮助我们开启好了线程，最终的结果会回调到callback中
        client.newCall(request).enqueue(callback);
    }

    //使用POST方式向服务器提交数据并获取返回提示数据
    public static void sendOkHttpResponse(final String address,
                                          final RequestBody requestBody, final Callback callback){
        client=getInstance();
        //JSONObject这里是要提交的数据部分
        Request request=new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

}

