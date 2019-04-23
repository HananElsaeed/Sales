package com.hananelsaid.hp.salesapp.SalesPackage.SalesView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hananelsaid.hp.salesapp.HomePackage.View.MainActivity;
import com.hananelsaid.hp.salesapp.R;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.Contract;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.MySingleton;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesPresenter.SalesPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        MainActivity.fa.finish();
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
                    String day = getTheDay();
                    String month = getTheMonth();
                    mPresenterView.catchdata(productName, sellingPrice, buyingPrice, day, month);
                    Toast.makeText(Sales_Activity.this, "تم الاضافه",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sales_Activity.this,MainActivity.class);
                    startActivity(intent);
                    Sales_Activity.this.finish();

                } else
                    Toast.makeText(Sales_Activity.this, "من فضلك ادخل جميع بيانات المنتج",
                            Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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
