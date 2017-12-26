package com.fiction.neosoft.calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fiction.neosoft.calculator.R;
import com.fiction.neosoft.calculator.database.History;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by neosoft on 21/6/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    List<History> mHistoryList;

    public HistoryAdapter(ArrayList<History> mList) {
        mHistoryList = mList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_history_item, parent, false);

        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History history = mHistoryList.get(position);
        holder.mCalculationText.setText(history.getCalString() + "=");
        holder.mResult.setText(history.getCalResult());
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_calculation)
        TextView mCalculationText;

        @BindView(R.id.tv_result)
        TextView mResult;

        public HistoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
