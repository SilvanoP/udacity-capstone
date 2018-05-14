package com.udacity.animal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.animal.R;
import com.udacity.animal.data.entities.user.BaseUserItem;
import com.udacity.animal.data.entities.user.UserAnimeItem;
import com.udacity.animal.data.entities.user.UserMangaItem;
import com.udacity.animal.utils.Constants;
import com.udacity.animal.view.adapters.ListEntryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String LIST_USER_ITEMS = "LIST_USER_ITEMS";

    // TODO: Rename and change types of parameters
    private Boolean isAnime;
    private List<UserAnimeItem> mAnimeItems;
    private List<UserMangaItem> mMangaItems;

    private ListEntryAdapter mAdapter;

    @BindView(R.id.list_recycler_view)
    RecyclerView mListRecycler;

    private ItemClickListener mItemClickListener;
    private OnFragmentInteractionListener mCallback;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isAnime Defines if the list that will be shown is an Anime list or a Manga list.
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(boolean isAnime, List<UserAnimeItem> animeItems,
                                           List<UserMangaItem> mangaItems) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ANIME_MANGA_BUNDLE, isAnime);
        if (isAnime) {
            args.putParcelableArrayList(Constants.ITEMS_LIST_BUNDLE, new ArrayList<Parcelable>(animeItems));
        } else {
            args.putParcelableArrayList(Constants.ITEMS_LIST_BUNDLE, new ArrayList<Parcelable>(mangaItems));
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isAnime = getArguments().getBoolean(Constants.ANIME_MANGA_BUNDLE);
            if (isAnime) {
                mAnimeItems = getArguments().getParcelableArrayList(Constants.ITEMS_LIST_BUNDLE);
            } else {
                mMangaItems = getArguments().getParcelableArrayList(Constants.ITEMS_LIST_BUNDLE);
            }
        }

        mItemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int clickedItemIndex) {
                // TODO call main activity and send the clicked item
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rooView);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.num_grid_columns));

        mListRecycler.setLayoutManager(layoutManager);
        refreshList();

        return rooView;
    }

    public void refreshList() {
        if ((mAnimeItems == null || mAnimeItems.size() == 0)
                && (mMangaItems == null || mMangaItems.size() == 0)) {
            Toast.makeText(getContext(), R.string.toast_empty_list, Toast.LENGTH_SHORT).show();
        }

        if (mAdapter == null) {
            mAdapter = new ListEntryAdapter(getContext(), isAnime, mItemClickListener);
        }

        if (isAnime) {
            mAdapter.setAnimeItems(mAnimeItems);
        } else {
            mAdapter.setMangaItems(mMangaItems);
        }

        mListRecycler.setAdapter(mAdapter);
    }

    public void updateItems(boolean isAnime, List<UserAnimeItem> animeItems, List<UserMangaItem> mangaItems) {
        this.isAnime = isAnime;
        mAnimeItems = animeItems;
        mMangaItems = mangaItems;
        refreshList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mCallback = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
        mAnimeItems = null;
        mMangaItems = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(BaseUserItem userItem);
    }
}
