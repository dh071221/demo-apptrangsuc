package com.example.apptrangsuc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apptrangsuc.R;

public class GioHang extends AppCompatActivity {

    TextView tvback,tvtotalmoney;
    ListView lvcart;
    Button btnB_giohang,btn_continue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhxa();
    }

    private void anhxa() {
        tvback=findViewById(R.id.tvback);
        tvtotalmoney=findViewById(R.id.tvtotalmoney);
        lvcart=findViewById(R.id.lvcart);
        btnB_giohang=findViewById(R.id.btnB_giohang);
        btn_continue=findViewById(R.id.btn_continue);
    }
}