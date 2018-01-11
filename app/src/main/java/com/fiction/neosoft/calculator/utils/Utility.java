package com.fiction.neosoft.calculator.utils;

/**
 * Created by neosoft on 21/6/17.
 */

public class Utility {

    public static String getFinalExpression(String text) {
        int strLength = text.length();
        if (strLength > 0) {
            char lastChar = text.charAt(strLength - 1);
            if (Character.isDigit(lastChar)) {
                return text;
            } else {
                return text.substring(0, strLength - 1);
            }
        }
        return "";
    }

    public static String getCalcuolationResult(String value) {
        if (value.endsWith(".0")) {
            return value.replace(".0", "");
        } else {
            return value;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
