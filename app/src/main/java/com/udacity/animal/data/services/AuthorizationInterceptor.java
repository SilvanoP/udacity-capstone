package com.udacity.animal.data.services;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private String accessToken;

    public AuthorizationInterceptor() { }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader("Accept", "application/vnd.api+json");
        builder.addHeader("Content-Type", "application/vnd.api+json");

        if (accessToken != null && !accessToken.equals("")) {
            String auth = "Bearer " + accessToken;
            builder.addHeader("Authorization", auth);
        }

        return chain.proceed(builder.build());
    }
}
