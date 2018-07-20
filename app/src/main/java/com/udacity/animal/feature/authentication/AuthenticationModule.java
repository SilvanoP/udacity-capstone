package com.udacity.animal.feature.authentication;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AuthenticationModule {

    @Binds
    public abstract AuthenticationContract.Presenter providesAuthenticationPresenter(AuthenticationPresenter presenter);
}
