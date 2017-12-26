package com.fiction.neosoft.calculator.components.fragment.memory;

import com.fiction.neosoft.calculator.base.listeners.BasePresenter;
import com.fiction.neosoft.calculator.database.Memory;
import com.fiction.neosoft.calculator.utils.Constants;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by neosoft on 20/6/17.
 */

public class MemoryPresenter extends BasePresenter<MemoryView> {

    Realm realm = Realm.getDefaultInstance();
    private ArrayList<Memory> memoryList = new ArrayList<>();

    public void getHistories() {
        RealmQuery<Memory> query = realm.where(Memory.class);
        query.equalTo("calType", Constants.STANDARD_TYPE);
        RealmResults<Memory> result = query.findAll();
        if (result.size() > 0) {
            memoryList.addAll(result);
            getView().setHistoryList(memoryList);
        } else {
            getView().showEmptyMessage();
        }
    }

    public void deleteHistories() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Memory> result = realm.where(Memory.class).equalTo("calType", Constants.STANDARD_TYPE).findAll();
                if (result.deleteAllFromRealm()) {
                    getView().showEmptyMessage();
                }
            }
        });

    }
}
