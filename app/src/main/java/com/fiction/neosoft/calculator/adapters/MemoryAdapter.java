package com.fiction.neosoft.calculator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fiction.neosoft.calculator.R;
import com.fiction.neosoft.calculator.components.fragment.standard.StandardPresenter;
import com.fiction.neosoft.calculator.database.Memory;
import com.fiction.neosoft.calculator.listners.MemoryOperationListner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 21/6/17.
 */

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder> {

    List<Memory> mMemoryList;
    StandardPresenter mStandardPrensenter;
    MemoryOperationListner mOperationListner;

    public MemoryAdapter(ArrayList<Memory> mList, Context context) {
        mMemoryList = mList;
        mStandardPrensenter = new StandardPresenter();
    }

    @Override
    public MemoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_memory_history, parent, false);

        return new MemoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemoryViewHolder holder, int position) {
        Memory memory = mMemoryList.get(position);
        holder.mMemoryValue.setText(memory.getCalResult());
    }

    public void setMemoryClickListner(MemoryOperationListner memoryClickListner) {
        this.mOperationListner = memoryClickListner;
    }

    @Override
    public int getItemCount() {
        return mMemoryList.size();
    }

    public class MemoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_mem_value)
        TextView mMemoryValue;

        @BindView(R.id.btn_mc)
        TextView mBtnMC;

        @BindView(R.id.btn_m_plus)
        TextView mBtnMPlus;

        @BindView(R.id.btn_m_sub)
        TextView mBtnMSub;

        public MemoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.btn_mc)
        public void OnClickMC() {
            mStandardPrensenter.clickMemoryClear();
            if (mOperationListner != null) {
                mOperationListner.onMcClick();
            }
        }

        @OnClick(R.id.btn_m_plus)
        public void OnClickMPlus() {
            mStandardPrensenter.clickMemoryPlus(mMemoryValue.getText().toString());
            if (mOperationListner != null) {
                mOperationListner.onMPlusClick();
            }
        }

        @OnClick(R.id.btn_m_sub)
        public void OnClickMSub() {
            mStandardPrensenter.clickMemorySub();
            if (mOperationListner != null) {
                mOperationListner.onMSubClick();
            }
        }
    }
}
