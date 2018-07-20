package com.udacity.animal.feature.detailseries;

import com.udacity.animal.data.entities.local.BaseSeries;

public interface SeriesDetailContract {

    interface View {

    }

    interface Presenter {
        void setView(SeriesDetailContract.View view);
        void saveSeries(BaseSeries series, boolean isAnime);
        void updateSeries(BaseSeries series, boolean isAnime);
        void deleteSeries(BaseSeries series, boolean isAnime);
    }
}
