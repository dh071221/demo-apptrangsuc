package com.example.apptrangsuc.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.templates.ControlButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptrangsuc.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    Button btnL_Dangnhap, btnL_Dangky, btnL_Thoat;

    private EditText edtL_Email, edtL_Pass;
    private String email, password;
    private String URL = "http://192.168.7.163:3000/banhang/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();

        // xuly thoat
        btnL_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulythoat();
            }
        });

        // chuyen trang dang ky
        btnL_Dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // xu ly dang nhap
        btnL_Dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtL_Email.getText().toString().trim();
                password = edtL_Pass.getText().toString().trim();
                if (!email.equals("") && !password.equals("")) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("email", email);
                            data.put("password", password);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(LoginActivity.this, "Must enter the full field", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void xulythoat() {
        AlertDialog.Builder exit = new AlertDialog.Builder(LoginActivity.this);
        exit.setTitle("Exit");
        exit.setIcon(android.R.drawable.ic_dialog_info);
        exit.setMessage("Do you want to exit the app?");
        exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        exit.setCancelable(false);
        exit.create().show();
    }

    private void Anhxa() {
        email = password = "";
        edtL_Email = findViewById(R.id.edtL_Email);
        edtL_Pass = findViewById(R.id.edtL_Pass);
        btnL_Dangnhap = findViewById(R.id.btnL_Dangnhap);
        btnL_Thoat = findViewById(R.id.btnL_Thoat);
        btnL_Dangky = findViewById(R.id.btnL_Dangky);
    }
}