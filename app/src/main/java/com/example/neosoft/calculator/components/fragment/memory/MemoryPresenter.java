package com.example.neosoft.calculator.components.fragment.memory;

import com.example.neosoft.calculator.base.listeners.BasePresenter;
import com.example.neosoft.calculator.database.History;
import com.example.neosoft.calculator.utils.Constants;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by neosoft on 20/6/17.
 */

public class MemoryPresenter extends BasePresenter<MemoryView> {

    Realm realm = Realm.getDefaultInstance();
    private ArrayList<History> historyArrayList = new ArrayList<>();

    public void getHistories() {
        RealmQuery<History> query = realm.where(History.class);
        query.equalTo("calType", Constants.STANDARD_TYPE);
        RealmResults<History> result = query.findAll();
        if (result.size() > 0) {
            historyArrayList.addAll(result);
            getView().setHistoryList(historyArrayList);
        } else {
            getView().showEmptyMessage();
        }
    }

    public void deleteHistories() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<History> result = realm.where(History.class).equalTo("calType", Constants.STANDARD_TYPE).findAll();
                if (result.deleteAllFromRealm()) {
                    getView().showEmptyMessage();
                }
            }
        });

    }
}
