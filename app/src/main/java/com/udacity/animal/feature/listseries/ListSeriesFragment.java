package com.udacity.animal.feature.listseries;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.animal.AnimalApplication;
import com.udacity.animal.R;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.feature.shared.FragmentListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListener} interface
 * to handle interaction events.
 * Use the {@link ListSeriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListSeriesFragment extends Fragment implements ListSeriesContract.View, GridSeriesAdapter.GridSeriesAdapterListener {

    private static final String ARG_IS_ANIME = "IS_ANIME";
    private static final String ARG_USER_ID = "USER_ID";
    private static final String IS_ANIME_STATE = "IS_ANIME_STATE";
    private static final String LIST_SERIES_STATE = "LIST_SERIES_STATE";
    private static final String USER_ID_STATE = "USER_ID_STATE";

    private Long mUserId;
    private GridSeriesAdapter mAdapter;
    private FragmentListener mListener;
    private Boolean isAnime;
    private List<BaseSeries> mSeriesList;

    @Inject
    ListSeriesContract.Presenter mPresenter;

    @BindView(R.id.list_recycler)
    RecyclerView mRecyclerView;

    public ListSeriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId Id of the current logged User.
     * @param isAnime Defines which list the user will see.
     * @return A new instance of fragment ListSeriesFragment.
     */
    public static ListSeriesFragment newInstance(Long userId, Boolean isAnime) {
        ListSeriesFragment fragment = new ListSeriesFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_USER_ID, userId);
        args.putBoolean(ARG_IS_ANIME, isAnime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            mUserId = savedInstanceState.getLong(USER_ID_STATE);
            isAnime = savedInstanceState.getBoolean(IS_ANIME_STATE);
            mSeriesList = savedInstanceState.getParcelableArrayList(LIST_SERIES_STATE);
        } else {
            if (getArguments() != null) {
                mUserId = getArguments().getLong(ARG_USER_ID);
                isAnime = getArguments().getBoolean(ARG_IS_ANIME);
            }
        }

        AnimalApplication.getKitsuComponent().inject(this);
        mPresenter.setView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_series, container, false);
        ButterKnife.bind(this, view);

        int numColumns = getResources().getInteger(R.integer.num_grid_columns);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numColumns);
        mRecyclerView.setLayoutManager(layoutManager);

        mSeriesList = new ArrayList<>();
        initializeAdapter();
        requestSeries();

        return view;
    }

    public void initializeAdapter() {
        mAdapter = new GridSeriesAdapter(getContext(), mSeriesList, true, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void requestSeries() {
        mPresenter.getListSeries(isAnime, mUserId);
    }

    public void refreshSeriesGrid() {
        if (mSeriesList == null || mSeriesList.size() == 0) {
            Toast.makeText(getContext(), R.string.toast_empty_list, Toast.LENGTH_SHORT).show();
        }

        if (mAdapter == null) {
            initializeAdapter();
        } else {
            mAdapter.swapItems(mSeriesList, true);
        }
    }

    public void sortListBy(boolean title) {
        if (mSeriesList == null || mSeriesList.size() <= 0) {
            return;
        }

        if (title) {
            Collections.sort(mSeriesList, new Comparator<BaseSeries>() {
                @Override
                public int compare(BaseSeries o1, BaseSeries o2) {
                    if (o1 == null && o2 == null) {
                        return 0;
                    } else if (o1 == null) {
                        return -1;
                    } else if (o2 == null) {
                        return 1;
                    }
                    return o1.getCanonicalTitle().compareTo(o2.getCanonicalTitle());
                }
            });
        } else {
            Collections.sort(mSeriesList, new Comparator<BaseSeries>() {
                @Override
                public int compare(BaseSeries o1, BaseSeries o2) {
                    if (o1 == null && o2 == null) {
                        return 0;
                    } else if (o1 == null) {
                        return -1;
                    } else if (o2 == null) {
                        return 1;
                    }
                    return o1.getAverageRating().compareTo(o2.getAverageRating());
                }
            });
        }

        refreshSeriesGrid();
    }

    public void switchList(boolean isAnime) {
        this.isAnime = isAnime;
        requestSeries();
    }

    public void searchItem(String seriesName) {
        mPresenter.onSearchSeriesByTitle(isAnime, seriesName);
    }

    public void filterSeriesByUserStatus(String status) {
        mPresenter.filterSeriesByUserStatus(isAnime, status);
    }

    @Override
    public void receiveListSeries(List<BaseSeries> seriesDataList) {
        mSeriesList = seriesDataList;
        sortListBy(true); // TODO use shared pref
        refreshSeriesGrid();
    }

    @Override
    public void receiveError() {
        Toast.makeText(getContext(), R.string.toast_empty_list, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(BaseSeries series) {
        mListener.onSelectSeriesFromList(series);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong(USER_ID_STATE, mUserId);
        outState.putBoolean(IS_ANIME_STATE, isAnime);
        outState.putParcelableArrayList(LIST_SERIES_STATE, new ArrayList<Parcelable>(mSeriesList));

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            mListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
