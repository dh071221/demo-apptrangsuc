package com.example.apptrangsuc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apptrangsuc.R;
import com.example.apptrangsuc.SERVER;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarchitiet;
    ImageView imgchitiet;
    TextView tvtensp,tvgiasp,tvmotasp;
    Button btnmua;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();

        //nhận dữ liệu của sản phẩm
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String img = intent.getStringExtra("img");
        String mota = intent.getStringExtra("mota");
        tvtensp.setText(name);
        tvgiasp.setText(price);
        tvmotasp.setText(mota);
        Glide.with(this).load(SERVER.imagepath+img).into(imgchitiet);
    }



    private void Anhxa() {
        toolbarchitiet=findViewById(R.id.toolbarchitietsanpham);
        imgchitiet=findViewById(R.id.imgchitietsanpham);
        tvtensp=findViewById(R.id.tvtenchitietsanpham);
        tvgiasp=findViewById(R.id.tvgiachitietsanpham);
        tvmotasp=findViewById(R.id.tvmotachitietsanpham);
        btnmua=findViewById(R.id.btnmua);
        spinner=findViewById(R.id.spinner);
    }
}