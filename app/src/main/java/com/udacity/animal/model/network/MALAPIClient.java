package com.udacity.animal.model.network;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MALAPIClient {

    private static final String BASE_URL = "https://myanimelist.net/";

    private OkHttpClient.Builder client = new OkHttpClient.Builder();

    public MALService getService(String username, String password) {
        AuthorizationInterceptor interceptor = new AuthorizationInterceptor(username, password);
        if (!client.interceptors().contains(interceptor)) {
            client.addInterceptor(interceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                .client(client.build())
                .build();

        return retrofit.create(MALService.class);
    }
}
