package yc.com.retrofit.http;


import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yc.com.retrofit.utils.json.MyDateDeserializer;

/**
 * 联网工具类
 * Created by ex-caoyanchang on 2017/12/28.
 */

public class RetrofitUtil {

    private static volatile Retrofit retrofit;
    private static volatile Retrofit fileRetrofit;


    public static ApiService createService() {
//        if (retrofit == null) {
//            synchronized (RetrofitUtil.class) {
//                if (retrofit == null) {
//                    retrofit = new Retrofit.Builder()
//                            .baseUrl("wwww.bai.du.com")
//                            .addConverterFactory(GsonConverterFactory.create(
//                                    new GsonBuilder().registerTypeAdapter(Date.class,
//                                            new MyDateDeserializer())
//                                            .setDateFormat(DateFormat.LONG)
//                                            .create()
//                            ))
//                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .client()
//                            .build();
//                }
//            }
//        }
        return retrofit.create(ApiService.class);
    }

    public static ApiService createFileService() {
//        if (fileRetrofit == null) {
//            synchronized (RetrofitUtil.class) {
//                if (fileRetrofit == null) {
//                    fileRetrofit = new Retrofit.Builder()
//                            .baseUrl("www.baidu.com")
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .client()
//                            .build();
//                }
//            }
//        }
        return fileRetrofit.create(ApiService.class);
    }

}
