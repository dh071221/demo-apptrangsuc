package com.example.apptrangsuc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptrangsuc.R;
import com.example.apptrangsuc.adapter.SanPhamAdapter;
import com.example.apptrangsuc.model.SanPham;
import com.example.apptrangsuc.sever.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductByCategoriesActivity extends AppCompatActivity {

    private String idchude;
    private Intent intent;
    private ArrayList<SanPham> dataProducts = new ArrayList<>();
    private RecyclerView rvProducts;
    private SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_categories);
        initView();
        callAPIGetProducts(idchude);
    }

    private void callAPIGetProducts(String idchude){
        String URL = SERVER.productsByCategory+"?idchude="+idchude;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsondArray) {
                for (int i = 0; i <jsondArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsondArray.getJSONObject(i);
                        dataProducts.add(new SanPham(jsonObject.getString("idsanpham"),
                                jsonObject.getString("idchude"),
                                jsonObject.getString("tensanpham"),
                                jsonObject.getString("hinhsanpham"),
                                jsonObject.getString("motasanpham"),
                                jsonObject.getInt("giasanpham")));

                    } catch (JSONException e) {
                        Toast.makeText(ProductByCategoriesActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                sanPhamAdapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductByCategoriesActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
        sanPhamAdapter = new SanPhamAdapter(dataProducts,this);
        sanPhamAdapter.setOnItemClickListener(new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SanPham sp) {
                Intent intent = new Intent(ProductByCategoriesActivity.this, ChiTietSanPhamActivity.class);
                //chuyền du lieu
                intent.putExtra("id",sp.getIdsanpham());
                intent.putExtra("name",sp.getTensanpham());
                intent.putExtra("price",sp.getGiasanpham());
                intent.putExtra("img",sp.getHinhsanpham());
                intent.putExtra("mota",sp.getMotasanpham());
                startActivity(intent);
            }
        });
        rvProducts.setAdapter(sanPhamAdapter);
        rvProducts.setLayoutManager(new GridLayoutManager(this,2));
        rvProducts.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }


    private void initView(){
        rvProducts = findViewById(R.id.rvProducts);
        intent  = getIntent();
        idchude = intent.getStringExtra("idchude");
    }
}