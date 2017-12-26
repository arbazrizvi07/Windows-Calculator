package com.fiction.neosoft.calculator.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiction.neosoft.calculator.base.listeners.BasePresenter;
import com.fiction.neosoft.calculator.base.listeners.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements BaseView {

    protected FragmentManager fragmentManager;

    protected BasePresenter presenter;

    protected abstract void initializePresenter();

    protected Unbinder unbinder;

    public abstract int getLayoutId();

    private View view;


    private String toolbarTitleKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        initializePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        if (presenter != null) {
            presenter.initialize(getArguments());
        }
        initViews(view);
        return view;
    }

    protected abstract void initViews(View view);

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
