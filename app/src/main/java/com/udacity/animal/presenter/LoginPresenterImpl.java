package com.udacity.animal.presenter;

import com.udacity.animal.data.network.AuthorizationInterceptor;
import com.udacity.animal.data.network.MALService;
import com.udacity.animal.data.network.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

public class LoginPresenterImpl implements LoginPresenter {

    private static final int CODE_SUCCESS = 200;
    private LoginPresenter.Callback callback;

    private MALService service;

    public LoginPresenterImpl(LoginPresenter.Callback callback) {
        this.callback = callback;
        service = ServiceGenerator.getINSTANCE().getService();
    }

    public void refreshToken(String token) {
        ServiceGenerator.getINSTANCE().refreshToken(token);
    }

    @Override
    public void verifyCredentials() {
        Call<Void> response = service.verfiyCredentials();
        response.enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                if (code == CODE_SUCCESS) {
                    callback.onReceiveResultCredentials(true);
                } else {
                    callback.onReceiveResultCredentials(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onReceiveResultCredentials(false);
            }
        });

    }
}
