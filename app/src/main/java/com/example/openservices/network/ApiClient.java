package com.example.openservices.network;

import com.example.openservices.utilities.ConstantValue;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static HttpLoggingInterceptor logger;
    private static HttpLoggingInterceptor loggerSearch;
    private static HeaderInterceptor headerInterceptor;
    private static OkHttpClient okHttpClient;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            loggerSearch = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);
            HeaderInterceptor headerInterceptor = new HeaderInterceptor();

            okHttpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(logger)
                    .addInterceptor(loggerSearch)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstantValue.API_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
