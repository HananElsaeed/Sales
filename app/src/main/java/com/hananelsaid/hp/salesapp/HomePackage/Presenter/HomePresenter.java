package com.hananelsaid.hp.salesapp.HomePackage.Presenter;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.hananelsaid.hp.salesapp.HomePackage.Model.HomeMVPInterface;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.Contract;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.SalesModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomePresenter implements HomeMVPInterface.HomePresenetView, HomeMVPInterface.HomePresenterModel {
    HomeMVPInterface.HomeView mView;
    Contract.IModel mModel;
    private int position;
    private Item item;
    private Item lastItem;
    List<Item> items;

    public HomePresenter(HomeMVPInterface.HomeView mView) {
        this.mView = mView;
        mModel = new SalesModel(this);
    }

    @Override
    public void onItemsLoaded(List<Item> items) {
        if (items.size() > 0) {
            mView.setRecycler(items);
            item = items.get(position);
            lastItem = items.get(items.size()-1);
            this.items = items;
        }
    }

    @Override
    public void getItemsList() {
        mModel.getItems();
    }

    public void deletItem(int position) {
        if (position >= 0 && item != null) {
            mModel.deleteItem(item);
            items.remove(position);
        }
    }

    @Override
    public void updateDB() {
        if (item != null) {
            mModel.update(lastItem);
        }
    }

    @Override
    public double getdailyProfit() {
        double totalProfit = 0;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            double itemSellingPrice = item.getItemSellingPrice();
            double itemBuyingPrice = item.getItemBuyingPrice();
            double profit = itemSellingPrice - itemBuyingPrice;
            String day = item.getItemDay();
            String currentDay = getTheDay();
            if (day.equals(currentDay)) {
                totalProfit = totalProfit + profit;
            }
        }
        return totalProfit;
    }

    @Override
    public double getMonthlyProfit() {
        double totalMonthProfit = 0;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            double itemSellingPrice = item.getItemSellingPrice();
            double itemBuyingPrice = item.getItemBuyingPrice();
            double profit = itemSellingPrice - itemBuyingPrice;
            String month = item.getItemMonth();
            Log.i(TAG, "getMonthlyProfit: " + month);
            String currentMonth = getTheMonth();
            Log.i(TAG, "getMonthlyProfit: " + currentMonth);
            if (month.equals(currentMonth)) {
                totalMonthProfit += profit;
                Log.i(TAG, "getMonthlyProfit: " + profit);
            }
        }
        return totalMonthProfit;
    }

    public String getdate() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateString = formatter.format(currentDate);
        return formattedDateString;
    }

    public String getTheDay() {
        String input_date = getdate();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1 = null;
        try {
            dt1 = format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("dd");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public String getTheMonth() {
        String input_date = getdate();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1 = null;
        try {
            dt1 = format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("MM");
        String finalMonth = format2.format(dt1);
        return finalMonth;
    }
}

