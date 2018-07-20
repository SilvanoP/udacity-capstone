package com.udacity.animal.feature.authentication;

import com.udacity.animal.data.DataRepository;
import com.udacity.animal.data.DataSource;
import com.udacity.animal.data.entities.local.User;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    private WeakReference<AuthenticationContract.View> mView;
    private DataRepository dataRepository;
    private CompositeDisposable disposable;

    @Inject
    AuthenticationPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.disposable = new CompositeDisposable();
    }

    public void setView(AuthenticationContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void unbind() {
        disposable.dispose();
    }

    @Override
    public void onLogin(String username, String email, String password) {
        disposable.add(dataRepository.onLogin(username, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        mView.get().onAuthSuccess(user);
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                        mView.get().onAuthError();
                    }
                }, new Action() {
                    @Override
                    public void run() {
                        // Completes with no user found
                        mView.get().onAuthError();
                    }
                }));
    }
}
