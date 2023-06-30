package com.example.apptrangsuc.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptrangsuc.R;
import com.example.apptrangsuc.activity.CartActivity;
import com.example.apptrangsuc.sever.SERVER;
import com.example.apptrangsuc.model.SanPham;
import com.example.apptrangsuc.sqlite.productsDAO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartViewholder> {
    Context context;
    ArrayList<SanPham> data;
    productsDAO productsDAO;

    public CartAdapter(Context context, ArrayList<SanPham> data){
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.cartViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_cart,parent,false);
        return new cartViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.cartViewholder holder, int position) {
        productsDAO = new productsDAO(context.getApplicationContext());
        SanPham sp = data.get(position);
        holder.cart_name.setText(sp.getTensanpham());
        holder.cart_price.setText(sp.getGiasanpham()+"");
        holder.cart_quantity.setText(sp.getSoluong()+"");
        Picasso.get().load(SERVER.imagepath+ sp.getHinhsanpham()).into(holder.cart_img);

        holder.cart_delelte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifications(sp);
            }
        });

        holder.cart_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.setSoluong(sp.getSoluong() + 1);
                holder.cart_quantity.setText(String.valueOf(sp.getSoluong()));
                updateTotal();
                // cập nhật số lượng
                productsDAO.updateProductQuantityById(sp.getIdsanpham(), sp.getSoluong());
            }
        });

        holder.cart_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getSoluong() > 1) {
                    sp.setSoluong(sp.getSoluong() - 1);
                    holder.cart_quantity.setText(String.valueOf(sp.getSoluong()));
                    updateTotal();
                    // cập nhật số lượng
                    productsDAO.updateProductQuantityById(sp.getIdsanpham(), sp.getSoluong());
                }
            }
        });


        updateTotal();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class cartViewholder extends RecyclerView.ViewHolder{
        ImageView cart_img;
        TextView cart_name ,cart_price;
        Button cart_delelte;
        TextView cart_quantity, cart_min, cart_max, cart_total;

        public cartViewholder(@NonNull View itemView) {
            super(itemView);
            // ánh xạ
            cart_img = itemView.findViewById(R.id.cart_img);
            cart_name = itemView.findViewById(R.id.cart_name);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_min = itemView.findViewById(R.id.cart_min);
            cart_quantity = itemView.findViewById(R.id.cart_quantity);
            cart_max = itemView.findViewById(R.id.cart_max);
            cart_delelte = itemView.findViewById(R.id.cart_delete);
            cart_total = itemView.findViewById(R.id.cart_total);

        }

    }
    void notifications(SanPham sp){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Bạn có chắc muốn xóa sản phẩm này? ");
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                productsDAO productsDAO = new productsDAO(context);
                productsDAO.deleteProductById(sp.getIdsanpham());
                data.remove(sp);
                notifyDataSetChanged();
                updateTotal();

                Toast.makeText(context,"Xóa sản phẩm thành công",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    private void  updateTotal(){
        double total = 0;
        for (SanPham sp: data){
            total += sp.getGiasanpham() * sp.getSoluong();
        }
        ((CartActivity) context).updateTotal(total);
    }
}