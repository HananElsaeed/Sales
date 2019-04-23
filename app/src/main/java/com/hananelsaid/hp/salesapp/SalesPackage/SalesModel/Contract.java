package com.hananelsaid.hp.salesapp.SalesPackage.SalesModel;

import android.arch.lifecycle.LiveData;

import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;

import java.util.List;

public interface Contract {
    public interface IView {

    }

    public interface IPresenterView {
        void catchdata(String productName, double sellingPrice, double buyingPrice, String day, String month);
    }

    public interface IModel {
        void addItem(Item item);

        void deleteItem(Item item);

        void getItems();

        void update(Item item);
    }

    public interface IPresenterModel {

    }


}
