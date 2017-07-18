package com.example.neosoft.calculator.database;

import io.realm.RealmObject;

/**
 * Created by neosoft on 21/6/17.
 */

public class History extends RealmObject {
    public String calType;
    public String calString;
    public String calResult;

    public String getCalType() {
        return calType;
    }

    public void setCalType(String calType) {
        this.calType = calType;
    }

    public String getCalString() {
        return calString;
    }

    public void setCalString(String calString) {
        this.calString = calString;
    }

    public String getCalResult() {
        return calResult;
    }

    public void setCalResult(String calResult) {
        this.calResult = calResult;
    }
}
