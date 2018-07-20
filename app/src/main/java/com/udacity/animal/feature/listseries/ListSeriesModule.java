package com.udacity.animal.feature.listseries;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ListSeriesModule {

    @Binds
    public abstract ListSeriesContract.Presenter providesListSeriesPresenter(ListSeriesPresenter presenter);
}
