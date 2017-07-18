package com.example.neosoft.calculator.listners;

/**
 * Created by neosoft on 21/6/17.
 */

public interface StandardKeypadListner {
    void onNumberKeyClick(String num);

    void onSymbolKeyClick(String action);

    void onPressEqual();

    void onPressPosneg();

    void onPressSquare();

    void onPressSqrt();

    void onPressBackSpace();

    void onPressClear();

    void onPressDot();

    void onPressClearAll();

    void onPressModulo();

    void onPressOneX();

}
