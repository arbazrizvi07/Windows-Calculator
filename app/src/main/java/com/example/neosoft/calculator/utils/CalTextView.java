package com.example.neosoft.calculator.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by neosoft on 16/3/17.
 */

public class CalTextView extends android.support.v7.widget.AppCompatTextView {

    public CalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.DEFAULT;

            switch (getTypeface().getStyle()) {
                case Typeface.BOLD:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
                    break;
                case Typeface.NORMAL:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
                    break;
                default:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
                    break;
            }

            setTypeface(tf);
        }
    }

}
