package com.udacity.animal.feature.authentication;

import com.udacity.animal.data.entities.local.User;

public interface AuthenticationContract {

    interface View {
        void onAuthSuccess(User user);
        void onAuthError();
    }

    interface Presenter {
        void onLogin(String username, String email, String password);
        void setView(AuthenticationContract.View view);
        void unbind();
    }
}
