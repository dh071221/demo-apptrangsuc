package com.example.apptrangsuc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptrangsuc.activity.CartActivity;
import com.example.apptrangsuc.R;
import com.example.apptrangsuc.adapter.PayAdapter;
import com.example.apptrangsuc.model.SanPham;
import com.example.apptrangsuc.sever.SERVER;
import com.example.apptrangsuc.sqlite.productsDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PayActivity extends AppCompatActivity {
    ImageView pay_imgback;
    RecyclerView rcCart;
    TextView moneypay;
    Button btnorder;
    public String car_total;
    public static ArrayList<SanPham> dataSP = new ArrayList<>();
    private productsDAO productsDAO;
    private PayAdapter payAdapter;
    public SharedPreferences sharedPreferences;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        anhxa();
        getProductCart();
        onClick();
    }


    private void onClick() {

        // Xử lý sự kiện khi người dùng nhấn nút back
        pay_imgback.setOnClickListener(view -> {
            // Tạo Intent để chuyển đổi từ PayActivity sang CartActivity
            Intent intent = new Intent(PayActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện khi người dùng nhấn nút đặt hàng
        btnorder.setOnClickListener(view -> {
            pay();
        });

    }

    //  lấy danh sách sản phẩm từ giỏ hàng và thiết lập adapter để hiển thị các mục trong giỏ hàng.
    private void getProductCart(){
        productsDAO = new productsDAO(this);
        dataSP = productsDAO.getProducts();

        payAdapter = new PayAdapter(this, dataSP);
        rcCart.setAdapter(payAdapter);
        rcCart.setLayoutManager(new LinearLayoutManager(this));
        rcCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    // thực hiện việc gửi yêu cầu thanh toán và đặt hàng lên máy chủ
    private void pay(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // gửi yêu cầu POST đến một URL (SERVER.hoadon) để tạo hóa đơn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.hoadon, new Response.Listener<String>() {
            @Override
            public void onResponse(final String madonhang) {
                if(Integer.parseInt(madonhang)>0){
                    // Nếu mã đơn hàng trả về là số dương , tiếp tục gửi yêu cầu
                    // POST khác (SERVER.chitiethoadon) để thêm chi tiết hóa đơn vào máy chủ.
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest request = new StringRequest(Request.Method.POST, SERVER.chitiethoadon, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("1")) {
                                dataSP.clear();
                                productsDAO.reloadcart();
                                Intent intent = new Intent(getApplicationContext(), ThankYouOrderActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(PayActivity.this, "Cart data has been corrupted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            JSONArray jsonArray = new JSONArray();
                            for(int i=0;i<dataSP.size();i++)
                            {
                                //JSON chứa thông tin chi tiết hóa đơn
                                JSONObject jsonObject = new JSONObject();
                                try {

                                    jsonObject.put("idhoadon",madonhang);
                                    jsonObject.put("idsanpham",dataSP.get(i).getIdsanpham());
                                    jsonObject.put("dongia",dataSP.get(i).getGiasanpham());
                                    jsonObject.put("soluong",dataSP.get(i).getSoluong());
                                    jsonObject.put("thanhtien",dataSP.get(i).getGiasanpham()*dataSP.get(i).getSoluong());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                            }
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("json",jsonArray.toString());
                            return hashMap;
                        }
                    };
                    queue.add(request);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String,String>();
                hashMap.put("tongtien",car_total);
                hashMap.put("email",email);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void anhxa() {
        pay_imgback = findViewById(R.id.pay_imageback);
        rcCart = findViewById(R.id.rvCart);
        moneypay = findViewById(R.id.moneyPay);
        btnorder = findViewById(R.id.btnorder);

        car_total = CartActivity.cart_total.getText().toString().trim();
        moneypay.setText(car_total);
        // luu tru thong tin email
        sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");

    }
}