package com.fiction.neosoft.calculator.components.fragment.history;

import com.fiction.neosoft.calculator.base.listeners.BaseView;
import com.fiction.neosoft.calculator.database.History;

import java.util.ArrayList;

/**
 * Created by neosoft on 20/6/17.
 */

public interface HistoryView extends BaseView {
    void setHistoryList(ArrayList<History> historyList);

    void showEmptyMessage();
}
