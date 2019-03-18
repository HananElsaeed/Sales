package com.hananelsaid.hp.salesapp.HomePackage.Presenter;

import android.arch.lifecycle.LiveData;

import com.hananelsaid.hp.salesapp.HomePackage.Model.HomeMVPInterface;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.Contract;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.SalesModel;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeMVPInterface.HomePresenetView, HomeMVPInterface.HomePresenterModel {
    HomeMVPInterface.HomeView mView;
    Contract.IModel mModel;

    public HomePresenter(HomeMVPInterface.HomeView mView) {
        this.mView=mView;
        mModel=new SalesModel(this);
    }


    @Override
    public void getItemsList() {
        //List<Item> itemlist= new ArrayList<>();
        List<Item> itemlist = mModel.getItems();
        mView.setRecycler(itemlist);

    }
}

