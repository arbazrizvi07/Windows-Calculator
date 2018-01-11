package com.fiction.neosoft.calculator.components.fragment.standard;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fiction.neosoft.calculator.R;
import com.fiction.neosoft.calculator.base.BaseFragment;
import com.fiction.neosoft.calculator.components.fragment.history.HistoryFragment;
import com.fiction.neosoft.calculator.components.fragment.memory.MemoryFragment;
import com.fiction.neosoft.calculator.components.fragment.standard_keyboard.StandardKeyboardFragment;
import com.fiction.neosoft.calculator.helper.ExtendedDoubleEvaluator;
import com.fiction.neosoft.calculator.listners.StandardKeypadListner;
import com.fiction.neosoft.calculator.utils.Constants;
import com.fiction.neosoft.calculator.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.grantland.widget.AutofitTextView;

/**
 * Created by neosoft on 16/6/17.
 */

public class StandardCalculatorFragment extends BaseFragment implements StandardView, TextWatcher {

    private static final String TYPE = "STANDARD";
    protected StandardPresenter standardPresenter;
    protected StandardKeyboardFragment stdKeyFragment;

    @BindView(R.id.et_recent_cal)
    TextView mEditTop;
    @BindView(R.id.et_user_input)
    AutofitTextView mEditBottom;
    @BindView(R.id.horizontal_view)
    HorizontalScrollView hView;
    @BindView(R.id.iv_left)
    ImageView imageLeft;
    @BindView(R.id.iv_right)
    ImageView imageRight;

    @BindView(R.id.btn_mc)
    TextView btnMC;
    @BindView(R.id.btn_mr)
    TextView btnMR;
    @BindView(R.id.btn_mplus)
    TextView btnMplus;
    @BindView(R.id.btn_msub)
    TextView btnMsub;
    @BindView(R.id.btn_ms)
    TextView btnMS;
    @BindView(R.id.btn_m)
    TextView btnM;


    private int count = 0;
    private String expression = "";
    private String text = "";
    private Double result = 0.0;
    private boolean isResultSet;
    private MemoryFragment memFragment;

    @Override
    protected void initializePresenter() {
        standardPresenter = new StandardPresenter();
        super.presenter = standardPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_standard;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(view);
        setCalculationResult("0.0");
        mEditTop.addTextChangedListener(this);
        stdKeyFragment = new StandardKeyboardFragment();
        memFragment = new MemoryFragment();
        addFragment(R.id.fl_key_holder, stdKeyFragment, Constants.STD_KEYBOARD);
        initListner();
    }

    /**
     * Initialize Click Listner
     */
    private void initListner() {

        stdKeyFragment.initKeypadListner(new StandardKeypadListner() {
            @Override
            public void onNumberKeyClick(String num) {
                if (mEditBottom.getText().toString().equalsIgnoreCase("0")) {
                    setCalculationResult(num);
                    isResultSet = false;
                } else {
                    if (isResultSet) {
                        isResultSet = false;
                        mEditBottom.setText(num);
                    } else {
                        mEditBottom.append(num);
                    }
                }
            }

            @Override
            public void onSymbolKeyClick(String action) {
                operationClicked(action);
            }


            @Override
            public void onPressBackSpace() {
                onBackPressClick();
            }

            @Override
            public void onPressDot() {
                if (count == 0 && mEditBottom.length() != 0) {
                    mEditBottom.setText(mEditBottom.getText() + ".");
                    count++;
                }
            }

            @Override
            public void onPressClearAll() {
                mEditBottom.setText("0");
                count = 0;
            }

            @Override
            public void onPressModulo() {
                standardPresenter.calculateModulo(getTopStrings(), getBottomString());
            }

            @Override
            public void onPressOneX() {
                standardPresenter.calculateOnex(getTopStrings(), getBottomString());
            }

            @Override
            public void onPressClear() {
                count = 0;
                mEditTop.setText("");
                mEditBottom.setText("0");
                expression = "";
            }

            @Override
            public void onPressSqrt() {
                if (mEditBottom.length() != 0) {
                    text = mEditBottom.getText().toString();
                    mEditBottom.setText("sqrt(" + text + ")");
                }
            }

            @Override
            public void onPressSquare() {
                if (mEditBottom.length() != 0) {
                    text = mEditBottom.getText().toString();
                    mEditBottom.setText("(" + text + ")^2");
                }
            }

            @Override
            public void onPressPosneg() {
                if (mEditBottom.length() != 0) {
                    String s = mEditBottom.getText().toString();
                    char arr[] = s.toCharArray();
                    if (arr[0] == '-')
                        mEditBottom.setText(s.substring(1, s.length()));
                    else
                        mEditBottom.setText("-" + s);
                }
            }

            @Override
            public void onPressEqual() {
                String calculation = Utility.getFinalExpression(mEditTop.getText().toString() + mEditBottom.getText().toString());
                onPressEqualButton();
                if (!TextUtils.isEmpty(mEditTop.getText().toString()))
                    standardPresenter.storeCalculation(TYPE, calculation, mEditBottom.getText().toString());
            }

        });
    }

    @OnClick(R.id.btn_mc)
    public void onClickbtnMC() {
        standardPresenter.clickMemoryClear();
    }

    @OnClick(R.id.btn_mr)
    public void onClickbtnMR() {
        standardPresenter.clickMemoryRestore();
    }

    @OnClick(R.id.btn_mplus)
    public void onClickbtnMplus() {
        standardPresenter.clickMemoryPlus(getBottomString());
    }

    @OnClick(R.id.btn_msub)
    public void onClickbtnMsub() {
        standardPresenter.clickMemorySub();
    }

    @OnClick(R.id.btn_ms)
    public void onClickbtnMS() {
        standardPresenter.clickMemoryS();
    }

    @OnClick(R.id.btn_m)
    public void onClickbtnM() {
        standardPresenter.clickMemory();
        MemoryFragment memoryFragmentFragment = (MemoryFragment) getChildFragmentManager().findFragmentByTag("STD_MEMORY");
        StandardKeyboardFragment stdKeyboard = (StandardKeyboardFragment) getChildFragmentManager().findFragmentByTag("STD_KEYBOARD");
        if (memoryFragmentFragment == null || stdKeyboard.isVisible()) {
            replaceFragment(R.id.fl_key_holder, new MemoryFragment(), "STD_MEMORY");
        } else if (memoryFragmentFragment != null && memoryFragmentFragment.isVisible()) {
            replaceFragment(R.id.fl_key_holder, stdKeyFragment, "STD_KEYBOARD");
        }
    }

    public boolean isHistoryVisible() {
        StandardKeyboardFragment stdKeyboard = (StandardKeyboardFragment) getChildFragmentManager().findFragmentByTag("STD_KEYBOARD");
        if (!stdKeyboard.isVisible())
            return true;
        else
            return false;
    }

    /**
     * Get Calculation result and User input
     *
     * @return
     */
    private String getBottomString() {
        return mEditBottom.getText().toString().trim();
    }

    /**
     * get Calculation string
     *
     * @return
     */
    private String getTopStrings() {
        return mEditTop.getText().toString().trim();
    }

    /**
     * Set Calcation String
     *
     * @param cal
     */
    private void setCalculation(String cal) {
        mEditTop.setText(cal);
    }

    /**
     * set Calculation result
     *
     * @param result
     */
    private void setCalculationResult(String result) {
        isResultSet = true;
        mEditBottom.setText(Utility.getCalcuolationResult(result));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Method show history and keypad toggle
     */
    public void onPressHistory() {
        HistoryFragment historyFragment = (HistoryFragment) getChildFragmentManager().findFragmentByTag("STD_HISTORY");
        StandardKeyboardFragment stdKeyboard = (StandardKeyboardFragment) getChildFragmentManager().findFragmentByTag("STD_KEYBOARD");
        if (historyFragment == null || stdKeyboard.isVisible()) {
            replaceFragment(R.id.fl_key_holder, new HistoryFragment(), "STD_HISTORY");
        } else if (historyFragment != null && historyFragment.isVisible()) {
            replaceFragment(R.id.fl_key_holder, stdKeyFragment, "STD_KEYBOARD");
        }
    }

    /**
     * Method Perform Final Calculation
     */
    private void onPressEqualButton() {
        if (mEditBottom.length() != 0) {
            text = mEditBottom.getText().toString();
            expression = mEditTop.getText().toString() + text;
        } else {
            expression = Utility.getFinalExpression(expression);
        }
        mEditTop.setText("");
        if (expression.length() == 0)
            expression = "0.0";
        try {
            result = new ExtendedDoubleEvaluator().evaluate(expression);
            if (result.toString().endsWith(".0")) {
                mEditBottom.setText(String.format("%.0f", result));
            } else {
                mEditBottom.setText(result + "");
            }

        } catch (Exception e) {
            mEditBottom.setText(getString(R.string.invalide_expression));
            mEditTop.setText("");
            expression = "";
            e.printStackTrace();
        }
    }

    /**
     * On Back Pressed by user
     */
    private void onBackPressClick() {
        text = mEditBottom.getText().toString();
        standardPresenter.onBackPressEvent(text);
    }

    /**
     * Method perform math operation
     *
     * @param op
     */
    private void operationClicked(String op) {
        if (mEditBottom.length() != 0) {
            count = 0;
            setCalculation(getTopStrings() + " " + getBottomString() + " " + op);
            String evaluate = getTopStrings().replaceAll(" ", "");
            result = new ExtendedDoubleEvaluator().evaluate(Utility.getFinalExpression(evaluate));
            setCalculationResult(result.toString());

        } else {
            String text = mEditTop.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + op;
                mEditTop.setText(newText);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        hView.postDelayed(new Runnable() {
            @Override
            public void run() {
                hView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100);

        if (mEditTop.getText().length() > 35) {
            imageLeft.setVisibility(View.VISIBLE);
            imageRight.setVisibility(View.VISIBLE);
        } else {
            imageLeft.setVisibility(View.GONE);
            imageRight.setVisibility(View.GONE);
        }
    }

    @Override
    public void backPressPerform(String text, boolean isCountZero) {
        mEditBottom.setText(text);
        if (isCountZero) {
            count = 0;
        }
    }

    @Override
    public void setModuloResult(String result) {
        mEditTop.setText("");
        try {
            setCalculationResult(result);
        } catch (Exception e) {
            mEditBottom.setText(Constants.BAD_EXPRESSION);
        }

    }

    @Override
    public void setOneXResult(String result, String calculation) {
        mEditTop.setText(calculation);
        try {
            setCalculationResult(result);
        } catch (Exception e) {
            mEditBottom.setText(Constants.BAD_EXPRESSION);
        }
    }

    @Override
    public void showMemoryResult(String calResult) {
        setCalculationResult(calResult);
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getChildFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();

    }

    /**
     * Replace Current fragment with new one
     *
     * @param containerViewId
     * @param fragment
     * @param fragmentTag
     */
    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag) {
        if (fragment.equals(stdKeyFragment)) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(containerViewId, fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_up, 0)
                    .replace(containerViewId, fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
        }

    }
}
