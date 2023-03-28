package com.example.apptrangsuc.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptrangsuc.R;
import com.example.apptrangsuc.SERVER;
import com.example.apptrangsuc.adapter.ChuDeAdapter;

import com.example.apptrangsuc.adapter.SanPhamAdapter;
import com.example.apptrangsuc.model.ChuDe;
import com.example.apptrangsuc.model.SanPham;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentA extends Fragment {


    RecyclerView rvChude;
    RecyclerView recyclerView;
    ArrayList<SanPham> data = new ArrayList<>();
    SanPhamAdapter adaptersp;
    ArrayList<ChuDe> chude = new ArrayList<>();
    ChuDeAdapter chuDeAdapter;
    ViewFlipper viewFlipper;
    EditText edtsearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_a, container, false);
        // lay slide
        viewFlipper=view.findViewById(R.id.viewflipper);
        LoadViewFlipper();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ánh xa
        recyclerView=view.findViewById(R.id.rcSanpham);
        rvChude= view.findViewById(R.id.rcchude);
        edtsearch=view.findViewById(R.id.edtsearch);


        // lay chu de
        LoadChuDe();
        // lay san pham
        LoadSanPham();

        // tim kiem
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String chuoitim = charSequence.toString();
                adaptersp.getFilter().filter(chuoitim);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    // lay chu de
    private void LoadChuDe() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsondArray) {
                for (int i = 0; i <jsondArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsondArray.getJSONObject(i);
                        chude.add(new ChuDe(jsonObject.getString("idchude"),
                                jsonObject.getString("tenchude"),
                                jsonObject.getString("hinhchude")));

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                chuDeAdapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.chudepath, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
        chuDeAdapter = new ChuDeAdapter(chude, getContext());
        rvChude.setAdapter(chuDeAdapter);
        rvChude.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }
    // lay hinh anh tu sever ve
    private void LoadSanPham() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsondArray) {
                for (int i = 0; i <jsondArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsondArray.getJSONObject(i);
                        data.add(new SanPham(jsonObject.getString("idsanpham"),
                                jsonObject.getString("idchude"),
                                jsonObject.getString("tensanpham"),
                                jsonObject.getInt("giasanpham"),
                                jsonObject.getString("hinhsanpham"),
                                jsonObject.getString("motasanpham")));

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                adaptersp.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.laysanphampath, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
        adaptersp = new SanPhamAdapter(data,getContext());
        adaptersp.setOnItemClickListener(new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SanPham sp) {
                Intent intent = new Intent(getContext(),ChiTietSanPhamActivity.class);
                //chuyền du lieu
                intent.putExtra("name",sp.getTensanpham());
                intent.putExtra("price",sp.getGiasanpham());
                intent.putExtra("img",sp.getHinhsanpham());
                intent.putExtra("mota",sp.getMotasanpham());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adaptersp);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));


    }



    // load slide
    void LoadViewFlipper() {

        ArrayList<String> slidepnj = new ArrayList<>();
        slidepnj.add(SERVER.slidepath + "slide1.jpg");
        slidepnj.add(SERVER.slidepath + "slide2.jpg");
        slidepnj.add(SERVER.slidepath + "slide3.jpg");
        slidepnj.add(SERVER.slidepath + "slide4.jpg");
        slidepnj.add(SERVER.slidepath + "slide5.jpg");

        for (String slide : slidepnj) {
            ImageView hinh = new ImageView(getContext());
            Picasso.get().load(slide).into(hinh);
            hinh.setScaleType(ImageView.ScaleType.FIT_XY); // Cố định lại 2 chiều hình ảnh\
            viewFlipper.addView(hinh);
        }
        // Animation
        Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        // Set autoStart
        viewFlipper.setAutoStart(true);
        // Set time run Slide
        viewFlipper.setFlipInterval(3000);
    }

}
