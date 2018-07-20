package com.udacity.animal.feature.shared;

import com.udacity.animal.data.entities.local.BaseSeries;

public interface FragmentListener {

    void onSelectSeriesFromList(BaseSeries series);
}
