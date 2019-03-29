package com.hananelsaid.hp.salesapp.SalesPackage.SalesPresenter;

import android.util.Log;

import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.Contract;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.SalesModel;

import static android.content.ContentValues.TAG;

public class SalesPresenter implements Contract.IPresenterModel, Contract.IPresenterView {
    private Contract.IView view;
    private Contract.IModel model;
    Item item;

    public SalesPresenter(Contract.IView view) {
        this.view=view;
        this.model= new SalesModel(this);

    }

    @Override
    public void catchdata(String productName, double sellingPrice, double buyingPrice,String day ,String month) {
        item= new Item(productName,sellingPrice,buyingPrice,day,month);
        model.addItem(item);
        Log.i("catchdata", "catchdata: ");


    }
}
