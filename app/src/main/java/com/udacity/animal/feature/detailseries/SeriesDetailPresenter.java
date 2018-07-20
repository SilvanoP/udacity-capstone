package com.udacity.animal.feature.detailseries;

import com.udacity.animal.data.DataRepository;
import com.udacity.animal.data.entities.local.BaseSeries;

import java.lang.ref.WeakReference;

public class SeriesDetailPresenter implements SeriesDetailContract.Presenter {

    private DataRepository dataRepository;
    private WeakReference<SeriesDetailContract.View> mView;

    public SeriesDetailPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void setView(SeriesDetailContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void saveSeries(BaseSeries series, boolean isAnime) {
        dataRepository.saveSeries(series, isAnime);
    }

    @Override
    public void updateSeries(BaseSeries series, boolean isAnime) {
        dataRepository.updateSeries(series, isAnime);
    }

    @Override
    public void deleteSeries(BaseSeries series, boolean isAnime) {
        dataRepository.deleteSeries(series, isAnime);
    }
}
