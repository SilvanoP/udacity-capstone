package com.udacity.animal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.animal.R;
import com.udacity.animal.data.entities.user.BaseUserItem;
import com.udacity.animal.data.entities.user.UserAnimeItem;
import com.udacity.animal.data.entities.user.UserMangaItem;
import com.udacity.animal.presenter.ListPresenter;
import com.udacity.animal.presenter.ListPresenterImpl;
import com.udacity.animal.presenter.Status;
import com.udacity.animal.presenter.Type;
import com.udacity.animal.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListPresenter.Callback,
        ListFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    private ListPresenter mPresenter;
    private Type mType;
    private Status mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPresenter = new ListPresenterImpl(this);
        String username = "";

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.INTENT_EXTRA_USERNAME)) {
            username = intent.getStringExtra(Constants.INTENT_EXTRA_USERNAME);
        }

        // Get the last Status selected
        SharedPreferences pref = getSharedPreferences(Constants.ANIMAL_SHARED_PREFS, Context.MODE_PRIVATE);
        mStatus = Status.getStatus(pref.getInt(Constants.STATUS_PREFS, Status.WATCHING_READING.getValue()));
        mType = Type.getType(pref.getString(Constants.ANIME_MANGA_PREFS, Type.ANIME.getValue()));

        if (mType.equals(Type.ANIME)) {
            getSupportActionBar().setTitle(R.string.appbar_anime);
        } else {
            getSupportActionBar().setTitle(R.string.appbar_manga);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_drawer);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.appbar_toogle_open, R.string.appbar_toogle_close);
        mDrawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mPresenter.listUserItemByStatus(username, mStatus, mType);
    }

    public void updateListFragments(List<UserAnimeItem> animeItems, List<UserMangaItem> mangaItems) {
        if (!getResources().getBoolean(R.bool.isTablet)) {
            boolean isAnime = mType.equals(Type.ANIME);
            ListFragment listFragment = ListFragment.newInstance(isAnime, animeItems, mangaItems);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_frame, listFragment)
                    .commit();
        }
    }

    @Override
    public void onReceiveUserAnimeItems(List<UserAnimeItem> items) {
        if (items != null && items.size() > 0) {
            updateListFragments(items, null);
        }
    }

    @Override
    public void onReceiveUserMangaItems(List<UserMangaItem> items) {
        if (items != null && items.size() > 0) {
            updateListFragments(null, items);
        }
    }

    @Override
    public void onError(String message) {
        //TODO Toast showing the error
    }

    @Override
    public void onFragmentInteraction(BaseUserItem userItem) {
        // TODO pass userItem to another screen/fragment
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_switch:
                //TODO MAKE THE MENU WORK
                Toast.makeText(this, "SWITCH", Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
