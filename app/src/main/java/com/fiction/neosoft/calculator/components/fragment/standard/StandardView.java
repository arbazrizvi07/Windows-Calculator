package com.fiction.neosoft.calculator.components.fragment.standard;

import com.fiction.neosoft.calculator.base.listeners.BaseView;

/**
 * Created by neosoft on 16/6/17.
 */

public interface StandardView extends BaseView {
    void backPressPerform(String text, boolean isCountZero);

    void setModuloResult(String result);

    void setOneXResult(String result, String calculation);

    void showMemoryResult(String calResult);

    void showToast(String msg);
}
