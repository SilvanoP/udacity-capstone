package com.udacity.animal.di;

import com.udacity.animal.data.DataModule;
import com.udacity.animal.feature.authentication.AuthenticationModule;
import com.udacity.animal.feature.authentication.LoginActivity;
import com.udacity.animal.feature.detailseries.SeriesDetailFragment;
import com.udacity.animal.feature.detailseries.SeriesDetailModule;
import com.udacity.animal.feature.listseries.ListSeriesFragment;
import com.udacity.animal.feature.listseries.ListSeriesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataModule.class, ContextModule.class, AuthenticationModule.class,
        ListSeriesModule.class, SeriesDetailModule.class})
public interface KitsuComponent {

    void inject(LoginActivity activity);
    void inject(ListSeriesFragment listSeriesFragment);
    void inject(SeriesDetailFragment seriesDetailFragment);

}
