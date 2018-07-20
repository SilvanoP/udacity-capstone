package com.udacity.animal.feature.detailseries;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.udacity.animal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateSeriesDialogFragment extends DialogFragment {

    public static final String MAX_PROGRESS = "MAX_PROGRESS";
    public static final String PROGRESS_ARG = "PROGRESS";
    public static final String SCORE_ARG = "SCORE";
    public static final String STATUS_ARG = "STATUS";

    @BindView(R.id.dialog_update_progress_seek)
    AppCompatSeekBar progressSeekBar;
    @BindView(R.id.dialog_update_progress_text)
    TextView progressCountText;
    @BindView(R.id.dialog_update_score_seek)
    AppCompatSeekBar scoreSeekBar;
    @BindView(R.id.dialog_update_score_text)
    TextView scoreCountText;
    @BindView(R.id.dialog_update_status_spinner)
    Spinner statusSpinner;

    private int mProgress;
    private int mScore;

    public static UpdateSeriesDialogFragment newInstance(int maxProgress, int score, int progress,
                                                         String status) {
        UpdateSeriesDialogFragment fragment = new UpdateSeriesDialogFragment();
        Bundle args = new Bundle();
        args.putInt(MAX_PROGRESS, maxProgress);
        args.putInt(PROGRESS_ARG, progress);
        args.putInt(SCORE_ARG, score);
        args.putString(STATUS_ARG, status);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_update_series, container);
        ButterKnife.bind(this, view);

        getDialog().setTitle(R.string.menu_update);

        int maxProgress = 0;
        mProgress = 0;
        mScore = 0;
        String status = "";
        if (getArguments() != null && !getArguments().isEmpty()) {
            maxProgress = getArguments().getInt(MAX_PROGRESS);
            mProgress = getArguments().getInt(PROGRESS_ARG);
            mScore = getArguments().getInt(SCORE_ARG);
            status = getArguments().getString(STATUS_ARG);
        }

        progressCountText.setText(String.valueOf(mProgress));
        progressSeekBar.setMax(maxProgress);
        progressSeekBar.setProgress(mProgress);
        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress = progress;
                progressCountText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        scoreCountText.setText(String.valueOf(mScore));
        scoreSeekBar.setProgress(mScore);
        scoreSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mScore = progress;
                scoreCountText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        //TODO status spinner

        return view;
    }

    @OnClick({R.id.dialog_update_cancel_button, R.id.dialog_update_save_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_update_cancel_button:
                getDialog().cancel();
                break;
            case R.id.dialog_update_save_button:
                getDialog().dismiss();
                break;
        }
    }
}
