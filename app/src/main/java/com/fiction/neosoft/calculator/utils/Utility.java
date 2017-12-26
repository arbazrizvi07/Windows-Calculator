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

    public static String getCalcuolationResult(double value) {
        String val = String.valueOf(value);
        int strLen = String.valueOf(value).length();
        if (val.charAt(strLen - 1) == '0' && val.charAt(strLen - 2) == '.') {
            return Double.valueOf(val).intValue() + "";
        } else {
            return val;
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
