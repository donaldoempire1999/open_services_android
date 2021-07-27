package com.example.openservices.network;

import com.example.openservices.utilities.ConstantValue;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

public class HeaderInterceptor implements Interceptor {

    @Override
    public @NotNull Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Header header = new Header("collection", ConstantValue.getTOKEN());
        request = request.newBuilder()
                .addHeader("Authorization", "Bearer " +ConstantValue.getTOKEN())
                .build();
        return chain.proceed(request);
    }
}
