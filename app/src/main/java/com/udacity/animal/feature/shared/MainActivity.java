package com.udacity.animal.feature.shared;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.animal.R;
import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.feature.detailseries.SeriesDetailFragment;
import com.udacity.animal.feature.detailseries.UpdateSeriesDialogFragment;
import com.udacity.animal.feature.listseries.ListSeriesFragment;
import com.udacity.animal.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentListener,
        NavigationView.OnNavigationItemSelectedListener {

    private static final String BASE_SERIES_STATE = "base_series";
    private static final String FRAGMENT_STATE = "fragment";
    private static final String IS_ANIME_STATE = "is_anime";
    private static final String USER_STATE = "user";

    @BindView(R.id.list_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.list_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.list_include_toolbar)
    Toolbar toolbar;

    private Fragment mFragment;
    private BaseSeries mSeries;
    private Boolean isAnime;
    private FragmentManager mManager;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.appbar_anime);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_drawer);
        }

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.appbar_toogle_open, R.string.appbar_toogle_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            isAnime = savedInstanceState.getBoolean(IS_ANIME_STATE);
            mUser = savedInstanceState.getParcelable(USER_STATE);
            mSeries = savedInstanceState.getParcelable(BASE_SERIES_STATE);
            mFragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STATE);
        } else {
            isAnime = getIntent().getBooleanExtra(Constants.IS_ANIME_EXTRA, true);
            mUser = getIntent().getParcelableExtra(Constants.USER_EXTRA);
            mFragment = ListSeriesFragment.newInstance(mUser.getId(), isAnime);
        }

        View navHeaderView = navigationView.getHeaderView(0);
        ImageView avatarImage = navHeaderView.findViewById(R.id.navigation_header_avatar);
        if (avatarImage != null && mUser.getAvatar() != null) {
            Picasso.with(this)
                    .load(mUser.getAvatar().getOriginal())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .into(avatarImage);
        }
        TextView nameText = navHeaderView.findViewById(R.id.navigation_header_username);
        if (nameText != null) {
            nameText.setText(mUser.getName());
        }

        refreshFragment();
    }

    public void refreshFragment() {
        if (mManager == null) {
            mManager = getSupportFragmentManager();
        }

        mManager.beginTransaction()
                .replace(R.id.list_frame, mFragment)
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        // Change the hint of search button
        EditText searchEdit = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        if (isAnime) {
            searchEdit.setHint(R.string.search_anime);
        } else {
            searchEdit.setHint(R.string.search_manga);
        }

        // Customize the search listener on toolbar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSeries(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        MenuItem sortTitleItem = menu.findItem(R.id.menu_sort_title);
        MenuItem sortRatingItem = menu.findItem(R.id.menu_sort_rating);
        MenuItem addRemoveLibItem = menu.findItem(R.id.menu_add_remove_library);
        MenuItem updateItem = menu.findItem(R.id.menu_update_series);

        if (mFragment instanceof ListSeriesFragment) {
            addRemoveLibItem.setVisible(false);
            updateItem.setVisible(false);
        } else if (mFragment instanceof SeriesDetailFragment) {
            searchItem.setVisible(false);
            sortRatingItem.setVisible(false);
            sortTitleItem.setVisible(false);

            if (mSeries != null && !TextUtils.isEmpty(mSeries.getUserStatus())) {
                addRemoveLibItem.setTitle(R.string.menu_remove_from_library);
            } else {
                addRemoveLibItem.setTitle(R.string.menu_add_to_library);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_sort_title:
                ((ListSeriesFragment)mFragment).sortListBy(true);
                break;
            case R.id.menu_sort_rating:
                ((ListSeriesFragment)mFragment).sortListBy(false);
                break;
            case R.id.menu_add_remove_library:
                Toast.makeText(this, R.string.menu_add_to_library, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_update_series:
                //Toast.makeText(this, R.string.menu_update, Toast.LENGTH_SHORT).show();
                updateSeries();
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_switch:
                switchSeriesKind();
                break;
            case R.id.nav_menu_completed:
                filterList(Constants.UserStatus.COMPLETED);
                break;
            case R.id.nav_menu_dropped:
                filterList(Constants.UserStatus.DROPPED);
                break;
            case R.id.nav_menu_in_progress:
                filterList(Constants.UserStatus.CURRENT);
                break;
            case R.id.nav_menu_on_hold:
                filterList(Constants.UserStatus.ON_HOLD);
                break;
            case R.id.nav_menu_planned:
                filterList(Constants.UserStatus.PLANNED);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSelectSeriesFromList(BaseSeries series) {
        if (series != null) {
            mSeries = series;
            mFragment = SeriesDetailFragment.newInstance(series, isAnime);
            refreshFragment();
        }
    }

    public void switchSeriesKind() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        Menu menu = navigationView.getMenu();
        MenuItem switchItem = menu.findItem(R.id.nav_menu_switch);

        if (isAnime) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(R.string.appbar_manga);
            }
            switchItem.setTitle(R.string.menu_switch_anime);
        } else {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(R.string.appbar_anime);
            }
            switchItem.setTitle(R.string.menu_switch_manga);
        }

        isAnime = !isAnime;
        if (mFragment instanceof ListSeriesFragment) {
            ((ListSeriesFragment)mFragment).switchList(isAnime);
        } else {
            mFragment = ListSeriesFragment.newInstance(mUser.getId(), isAnime);
            refreshFragment();
            ((ListSeriesFragment)mFragment).switchList(isAnime);
        }
    }

    public void searchSeries(String query) {
        ((ListSeriesFragment) mFragment).searchItem(query);
    }

    public void filterList(String status) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (mFragment instanceof ListSeriesFragment) {
            ((ListSeriesFragment) mFragment).filterSeriesByUserStatus(status);
        } else {
            mFragment = ListSeriesFragment.newInstance(mUser.getId(), isAnime);
            refreshFragment();
            ((ListSeriesFragment) mFragment).filterSeriesByUserStatus(status);
        }
    }

    public void updateSeries() {
        int score = mSeries.getRatingTwenty() == null ? 0 : mSeries.getRatingTwenty()/2;
        int maxProgress;
        if (isAnime) {
            maxProgress = ((AnimeSeries) mSeries).getEpisodeCount();
        } else {
            maxProgress = ((MangaSeries) mSeries).getChapterCount();
        }

        UpdateSeriesDialogFragment fragment = UpdateSeriesDialogFragment.newInstance(maxProgress, score,
                mSeries.getProgress(), mSeries.getStatus());

        fragment.show(mManager, UpdateSeriesDialogFragment.class.getSimpleName());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mManager.putFragment(outState, FRAGMENT_STATE, mFragment);
        outState.putBoolean(IS_ANIME_STATE, isAnime);
        outState.putParcelable(USER_STATE, mUser);
        if (mSeries != null) {
            outState.putParcelable(BASE_SERIES_STATE, mSeries);
        }

        super.onSaveInstanceState(outState);
    }
}
