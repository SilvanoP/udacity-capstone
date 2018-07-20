package com.udacity.animal.feature.detailseries;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SeriesDetailModule {

    @Binds
    public abstract SeriesDetailContract.Presenter providesSeriesDetailPresenter(SeriesDetailPresenter presenter);
}
