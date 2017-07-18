package com.example.neosoft.calculator.components.fragment.memory;

import com.example.neosoft.calculator.base.listeners.BaseView;
import com.example.neosoft.calculator.database.History;

import java.util.ArrayList;

/**
 * Created by neosoft on 20/6/17.
 */

public interface MemoryView extends BaseView {
    void setHistoryList(ArrayList<History> historyList);

    void showEmptyMessage();
}