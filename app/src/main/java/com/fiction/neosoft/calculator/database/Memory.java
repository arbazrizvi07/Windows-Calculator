package com.fiction.neosoft.calculator.database;

import io.realm.RealmObject;

/**
 * Created by neosoft on 21/6/17.
 */

public class Memory extends RealmObject {
    public String calType;
    public String calResult;
    public String firstValue;

    public String getCalType() {
        return calType;
    }

    public void setCalType(String calType) {
        this.calType = calType;
    }

    public String getCalResult() {
        return calResult;
    }

    public void setCalResult(String calResult) {
        this.calResult = calResult;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }
}
