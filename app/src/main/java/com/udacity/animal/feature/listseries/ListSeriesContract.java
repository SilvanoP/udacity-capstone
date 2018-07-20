package com.udacity.animal.feature.listseries;

import com.udacity.animal.data.entities.local.BaseSeries;

import java.util.List;

public interface ListSeriesContract {

    interface View {
        void receiveListSeries(List<BaseSeries> seriesDataList);
        void receiveError();
    }

    interface Presenter {
        void setView(ListSeriesContract.View view);
        void getListSeries(Boolean isAnime, Long userId);
        void onSearchSeriesByTitle(Boolean isAnime, String search);
        void filterSeriesByUserStatus(Boolean isAnime, String status);
        void unbind();
    }
}
