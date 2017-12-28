package xianglin.com.retrofit.http;

import okhttp3.OkHttpClient;
import xianglin.com.retrofit.BuildConfig;
import xianglin.com.retrofit.http.interceptor.HttpLoggingInterceptor;

/**
 * Okhttp工具类
 * Created by ex-caoyanchang on 2017/12/28.
 */

public class OkhttpUtil {
    private static OkHttpClient client;
    private static OkHttpClient fileClient;

//    //默认
//    private static OkHttpClient defaultOkHttpClient() {
//        if (client != null) {
//            return client;
//        }
//        //日志拦截
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        if (BuildConfig.DEBUG) {
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }
//        //处理https
//
//
//    }


    //文件


}
