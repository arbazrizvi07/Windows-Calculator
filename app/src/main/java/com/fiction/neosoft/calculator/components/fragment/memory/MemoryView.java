package com.fiction.neosoft.calculator.components.fragment.memory;

import com.fiction.neosoft.calculator.base.listeners.BaseView;
import com.fiction.neosoft.calculator.database.Memory;

import java.util.ArrayList;

/**
 * Created by neosoft on 20/6/17.
 */

public interface MemoryView extends BaseView {
    void setHistoryList(ArrayList<Memory> historyList);

    void showEmptyMessage();
}
