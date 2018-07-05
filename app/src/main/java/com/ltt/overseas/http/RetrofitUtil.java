package com.ltt.overseas.http;

import com.ltt.overseas.XApplication;
import com.ltt.overseas.base.Constants;
import com.ltt.overseas.http.convert.FastJsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/5/20.
 */
public class RetrofitUtil {
    private static APIService mAPIService;

    public static APIService getAPIService() {
        if (mAPIService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(RetrofitUtil.genericClient())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mAPIService = retrofit.create(APIService.class);
        }
        return mAPIService;
    }

    public static OkHttpClient genericClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(mTokenInterceptor)
                .build();
        return httpClient;
    }

    static Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request authorised = originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", getAuthorzation())
                    .build();
            return chain.proceed(authorised);
        }
    };

    private static String getAuthorzation(){
        if (XApplication.globalUserBean == null || XApplication.globalUserBean.getAccess_token() == null){
            return "";
        }else{
           // return "Bearer eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJjb25zdW1lcktleSI6Im1CMHlPajVZZW9nZ3hXTGtEdGtJbnl1YTE4d3N2ZmdnIiwidXNlcl9pZCI6IjE1MyIsImlzc3VlZEF0IjoiMjAxOC0wNS0wN1QxNTowMTo0OCswODAwIiwidHRsIjoyNjc4NDAwfQ.s6wW_qSdLrMqX7_oYXVJ4OMZvV1qaiVFzJU8G_5MG74";
            return "Bearer " + XApplication.globalUserBean.getAccess_token();

        }
    }

}
