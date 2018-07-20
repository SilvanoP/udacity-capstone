package com.udacity.animal.feature.detailseries;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.animal.R;
import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.feature.shared.FragmentListener;
import com.udacity.animal.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.udacity.animal.feature.shared.FragmentListener} interface
 * to handle interaction events.
 * Use the {@link SeriesDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeriesDetailFragment extends Fragment {

    private static final String ARG_BASE_SERIES = "BASE_SERIES";
    private static final String ARG_IS_ANIME = "IS_ANIME";
    private static final String BASE_SERIES_STATE = "BASE_SERIES_STATE";
    private static final String IS_ANIME_STATE = "IS_ANIME_STATE";

    @BindView(R.id.series_detail_backdrop_image)
    ImageView mBackdropImageView;
    @BindView(R.id.series_detail_poster_image)
    ImageView mPosterImageView;
    @BindView(R.id.series_detail_name_text)
    TextView mNameTextView;
    @BindView(R.id.series_detail_original_name_text)
    TextView mOriginalNameTextView;
    @BindView(R.id.series_detail_overview_text)
    TextView mOverviewTextView;
    @BindView(R.id.series_detail_score_rating_text)
    TextView mScoreRatingTextView;
    @BindView(R.id.series_detail_progress_title)
    TextView mProgressTitleTextView;
    @BindView(R.id.series_detail_progress_number)
    TextView mProgressTextView;
    @BindView(R.id.series_detail_status_title)
    TextView mStatusTitleTextView;
    @BindView(R.id.series_detail_status_text)
    TextView mStatusTextView;

    private BaseSeries mBaseSeries;
    private Boolean isAnime;

    private FragmentListener mListener;

    public SeriesDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param series The generic series.
     * @return A new instance of fragment SeriesDetailFragment.
     */
    public static SeriesDetailFragment newInstance(BaseSeries series, Boolean isAnime) {
        SeriesDetailFragment fragment = new SeriesDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BASE_SERIES, series);
        args.putBoolean(ARG_IS_ANIME, isAnime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            mBaseSeries = savedInstanceState.getParcelable(BASE_SERIES_STATE);
            isAnime = savedInstanceState.getBoolean(IS_ANIME_STATE);
        } else {
            if (getArguments() != null) {
                mBaseSeries = getArguments().getParcelable(ARG_BASE_SERIES);
                isAnime = getArguments().getBoolean(ARG_IS_ANIME, true);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_detail, container, false);
        ButterKnife.bind(this, view);


        setHasOptionsMenu(true);
        fillFields();
        return view;
    }

    private void fillFields() {
        String coverUrl = mBaseSeries.getCoverImage().getOriginal();
        String posterUrl = mBaseSeries.getPosterImage().getLarge();
        if (getResources().getDisplayMetrics().density < 2f) {
            posterUrl = mBaseSeries.getPosterImage().getSmall();
            coverUrl = mBaseSeries.getCoverImage().getSmall();
        }

        if (TextUtils.isEmpty(coverUrl)) {
            mBackdropImageView.setVisibility(View.GONE);
        } else {
            Picasso.with(getContext())
                    .load(coverUrl)
                    .placeholder(R.drawable.error_backdrop_image)
                    .error(R.drawable.error_backdrop_image)
                    .into(mBackdropImageView);
        }

        Picasso.with(getContext())
                .load(posterUrl)
                .placeholder(R.drawable.error_backdrop_image)
                .error(R.drawable.error_backdrop_image)
                .into(mPosterImageView);

        mNameTextView.setText(mBaseSeries.getCanonicalTitle());

        String originalTitle = mBaseSeries.getTitleJapanese();
        if (TextUtils.isEmpty(originalTitle)) {
            originalTitle = mBaseSeries.getTitleRomanji();
        }
        mOriginalNameTextView.setText(originalTitle);

        String myRating = "0";
        if (mBaseSeries.getRatingTwenty() != null) {
            myRating = String.valueOf(mBaseSeries.getRatingTwenty() / 4);
        }
        mScoreRatingTextView.setText(myRating);

        String progressTitle;
        String progress = mBaseSeries.getProgress() != null ? mBaseSeries.getProgress().toString() : "0";
        progress += "/";
        if (isAnime) {
            progressTitle = getString(R.string.episodes);
            progress += ((AnimeSeries) mBaseSeries).getEpisodeCount().toString();
        } else {
            progressTitle = getString(R.string.chapters);
            progress += ((MangaSeries) mBaseSeries).getChapterCount().toString();
        }
        mProgressTitleTextView.setText(progressTitle);
        mProgressTextView.setText(progress);

        String status = mBaseSeries.getUserStatus();
        switch (status) {
            case Constants.UserStatus.CURRENT:
                if (isAnime) {
                    mStatusTextView.setText(R.string.watching);
                } else {
                    mStatusTextView.setText(R.string.reading);
                }
                break;
            case Constants.UserStatus.PLANNED:
                if (isAnime) {
                    mStatusTextView.setText(R.string.plan_to_watch);
                } else {
                    mStatusTextView.setText(R.string.plan_to_read);
                }
                break;
            case Constants.UserStatus.COMPLETED:
                mStatusTextView.setText(R.string.completed);
                break;
            case Constants.UserStatus.DROPPED:
                mStatusTextView.setText(R.string.dropped);
                break;
            case Constants.UserStatus.ON_HOLD:
                mStatusTextView.setText(R.string.on_hold);
                break;
            default:
                if (isAnime) {
                    mStatusTextView.setText(R.string.didnt_watch);
                } else {
                    mStatusTextView.setText(R.string.didnt_read);
                }
        }

        mOverviewTextView.setText(mBaseSeries.getSynopsis());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(BASE_SERIES_STATE, mBaseSeries);
        outState.putBoolean(IS_ANIME_STATE, isAnime);

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
