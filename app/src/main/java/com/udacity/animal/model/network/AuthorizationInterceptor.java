package com.udacity.animal.model.network;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private String username;
    private String password;

    public AuthorizationInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) {
        Request original = chain.request();
        String notEncodedValue = username + ":" + password;
        String encoded_value = Base64.encodeToString(notEncodedValue.getBytes(), 0);
        try {
            Request request = original.newBuilder()
                    .header("Authorization","Basic " + encoded_value.trim())
                    .build();
            return chain.proceed(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
