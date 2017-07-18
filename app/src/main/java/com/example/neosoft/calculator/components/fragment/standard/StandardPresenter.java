package com.example.neosoft.calculator.components.fragment.standard;

import android.util.Log;

import com.example.neosoft.calculator.base.listeners.BasePresenter;
import com.example.neosoft.calculator.database.History;
import com.example.neosoft.calculator.database.Memory;
import com.example.neosoft.calculator.helper.ExtendedDoubleEvaluator;
import com.example.neosoft.calculator.utils.Constants;
import com.example.neosoft.calculator.utils.Utility;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by neosoft on 16/6/17.
 */

public class StandardPresenter extends BasePresenter<StandardView> {

    private Realm realm = Realm.getDefaultInstance();
    private ExtendedDoubleEvaluator doubleEvaluator = new ExtendedDoubleEvaluator();

    boolean isCountZero;

    public void onBackPressEvent(String text) {
        if (text.length() > 0) {
            if (text.equalsIgnoreCase("Bad Expression")) {
                getView().backPressPerform("", true);
                return;
            }

            if (text.endsWith(".")) {
                isCountZero = true;
            }

            String newText = text.substring(0, text.length() - 1);
            //to delete the data contained in the brackets at once
            if (text.endsWith(")")) {
                char[] a = text.toCharArray();
                int pos = a.length - 2;
                int counter = 1;
                //to find the opening bracket position
                for (int i = a.length - 2; i >= 0; i--) {
                    if (a[i] == ')') {
                        counter++;
                    } else if (a[i] == '(') {
                        counter--;
                    }
                    //if decimal is deleted b/w brackets then count should be zero
                    else if (a[i] == '.') {
                        isCountZero = true;
                    }
                    //if opening bracket pair for the last bracket is found
                    if (counter == 0) {
                        pos = i;
                        break;
                    }
                }
                newText = text.substring(0, pos);
            }
            //if mEditBottom edit text contains only - sign or sqrt at last then clear the edit text mEditBottom
            if (newText.equals("-") || newText.endsWith("sqrt")) {
                newText = "";
            }
            //if pow sign is left at the last
            else if (newText.endsWith("^"))
                newText = newText.substring(0, newText.length() - 1);

            getView().backPressPerform(newText, isCountZero);
        }
    }

    public void storeCalculation(String type, String calString, String calResult) {
        History history = new History();
        history.setCalType(type);
        history.setCalString(calString);
        history.setCalResult(calResult);

        realm.beginTransaction();
        realm.copyToRealm(history);
        realm.commitTransaction();
    }

    public void calculateModulo(String topStrings, String bottomString) {
        if (bottomString.length() > 0 && topStrings.length() > 0) {
            String expression = Utility.getFinalExpression(topStrings);
            double result = doubleEvaluator.evaluate(expression);
            result = ((result / 100) * Double.valueOf(bottomString));
            getView().setModuloResult(Utility.getCalcuolationResult(result));
        } else {
            getView().setModuloResult(Constants.BAD_EXPRESSION);
        }
    }

    public void calculateOnex(String topStrings, String bottomString) {
        if (bottomString.length() > 0) {
            String expression = "1/(" + bottomString + ")";
            double result = doubleEvaluator.evaluate(expression);
            String calculation = topStrings + expression;
            getView().setOneXResult(Utility.getCalcuolationResult(result), calculation);
        } else {
        }
    }

    public void clickMemoryClear() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Memory> result = realm.where(Memory.class).equalTo("calType", Constants.STANDARD_TYPE).findAll();
                if (result.deleteAllFromRealm()) {
                }
            }
        });
    }

    public void clickMemoryRestore() {
        RealmQuery<Memory> query = realm.where(Memory.class);
        query.equalTo("calType", Constants.STANDARD_TYPE);
        Memory result = query.findFirst();
        if (result != null) {
            Log.d("resultcal", result.getCalResult());
            getView().showMemoryResult(result.getCalResult());
        } else {
            Log.d("resultcal", "result is empty");
        }
    }

    public void clickMemoryPlus(String value) {

        RealmQuery<Memory> query = realm.where(Memory.class);
        query.equalTo("calType", Constants.STANDARD_TYPE);
        Memory result = query.findFirst();
        if (result != null && result.getFirstValue() != null) {
            double val = Double.parseDouble(result.getCalResult()) + Double.parseDouble(result.getFirstValue());
            realm.beginTransaction();
            result.setCalResult(String.valueOf(Utility.getCalcuolationResult(val)));
            realm.commitTransaction();
        } else {
            realm.beginTransaction();
            Memory memory = realm.createObject(Memory.class);
            ;
            memory.setCalResult(value);
            memory.setFirstValue(value);
            memory.setCalType(Constants.STANDARD_TYPE);
            realm.commitTransaction();
        }
    }

    public void clickMemoryS() {

    }

    public void clickMemorySub() {
        RealmQuery<Memory> query = realm.where(Memory.class);
        query.equalTo("calType", Constants.STANDARD_TYPE);
        Memory result = query.findFirst();
        if (result != null && result.getFirstValue() != null) {
            double val = Double.parseDouble(result.getCalResult()) - Double.parseDouble(result.getFirstValue());
            realm.beginTransaction();
            result.setCalResult(String.valueOf(Utility.getCalcuolationResult(val)));
            realm.commitTransaction();
        }
    }

    public void clickMemory() {
    }
}
