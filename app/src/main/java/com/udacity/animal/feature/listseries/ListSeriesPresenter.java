package com.udacity.animal.feature.listseries;

import com.udacity.animal.data.DataRepository;
import com.udacity.animal.data.entities.local.BaseSeries;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ListSeriesPresenter implements ListSeriesContract.Presenter {

    private DataRepository dataRepository;
    private CompositeDisposable disposable;
    private WeakReference<ListSeriesContract.View> mView;

    @Inject
    ListSeriesPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        disposable = new CompositeDisposable();
    }

    @Override
    public void setView(ListSeriesContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void getListSeries(Boolean isAnime, Long userId) {
        disposable.clear();
        disposable.add(dataRepository.getListSeries(isAnime, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseSeries>>() {
                    @Override
                    public void accept(List<BaseSeries> baseSeries) throws Exception {
                        mView.get().receiveListSeries(baseSeries);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.get().receiveError();
                    }
                }));
    }

    public void onSearchSeriesByTitle(Boolean isAnime, String search) {
        disposable.clear();
        disposable.add(dataRepository.onSearchSeriesByTitle(isAnime, search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseSeries>>() {
                    @Override
                    public void accept(List<BaseSeries> baseSeries) throws Exception {
                        mView.get().receiveListSeries(baseSeries);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.get().receiveError();
                    }
                }));

    }

    @Override
    public void filterSeriesByUserStatus(Boolean isAnime, String status) {
        disposable.clear();
        disposable.add(dataRepository.onSearchSeriesByUserStatus(isAnime, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseSeries>>() {
                    @Override
                    public void accept(List<BaseSeries> baseSeries) throws Exception {
                        mView.get().receiveListSeries(baseSeries);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.get().receiveError();
                    }
                }));
    }

    @Override
    public void unbind() {
        disposable.dispose();
    }
}
