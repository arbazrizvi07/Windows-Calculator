package com.example.neosoft.calculator.components.fragment.standard_keyboard;

import android.view.View;
import android.widget.TextView;

import com.example.neosoft.calculator.R;
import com.example.neosoft.calculator.base.BaseFragment;
import com.example.neosoft.calculator.listners.StandardKeypadListner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 21/6/17.
 */

public class StandardKeyboardFragment extends BaseFragment implements StdKeyboardView {

    protected StandardKeyboardPresenter standardKeyboardPresenter;
    protected StandardKeypadListner standardKeypadListner;

    @BindView(R.id.num0)
    TextView Key0;
    @BindView(R.id.num1)
    TextView Key1;
    @BindView(R.id.num2)
    TextView Key2;
    @BindView(R.id.num3)
    TextView Key3;
    @BindView(R.id.num4)
    TextView Key4;
    @BindView(R.id.num5)
    TextView Key5;
    @BindView(R.id.num6)
    TextView Key6;
    @BindView(R.id.num7)
    TextView Key7;
    @BindView(R.id.num8)
    TextView Key8;
    @BindView(R.id.num9)
    TextView Key9;
    @BindView(R.id.dot)
    TextView dot;
    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.backSpace)
    TextView backSpace;
    @BindView(R.id.plus)
    TextView plus;
    @BindView(R.id.minus)
    TextView minus;
    @BindView(R.id.divide)
    TextView divide;
    @BindView(R.id.multiply)
    TextView multiply;
    @BindView(R.id.sqrt)
    TextView sqrt;
    @BindView(R.id.square)
    TextView square;
    @BindView(R.id.posneg)
    TextView posneg;
    @BindView(R.id.equal)
    TextView equal;
    @BindView(R.id.tv_clear_all)
    TextView clearAll;
    @BindView(R.id.tv_modulo)
    TextView mModulo;
    @BindView(R.id.tv_one_x)
    TextView mOneX;

    @Override
    protected void initializePresenter() {
        standardKeyboardPresenter = new StandardKeyboardPresenter();
        super.presenter = standardKeyboardPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_std_keyboard;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.num0)
    public void onPressKey0() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("0");
        }
    }

    @OnClick(R.id.num1)
    public void onPressKey1() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("1");
        }
    }

    @OnClick(R.id.num2)
    public void onPressKey2() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("2");
        }
    }

    @OnClick(R.id.num3)
    public void onPressKey3() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("3");
        }
    }

    @OnClick(R.id.num4)
    public void onPressKey4() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("4");
        }
    }

    @OnClick(R.id.num5)
    public void onPressKey5() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("5");
        }
    }

    @OnClick(R.id.num6)
    public void onPressKey6() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("6");
        }
    }

    @OnClick(R.id.num7)
    public void onPressKey7() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("7");
        }
    }

    @OnClick(R.id.num8)
    public void onPressKey8() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("8");
        }
    }

    @OnClick(R.id.num9)
    public void onPressKey9() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onNumberKeyClick("9");
        }
    }

    @OnClick(R.id.dot)
    public void onPressDot() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressDot();
        }
    }

    @OnClick(R.id.clear)
    public void onPressClear() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressClear();
        }
    }

    @OnClick(R.id.backSpace)
    public void onPressBackSpace() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressBackSpace();
        }
    }

    @OnClick(R.id.plus)
    public void onPressPlus() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onSymbolKeyClick("+");
        }
    }

    @OnClick(R.id.minus)
    public void onPressMinus() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onSymbolKeyClick("-");
        }
    }

    @OnClick(R.id.divide)
    public void onPressDivide() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onSymbolKeyClick("/");
        }
    }

    @OnClick(R.id.multiply)
    public void onPressMultiply() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onSymbolKeyClick("*");
        }
    }

    @OnClick(R.id.sqrt)
    public void onPressSqrt() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressSqrt();
        }
    }

    @OnClick(R.id.square)
    public void onPressSquare() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressSquare();
        }
    }

    @OnClick(R.id.posneg)
    public void onPressPosneg() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressPosneg();
        }
    }

    @OnClick(R.id.equal)
    public void onPressEqual() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressEqual();
        }
    }

    @OnClick(R.id.tv_clear_all)
    public void onPressClearAll() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressClear();
        }
    }

    @OnClick(R.id.tv_modulo)
    public void onPressModulo() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressModulo();
        }
    }

    @OnClick(R.id.tv_one_x)
    public void onPressOneX() {
        if (standardKeypadListner != null) {
            standardKeypadListner.onPressOneX();
        }
    }

    public void onPressHistory() {

    }

    public void initKeypadListner(StandardKeypadListner stdKeyListner) {
        standardKeypadListner = stdKeyListner;
    }
}
