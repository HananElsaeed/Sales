package com.hananelsaid.hp.salesapp.HomePackage.Model;

import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;

import java.util.List;

public interface HomeMVPInterface {
    public interface HomeView{void setRecycler(List<Item> items);}
    public interface HomePresenetView{void getItemsList();}
    public interface HomePresenterModel{ }
}
