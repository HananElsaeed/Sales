package com.hananelsaid.hp.salesapp.HomePackage.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hananelsaid.hp.salesapp.R;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private List<Item> items;
    private Context context;

    public ProductAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_item, viewGroup, false);
        ProductHolder holder = new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder productHolder, int i) {

        Item item = items.get(i);
        productHolder.item_name_tv.setText(item.getItemName());
        productHolder.item_selling_price_tv.setText(item.getItemSellingPrice() + "");
        productHolder.item_buying_price_tv.setText(item.getItemBuyingPrice() + "");

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: "+items.size());
        return items.size();

    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView item_name_tv, item_selling_price_tv, item_buying_price_tv;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            item_name_tv = itemView.findViewById(R.id.item_name_tv);
            item_selling_price_tv = itemView.findViewById(R.id.item_selling_price_tv);
            item_buying_price_tv = itemView.findViewById(R.id.item_buying_price_tv);
        }
    }
}
