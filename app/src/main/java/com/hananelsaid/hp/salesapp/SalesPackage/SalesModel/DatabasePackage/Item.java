package com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "products")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "item_name")
    private String itemName;
    @ColumnInfo(name = "item_SellingPrice")
    private double itemSellingPrice;
    @ColumnInfo(name = "item_BuyingPrice")
    private double itemBuyingPrice;
    @ColumnInfo(name = "item_day")
    private String itemDay;
    @ColumnInfo(name = "item_month")
    private String itemMonth;

    public Item() {
    }

    public String getItemDay() {
        return itemDay;
    }

    public void setItemDay(String itemDay) {
        this.itemDay = itemDay;
    }

    public String getItemMonth() {
        return itemMonth;
    }

    public void setItemMonth(String itemMonth) {
        this.itemMonth = itemMonth;
    }

    public Item(String itemName, double itemSellingPrice, double itemBuyingPrice, String itemDay, String itemMonth) {
        this.itemName = itemName;
        this.itemSellingPrice = itemSellingPrice;
        this.itemBuyingPrice = itemBuyingPrice;
        this.itemDay = itemDay;
        this.itemMonth = itemMonth;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemSellingPrice() {
        return itemSellingPrice;
    }

    public void setItemSellingPrice(double itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }

    public double getItemBuyingPrice() {
        return itemBuyingPrice;
    }

    public void setItemBuyingPrice(double itemBuyingPrice) {
        this.itemBuyingPrice = itemBuyingPrice;
    }
}
