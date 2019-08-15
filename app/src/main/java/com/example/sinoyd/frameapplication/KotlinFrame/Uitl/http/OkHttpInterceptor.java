package com.example.sinoyd.frameapplication.KotlinFrame.Uitl.http;

import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;
import com.sinoyd.Code.Until.ShowLog4okhttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by kaka on 2018/3/15.
 * email:375120706@qq.com
 */

public class OkHttpInterceptor implements Interceptor {
    private static final String TAG = "scj";
    //设缓存有效期为1天
    private static final long CACHE_CONTROL_CACHE = 60 * 60 * 24 * 1;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //从chain对象中可以获取到request和response，想要的数据都可以从这里获取
        Request request = chain.request();
        if (!NetworkUtils.isConnected()) {//如果不能上网，那么启用缓存
            request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        //增加头部信息
//        request=addHeaders(request);
        //打印request日志
        logForRequest(request);
        //获取response对象
        Response response = chain.proceed(request);
        //打印response日志
        logForResponse(response);
        if (NetworkUtils.isConnected()) {
            //有网的时候读接口上的@Headers里的配置，可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_CONTROL_CACHE)
                    .removeHeader("Pragma")
                    .build();
        }

    }

    private Request addHeaders(Request request) {
        Map<String, String> params = new HashMap<>();
        params.put("sign", "12345");
        params.put("time", System.currentTimeMillis() + "");
        Headers headers = Headers.of(params);
        return request.newBuilder().headers(headers).build();
    }


    private void logForRequest(Request request) {
        Log.i(TAG, "request's log----------------------------------start");
        Log.i(TAG, "url: " + request.url());
        Log.i(TAG, "method: " + request.method());
        Headers headers = request.headers();
        if (headers != null && headers.size() != 0) {
            Log.i(TAG, "headers: " + headers.toString());
        }
        RequestBody body = request.body();
        if (body != null) {
            Log.i(TAG, body.toString());
        }
        Log.i(TAG, "request's log----------------------------------end");
    }

    private void logForResponse(Response response) {
        Log.i(TAG, "response's log---------------------------------start");
        Log.i(TAG, "code: " + response.code());
        Log.i(TAG, "protocol: " + response.protocol());
        Headers headers = response.headers();
        if (headers != null && headers.size() != 0) {
            Log.i(TAG, headers.toString());
        }
        try {
            //这里不能直接用response.body().string(),因为调用改方法后流就关闭，程序就可能会发生异常
            //我们需要创建出一个新的ResponseBody给应用层调用
            ResponseBody body = response.peekBody(1024 * 1024);
            if (body != null) {
//                Log.i(TAG, "protocol: " + body.string());
                ShowLog4okhttp sl = new ShowLog4okhttp(TAG);
                sl.printJson(body.string(), "返回结果：");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "response's log---------------------------------end");
    }


}
