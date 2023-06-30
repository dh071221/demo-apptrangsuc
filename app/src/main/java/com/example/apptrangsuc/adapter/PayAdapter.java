package com.example.apptrangsuc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptrangsuc.R;
import com.example.apptrangsuc.model.SanPham;
import com.example.apptrangsuc.sqlite.productsDAO;
import com.example.apptrangsuc.sever.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PAY_ADAPTER_ViewHolder>{

    Context context;
    ArrayList<SanPham> data;
    productsDAO productsDAO;

    public PayAdapter(Context context, ArrayList<SanPham> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PAY_ADAPTER_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cartproducts, parent, false);
        return new PAY_ADAPTER_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PAY_ADAPTER_ViewHolder holder, int position) {
        productsDAO = new productsDAO(context.getApplicationContext());
        SanPham sp = data.get(position);
        holder.cart_name.setText(sp.getTensanpham());
        holder.cart_price.setText(sp.getGiasanpham()+"");
        holder.cart_quantity.setText(sp.getSoluong() + "");
        Picasso.get().load(SERVER.imagepath + sp.getHinhsanpham()).into(holder.cart_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PAY_ADAPTER_ViewHolder extends RecyclerView.ViewHolder{

        ImageView cart_img;
        TextView cart_name, cart_price, cart_quantity;

        public PAY_ADAPTER_ViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_img = itemView.findViewById(R.id.cart_img);
            cart_name = itemView.findViewById(R.id.cart_name);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_quantity = itemView.findViewById(R.id.cart_quantity);
        }
    }
}