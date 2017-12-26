package com.fiction.neosoft.calculator.base;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.fiction.neosoft.calculator.base.listeners.BasePresenter;
import com.fiction.neosoft.calculator.base.listeners.BaseView;
import com.fiction.neosoft.calculator.listners.PermissionCallback;
import com.fiction.neosoft.calculator.managers.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected BasePresenter presenter;
    protected Unbinder unbinder;
    private PermissionCallback listener;

    protected abstract void initializePresenter();

    protected abstract void initViews();

    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initializePresenter();
        initViews();

        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void getPermission(PermissionCallback listener, int requestCode, boolean vibrate) {

        this.listener = listener;

        if (PermissionManager.askForPermission()) {

            List<String> listPermissions = new ArrayList<>();

            // Request Vibrate permission
            if (vibrate && !PermissionManager.hasVibratePermission()) {

                listPermissions.add(Manifest.permission.VIBRATE);
            }

            if (listPermissions.size() > 0) {

                String[] permissions = listPermissions.toArray(new String[listPermissions.size()]);

                ActivityCompat.requestPermissions(this, permissions, requestCode);
            } else {

                listener.onPermissionResult(requestCode, true);
            }

        } else {

            listener.onPermissionResult(requestCode, true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
