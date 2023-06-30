package com.example.apptrangsuc.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptrangsuc.R;
import com.example.apptrangsuc.sever.SERVER;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtS_Fullname, edtS_Email, edtS_Pass, edtS_EnterThePass, edtS_Address, edtS_Phone;
    private Button btnS_Register, btnS_Login, btnS_Exit;
    private String fullname, email, pass, enterpass, address, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // ánh xạ
        Anhxa();
        // xu ly dang ky

        // chuyen trang dang nhap
        btnS_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // xu ly dang ky
    public void submit(View view) {
        fullname = edtS_Fullname.getText().toString().trim();
        email = edtS_Email.getText().toString().trim();
        pass = edtS_Pass.getText().toString().trim();
        enterpass = edtS_EnterThePass.getText().toString().trim();
        address = edtS_Address.getText().toString().trim();
        phone = edtS_Phone.getText().toString().trim();

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_LONG).show();
            return;
        }

        if (!pass.equals(enterpass)) {
            Toast.makeText(this, "Password Incorrect", Toast.LENGTH_LONG).show();
            return;
        }

        if (!fullname.equals("") && !email.equals("") && !pass.equals("")
                && !address.equals("") && !phone.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.register, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Response", response);
                    if (response.equals("success")) {
                        Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    } else if (response.equals("failure")) {
                        Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("hoten", fullname);
                    data.put("email", email);
                    data.put("password", pass);
                    data.put("diachi", address);
                    data.put("sodienthoai", phone);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void Anhxa() {
        edtS_Email = findViewById(R.id.edtS_Email);
        edtS_Pass = findViewById(R.id.edtS_Pass);
        if (edtS_Pass != null) {
            edtS_Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        edtS_EnterThePass = findViewById(R.id.edtS_EnterThePass);
        edtS_Fullname = findViewById(R.id.edtS_FullName);
        edtS_Address = findViewById(R.id.edtS_Address);
        edtS_Phone = findViewById(R.id.edtS_Phone);
        btnS_Login = findViewById(R.id.btnS_Dangnhap);
        fullname = email = pass = enterpass = address = phone = "";
    }

    // Kiểm tra định dạng email
    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
