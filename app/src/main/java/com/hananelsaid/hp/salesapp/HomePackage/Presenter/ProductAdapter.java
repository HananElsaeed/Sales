package com.hananelsaid.hp.salesapp.HomePackage.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hananelsaid.hp.salesapp.DetailsPackage.Details_Activity;
import com.hananelsaid.hp.salesapp.R;
import com.hananelsaid.hp.salesapp.SalesPackage.SalesModel.DatabasePackage.Item;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    private List<Item> items;
    private Context context;
    private Item item;
    private final OnItemClickListener listener;

    public ProductAdapter(List<Item> items, Context context ,OnItemClickListener listener)
     {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_item, viewGroup, false);
        ProductHolder holder = new ProductHolder(row);
      /*  holder.constraintLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Details_Activity.class);
                intent.putExtra("name", item.getItemName());
                Log.i(TAG, "onClick: " + item.getItemName());
                intent.putExtra("ItemSellingPrice", item.getItemSellingPrice());
                intent.putExtra("BuyingPrice", item.getItemBuyingPrice());
                intent.putExtra("day", item.getItemDay());
                intent.putExtra("month", item.getItemMonth());
                context.startActivity(intent);

            }
        }); */

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder productHolder, int i) {
        productHolder.bind(items.get(i), listener);
        item = items.get(i);
        Log.i(TAG, "onBindViewHolder: " + i);
        productHolder.item_name_tv.setText(item.getItemName());
        productHolder.item_selling_price_tv.setText(item.getItemSellingPrice() + "");
        productHolder.item_buying_price_tv.setText(item.getItemBuyingPrice() + "");
        Log.i(TAG, "onClick: " + item.getItemName());


    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + items.size());
        return items.size();

    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView item_name_tv, item_selling_price_tv, item_buying_price_tv;
        ConstraintLayout constraintLayout;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            item_name_tv = itemView.findViewById(R.id.item_name_tv);
            item_selling_price_tv = itemView.findViewById(R.id.item_selling_price_tv);
            item_buying_price_tv = itemView.findViewById(R.id.item_buying_price_tv);
          //  constraintLayout = itemView.findViewById(R.id.rawId);
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
