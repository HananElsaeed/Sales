package com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Database;
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
    @ColumnInfo(name = "item_time")
    private long itemTime;

    public Item() {
    }

    public long getItemTime() {
        return itemTime;
    }

    public void setItemTime(long itemTime) {
        this.itemTime = itemTime;
    }


    public Item(String itemName, double itemSellingPrice, double itemBuyingPrice, long itemTime) {
        this.itemName = itemName;
        this.itemSellingPrice = itemSellingPrice;
        this.itemBuyingPrice = itemBuyingPrice;
        this.itemTime = itemTime;
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
