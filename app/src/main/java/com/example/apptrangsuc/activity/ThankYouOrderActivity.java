package com.example.apptrangsuc.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apptrangsuc.R;

public class ThankYouOrderActivity extends AppCompatActivity {

    Button  btntieptuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_order);


        btntieptuc = findViewById(R.id.btntrangchu);


        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThankYouOrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
