package com.hananelsaid.hp.salesapp.SalesPackage.SalesModel;

import android.os.AsyncTask;
import android.util.Log;

import com.hananelsaid.hp.salesapp.HomePackage.Model.HomeMVPInterface;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.ItemDatabase;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.MySingleton;

import java.util.List;

import static android.content.ContentValues.TAG;

public class SalesModel implements Contract.IModel {
    private Contract.IPresenterModel presenterModel;
    HomeMVPInterface.HomePresenterModel mHomePresenter;
    private ItemDatabase itemDB;
    // operations constants
    private static final int INSERT_OPERATION = 5;
    private static final int DELETE_OPERATION = 1;
    private static final int UPDATE_OPERATION = 2;
    private static final int QUERY_OPERATION = 7;

    public SalesModel(HomeMVPInterface.HomePresenterModel mHomePresenter) {
        this.mHomePresenter = mHomePresenter;
        initDB();
    }

    public SalesModel(Contract.IPresenterModel presenterModel) {
        this.presenterModel = presenterModel;
        initDB();
    }

    @Override
    public void addItem(Item item) {
        new DataBaseOperation(item, INSERT_OPERATION).execute();
    }

    @Override
    public void deleteItem(Item item) {
        new DataBaseOperation(item, DELETE_OPERATION).execute();
    }

    @Override
    public void getItems() {
        new DataBaseOperation(QUERY_OPERATION).execute();
    }

    private void initDB() {
        Log.e(TAG, "_DataBaseInit");
        itemDB = MySingleton.getInstance().getDB();
    }

    // Async
    class DataBaseOperation extends AsyncTask<Void, Void, Void> {
        public Item item;
        public int operation;


        public DataBaseOperation(Item item, int operation) {
            this.item = item;
            this.operation = operation;
        }

        public DataBaseOperation(int operation) {
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e(TAG, "_doInBackgroundInvoked");
            // INSERT operation
            if (operation == INSERT_OPERATION) itemDB.itemDao().insert(item);
            else if (operation == DELETE_OPERATION) itemDB.itemDao().delete(item);
            else if (operation == UPDATE_OPERATION) itemDB.itemDao().updateUser(item);
            else if (operation == QUERY_OPERATION) { mHomePresenter.onItemsLoaded(itemDB.itemDao().getItems());
            }
            return null;
        }
    }
}
