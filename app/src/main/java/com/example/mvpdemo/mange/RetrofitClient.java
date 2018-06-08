package com.example.mvpdemo.mange;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 对ApiService进行封装
 */
public class RetrofitClient {

    private static RetrofitClient instance;
    private OkHttpClient client;
    private Context context;
    private Retrofit retrofit;
    private ApiService apiService;

    private RetrofitClient(Context context){
        this.context = context;
    }

    /**
     * 初始化
     * @param context
     * @return
     */
    public static RetrofitClient getInstance(Context context){
        if (instance == null){
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    /**
     * 初始化OkHttpClient
     * @return
     */
    private OkHttpClient provideOkHttpClient(){
        if (client == null){
            client = new OkHttpClient.Builder()
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    /**
     * 初始化Retrofit
     * @return
     */
    private Retrofit provideRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    //.baseUrl("https://api.douban.com/v2/")
                    .baseUrl("https://aip.baidubce.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    public ApiService provideApiService(){
        if (apiService == null){
            apiService = provideRetrofit().create(ApiService.class);
        }
        return apiService;
    }

}
