package com.udacity.animal.feature.listseries;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.animal.R;
import com.udacity.animal.data.entities.local.BaseSeries;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridSeriesAdapter extends RecyclerView.Adapter<GridSeriesAdapter.SeriesViewHolder> {

    private Context mContext;
    private GridSeriesAdapterListener mListener;
    private List<BaseSeries> mSeriesDataList;
    private Boolean isAnime;

    GridSeriesAdapter(Context context, List<BaseSeries> seriesDataList, Boolean isAnime,
                      GridSeriesAdapterListener listener) {
        mListener = listener;
        mSeriesDataList = seriesDataList;
        mContext = context;
        this.isAnime = isAnime;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_series, parent, false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        holder.bind(mSeriesDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSeriesDataList.size();
    }

    public void swapItems(List<BaseSeries> seriesDataList, Boolean isAnime) {
        this.isAnime = isAnime;
        mSeriesDataList = seriesDataList;
        this.notifyDataSetChanged();
    }

    class SeriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.listitem_poster)
        ImageView posterImage;
        @BindView(R.id.listitem_name)
        TextView nameText;

        SeriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bind(BaseSeries series) {
            Picasso.with(mContext).load(series.getPosterImage().getSmall())
                    .error(R.drawable.error_loading_image)
                    .placeholder(R.drawable.error_loading_image)
                    .into(posterImage);

            nameText.setText(series.getCanonicalTitle());
        }

        @Override
        public void onClick(View v) {
            mListener.onItemSelected(mSeriesDataList.get(getAdapterPosition()));
        }
    }

    interface GridSeriesAdapterListener {
        void onItemSelected(BaseSeries series);
    }
}
