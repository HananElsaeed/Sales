package com.hananelsaid.hp.salesapp.DetailsPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hananelsaid.hp.salesapp.R;

public class Details_Activity extends AppCompatActivity {
    TextView name, sellingPrice_tv, buyingPrice_tv, day_tv, month_tv,profit_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);

        Intent intent = getIntent();
        String theName = intent.getStringExtra("name");
        double ItemSellingPrice = intent.getDoubleExtra("ItemSellingPrice", 0.00);
        double BuyingPrice = intent.getDoubleExtra("BuyingPrice", 0.00);
        String day = intent.getStringExtra("day");
        String month = intent.getStringExtra("month");
        name = findViewById(R.id.name);
        name.setText(theName);
        sellingPrice_tv = findViewById(R.id.selling_priceid);
        sellingPrice_tv.setText( ItemSellingPrice+"");
        buyingPrice_tv = findViewById(R.id.buying_priceID);
        buyingPrice_tv.setText(BuyingPrice+"");
        day_tv = findViewById(R.id.day);
        day_tv.setText(day);
        month_tv = findViewById(R.id.month);
        month_tv.setText(month);
        profit_tv=findViewById(R.id.profit);
        profit_tv.setText(ItemSellingPrice-BuyingPrice +"");


    }
}
