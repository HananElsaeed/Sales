package com.hananelsaid.hp.salesapp.SalesPackage.SalesView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hananelsaid.hp.salesapp.R;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.Contract;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.MySingleton;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesPresenter.SalesPresenter;

public class Sales_Activity extends AppCompatActivity implements Contract.IView {
    private SalesPresenter mPresenter;
    private Contract.IPresenterView mPresenterView;

    EditText mProductName, mSellingPrice, mBuyingPrice;
    Button add_btn;
    private String productName;
    private double sellingPrice;
    private double buyingPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_);
        //singleton inti
        MySingleton.initializeDB(getApplicationContext());
        //presenter instance initialization
        mPresenter = new SalesPresenter(this);
        mPresenterView = new SalesPresenter(this);
        // views objects
        mProductName = findViewById(R.id.productname_et);
        mSellingPrice = findViewById(R.id.saleprice_et);
        mBuyingPrice = findViewById(R.id.buyingprice_et);
        add_btn = findViewById(R.id.add_btn);


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = mProductName.getText().toString();
                String sellingPriceStr = mSellingPrice.getText().toString();
                String buyingPriceStr = mBuyingPrice.getText().toString();
                if (!TextUtils.isEmpty(productName) && !TextUtils.isEmpty(sellingPriceStr)
                        && !TextUtils.isEmpty(buyingPriceStr)) {
                    sellingPrice = Double.parseDouble(sellingPriceStr);
                    buyingPrice = Double.parseDouble(buyingPriceStr);
                    long time = System.currentTimeMillis();
                    mPresenterView.catchdata(productName, sellingPrice, buyingPrice, time);
                } else
                    Toast.makeText(Sales_Activity.this, "من فضلك ادخل جميع بيانات المنتج", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
