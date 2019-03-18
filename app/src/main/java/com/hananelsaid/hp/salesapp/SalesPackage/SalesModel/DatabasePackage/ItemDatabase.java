package com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.MainThread;

@Database(entities ={Item.class} ,version = 3)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

}
