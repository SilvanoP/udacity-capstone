package com.udacity.animal.presenter;

public interface LoginPresenter {

    void refreshToken(String token);
    void verifyCredentials();

    interface Callback {
        void onReceiveResultCredentials(boolean result);
    }

}
