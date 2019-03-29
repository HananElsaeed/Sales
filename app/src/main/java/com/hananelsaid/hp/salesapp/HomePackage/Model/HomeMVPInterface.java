package com.hananelsaid.hp.salesapp.HomePackage.Model;

import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;

import java.util.List;

public interface HomeMVPInterface {
    interface HomeView {
        void setRecycler(List<Item> items);
    }

    interface HomePresenetView {void getItemsList();
        void deletItem(int position);
        double getdailyProfit();
        double getMonthlyProfit();
    }

    interface HomePresenterModel {
        void onItemsLoaded(List<Item> items);
    }
}
