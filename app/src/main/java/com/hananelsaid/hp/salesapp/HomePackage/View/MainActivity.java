package com.hananelsaid.hp.salesapp.HomePackage.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hananelsaid.hp.salesapp.DetailsPackage.Details_Activity;
import com.hananelsaid.hp.salesapp.HomePackage.Model.HomeMVPInterface;
import com.hananelsaid.hp.salesapp.HomePackage.Presenter.HomePresenter;
import com.hananelsaid.hp.salesapp.HomePackage.Presenter.ProductAdapter;
import com.hananelsaid.hp.salesapp.R;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.MySingleton;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesView.Sales_Activity;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeMVPInterface.HomeView {

    private RecyclerView recyclerView;
    HomePresenter homePresenter;
    HomeMVPInterface.HomePresenetView mpresenter;
    private ProductAdapter productAdapter;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("المبيعات");
        fa=this;
        //singleton inti
        MySingleton.initializeDB(getApplicationContext());
        //init the presenter
        homePresenter = new HomePresenter(this);
        mpresenter = new HomePresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //recyclerview
        recyclerView = findViewById(R.id.rv);
        mpresenter.getItemsList();

        //delet item by swipping
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView
                    .ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("هل تريد مسح المنتج بالفعل")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mpresenter.deletItem(position);
                                productAdapter.notifyDataSetChanged();
                            }
                        })
                        .show();

            }
        });
        helper.attachToRecyclerView(recyclerView);
    }


    @Override
    protected void onResume() {
        super.onResume();
       // mpresenter.getItemsList();
      //  mpresenter.updateDB();
        //if (productAdapter!= null)
          //  productAdapter.notifyDataSetChanged();
    }



    @Override
    public void setRecycler(List<Item> items) {
        productAdapter = new ProductAdapter(items, this, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Intent intent = new Intent(MainActivity.this, Details_Activity.class);
                intent.putExtra("name", item.getItemName());
                Log.i(TAG, "onClick: " + item.getItemName());
                intent.putExtra("ItemSellingPrice", item.getItemSellingPrice());
                intent.putExtra("BuyingPrice", item.getItemBuyingPrice());
                intent.putExtra("day", item.getItemDay());
                intent.putExtra("month", item.getItemMonth());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_productsale) {
            Intent intent = new Intent(MainActivity.this, Sales_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dailyearnings) {
            double profit = mpresenter.getdailyProfit();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("ربح اليوم")
                    .setMessage("ربح اليوم =" + profit)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        } else if (id == R.id.nav_monthearnings) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("ربح الشهر")
                    .setMessage("ربح الشهر=" + mpresenter.getMonthlyProfit())
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
