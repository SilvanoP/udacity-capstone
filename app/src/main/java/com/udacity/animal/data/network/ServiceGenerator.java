package com.udacity.animal.data.network;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = "https://myanimelist.net/";
    private static final ServiceGenerator INSTANCE = new ServiceGenerator();

    private AuthorizationInterceptor interceptor;
    private Retrofit retrofit;

    private ServiceGenerator() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        interceptor = new AuthorizationInterceptor();

        clientBuilder.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                .client(clientBuilder.build())
                .build();
    }

    public static ServiceGenerator INSTANCE() {
        return INSTANCE;
    }

    public MALService getService() {
        return retrofit.create(MALService.class);
    }

    public void refreshToken(String token) {
        interceptor.setToken(token);
    }
}
