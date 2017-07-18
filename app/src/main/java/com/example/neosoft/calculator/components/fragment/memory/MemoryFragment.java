package com.example.neosoft.calculator.components.fragment.memory;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.neosoft.calculator.R;
import com.example.neosoft.calculator.adapters.HistoryAdapter;
import com.example.neosoft.calculator.base.BaseFragment;
import com.example.neosoft.calculator.database.History;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 20/6/17.
 */

public class MemoryFragment extends BaseFragment implements MemoryView {

    MemoryPresenter memoryPresenter;

    @BindView(R.id.view_recycler)
    RecyclerView mMemoryRecyclerView;
    @BindView(R.id.iv_delete)
    ImageView mDeleteButton;
    @BindView(R.id.tv_msg_no_history)
    TextView mTextEmpty;


    private HistoryAdapter mHistoryAdapter;
    private ArrayList<History> historyArrayList;

    @Override
    protected void initializePresenter() {
        memoryPresenter = new MemoryPresenter();
        super.presenter = memoryPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(view);
        historyArrayList = new ArrayList<>();
        memoryPresenter.getHistories();
    }

    @OnClick(R.id.iv_delete)
    public void cleanHistory() {
        memoryPresenter.deleteHistories();
    }

    @Override
    public void setHistoryList(ArrayList<History> historyList) {
        mHistoryAdapter = new HistoryAdapter(historyList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mMemoryRecyclerView.setLayoutManager(mLayoutManager);
        mMemoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMemoryRecyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    public void showEmptyMessage() {
        mTextEmpty.setVisibility(View.VISIBLE);
        mDeleteButton.setVisibility(View.GONE);
        mMemoryRecyclerView.setVisibility(View.GONE);
    }
}
