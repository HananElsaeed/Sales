package com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.SalesModel;

public class MySingleton {
    private static MySingleton mInstance;
    private ItemDatabase itemDB;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        itemDB = getDB();
    }

    public static synchronized MySingleton initializeDB(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public static MySingleton getInstance() {
        return mInstance;
    }

    public ItemDatabase getDB() {
        if (itemDB == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            itemDB = Room.databaseBuilder(mCtx,
                    ItemDatabase.class, "ItemDatabase").fallbackToDestructiveMigration().build();
        }
        return itemDB;
    }
}
