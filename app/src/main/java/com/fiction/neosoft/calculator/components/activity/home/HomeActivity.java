package com.fiction.neosoft.calculator.components.activity.home;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fiction.neosoft.calculator.R;
import com.fiction.neosoft.calculator.adapters.DrawerItemCustomAdapter;
import com.fiction.neosoft.calculator.base.BaseActivity;
import com.fiction.neosoft.calculator.components.fragment.standard.StandardCalculatorFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeView, AdapterView.OnItemClickListener {

    @BindView(R.id.layout_draw)
    DrawerLayout mDrawer;
    @BindView(R.id.lv_drawer_list)
    ListView mNavigationList;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    protected HomePresenter homePresenter;
    private StandardCalculatorFragment standard;
    private ArrayList<String> mStringArray;

    @Override
    protected void initializePresenter() {
        homePresenter = new HomePresenter();
        super.presenter = homePresenter;
        presenter.setView(this);
    }

    @Override
    protected void initViews() {
        initNavigationDrawer();
        mStringArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.menu)));
        standard = new StandardCalculatorFragment();
        addFragment(R.id.fl_container, standard, "STANDARD");

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.menu_text, mStringArray);
        mNavigationList.setAdapter(adapter);
        mNavigationList.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void showProgress(String message) {
        //show Loader
    }

    @Override
    public void hideProgress() {
        //hide Loader
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                //homePresenter.showHistory();
                standard.onPressHistory();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Add new fragment in framelayout container
     *
     * @param containerViewId
     * @param fragment
     * @param fragmentTag
     */
    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();

        getSupportActionBar().setTitle(fragmentTag);
    }

    /**
     * Replace Current fragment with new one
     *
     * @param containerViewId
     * @param fragment
     * @param fragmentTag
     */
    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
        getSupportActionBar().setTitle(fragmentTag);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                homePresenter.onStandardMenuSelected();
                break;
            case 2:
                homePresenter.onScientifcMenuSelected();
                break;
            case 3:
                homePresenter.onScientifcMenuSelected();
                break;
        }
        mDrawer.closeDrawer(GravityCompat.START);
    }

    /**
     * Initialize Navigation Drawer
     */
    public void initNavigationDrawer() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawer, mToolBar, R.string.nav_open, R.string.nav_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

    }
}
