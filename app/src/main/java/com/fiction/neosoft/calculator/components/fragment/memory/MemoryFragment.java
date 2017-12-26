package com.fiction.neosoft.calculator.components.fragment.memory;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiction.neosoft.calculator.R;
import com.fiction.neosoft.calculator.adapters.MemoryAdapter;
import com.fiction.neosoft.calculator.base.BaseFragment;
import com.fiction.neosoft.calculator.database.Memory;
import com.fiction.neosoft.calculator.listners.MemoryOperationListner;

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


    private MemoryAdapter mMemoryAdapter;
    private ArrayList<Memory> memoryArrayList;

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
        memoryArrayList = new ArrayList<>();
        memoryPresenter.getHistories();
    }

    @OnClick(R.id.iv_delete)
    public void cleanHistory() {
        memoryPresenter.deleteHistories();
    }

    @Override
    public void setHistoryList(ArrayList<Memory> historyList) {
        Log.d("memorylist", historyList.size() + "");
        memoryArrayList.addAll(historyList);
        mMemoryAdapter = new MemoryAdapter(memoryArrayList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mMemoryRecyclerView.setLayoutManager(mLayoutManager);
        mMemoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMemoryRecyclerView.setAdapter(mMemoryAdapter);
        mMemoryAdapter.notifyDataSetChanged();

        mMemoryAdapter.setMemoryClickListner(new MemoryOperationListner() {
            @Override
            public void onMcClick() {
                cleanHistory();
                memoryPresenter.getHistories();
            }

            @Override
            public void onMPlusClick() {
                memoryPresenter.getHistories();
                //mMemoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onMSubClick() {
                memoryPresenter.getHistories();
                //mMemoryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showEmptyMessage() {
        mTextEmpty.setVisibility(View.VISIBLE);
        mDeleteButton.setVisibility(View.GONE);
        mMemoryRecyclerView.setVisibility(View.GONE);
    }

    public MemoryAdapter getAdapter() {
        if (mMemoryAdapter == null) {
            mMemoryAdapter = new MemoryAdapter(memoryArrayList, getContext());
        }
        return mMemoryAdapter;
    }


}
