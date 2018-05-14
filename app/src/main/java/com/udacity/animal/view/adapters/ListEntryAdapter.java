package com.udacity.animal.view.adapters;

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
import com.udacity.animal.data.entities.user.BaseUserItem;
import com.udacity.animal.data.entities.user.UserAnimeItem;
import com.udacity.animal.data.entities.user.UserMangaItem;
import com.udacity.animal.view.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListEntryAdapter extends RecyclerView.Adapter<ListEntryAdapter.ListEntryViewHolder> {

    private Context mContext;
    private List<UserAnimeItem> mAnimeItems;
    private List<UserMangaItem> mMangaItems;
    private boolean isAnime;
    
    private ItemClickListener mListener;

    public ListEntryAdapter(Context context, boolean isAnime, ItemClickListener listener) {
        mContext = context;
        mListener = listener;
        this.isAnime = isAnime;
        mAnimeItems = new ArrayList<>();
        mMangaItems = new ArrayList<>();
    }

    public void setAnimeItems(List<UserAnimeItem> animeItems) {
        isAnime = true;
        mAnimeItems.clear();
        mAnimeItems = animeItems;
        this.notifyDataSetChanged();
    }

    public void setMangaItems(List<UserMangaItem> mangaItems) {
        isAnime = false;
        mMangaItems.clear();
        mMangaItems = mangaItems;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_list_item, parent, false);
        return new ListEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListEntryViewHolder holder, int position) {
        BaseUserItem item;
        if (isAnime) {
            item = mAnimeItems.get(position);
        } else {
            item = mMangaItems.get(position);
        }
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (isAnime) {
            return mAnimeItems.size();
        }
        return mMangaItems.size();
    }

    public class ListEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_poster_image)
        ImageView posterImage;
        @BindView(R.id.item_title_text)
        TextView titleText;

        public ListEntryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void bind(BaseUserItem item) {
            Picasso.with(mContext).load(item.getImageUrl())
                    .placeholder(R.drawable.error_loading_image)
                    .error(R.drawable.error_loading_image)
                    .into(posterImage);

            titleText.setText(item.getTitle());
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }
}
