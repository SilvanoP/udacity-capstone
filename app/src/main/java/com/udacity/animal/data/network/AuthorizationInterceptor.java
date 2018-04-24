package com.udacity.animal.data.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private String token;

    public AuthorizationInterceptor() {
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) {
        Request original = chain.request();
        try {
            if (token != null && !token.equals("")) {
                // Authentication required
                Request.Builder builder = original.newBuilder()
                        .addHeader("Authorization", "Basic " + token);

                // For this endpoint, the 'status' parameter is obligatory
                // The only value available for this parameter is 'all'
                // So this makes sure that this parameter will always be used in this endpoint
                if (original.url().toString().contains("malappinfo.php")) {
                    HttpUrl newUrl = original.url().newBuilder()
                            .addQueryParameter("status", "all")
                            .build();
                    builder.url(newUrl);
                }

                Request request = builder.build();
                return chain.proceed(request);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
