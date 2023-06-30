package com.example.apptrangsuc.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptrangsuc.R;
import com.example.apptrangsuc.activity.ProductByCategoriesActivity;
import com.example.apptrangsuc.sever.SERVER;
import com.example.apptrangsuc.model.ChuDe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChuDeAdapter extends RecyclerView.Adapter<KHUNGNHIN_CHUDE> {
    ArrayList<ChuDe> data;
    Context context;

    public ChuDeAdapter(ArrayList<ChuDe> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public KHUNGNHIN_CHUDE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_chude, parent, false);
        return new KHUNGNHIN_CHUDE(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN_CHUDE holder, int position) {
        ChuDe cd = data.get(position);
        // chua gan du lieu cho HINH
        holder.tvTenChude.setText(cd.getTenchude());
        // Gắn dữ liệu hình chủ đề
        Picasso.get().load(SERVER.imagepath + cd.hinhchude).into(holder.imgChude);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductByCategoriesActivity.class);
                intent.putExtra("idchude", cd.getIdchude());
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNGNHIN_CHUDE extends RecyclerView.ViewHolder {
    ImageView imgChude;
    TextView tvTenChude;

    public KHUNGNHIN_CHUDE(@NonNull View itemView) {
        super(itemView);
        imgChude = itemView.findViewById(R.id.imgChude);
        tvTenChude = itemView.findViewById(R.id.tvChude);
    }
}


