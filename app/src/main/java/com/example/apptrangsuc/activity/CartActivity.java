package com.example.apptrangsuc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptrangsuc.R;
import com.example.apptrangsuc.adapter.CartAdapter;
import com.example.apptrangsuc.model.SanPham;
import com.example.apptrangsuc.sqlite.DBHelper;
import com.example.apptrangsuc.sqlite.productsDAO;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    public  static TextView cart_total;
    private productsDAO productsDAO;
    private Intent intent;
    private Bundle bundle;
    private CartAdapter adapter_cart;
    private RecyclerView rvCart;
    private SQLiteDatabase db;
    public ArrayList<SanPham> dataSP = new ArrayList<>();
    public  double totalPrice = 0;
    private String id,name,image;
    private int quantity,price;
    Button btnThanhtoan,btnContinue;
    ImageView cart_imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        anhxa();
        checkData();
        getCart();
        getTotalPrice();
        onclick();


    }

    private void onclick() {
        cart_imgback.setOnClickListener(view -> {
            // Tạo Intent để chuyển đổi từ PayActivity sang CartActivity
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
        // click button thanh toan
        btnThanhtoan.setOnClickListener(view -> {
            Intent intent = new Intent(CartActivity.this, PayActivity.class);
            startActivity(intent);
        });

        // click button continue
        btnContinue.setOnClickListener(view -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }


    private void addProducts(){
        // Lấy thông tin sản phẩm từ bundle
        id = bundle.getString("id");
        name = bundle.getString("name");
        image = bundle.getString("img");
        price = bundle.getInt("price",1);
        quantity = bundle.getInt("quantity",1);

        // Thêm sản phẩm vào cơ sở dữ liệu
        productsDAO.insertProduct(id,name,image,price,quantity);
    }

    void  checkData(){
        if(bundle != null && !bundle.isEmpty()){
            addProducts();
        }else{
            getCart();
        }
    }
    private void getCart(){
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        dataSP = productsDAO.getProducts();
        // Tạo và thiết lập adapter cho RecyclerView hiển thị giỏ hàng
        adapter_cart = new CartAdapter(this,dataSP);
        rvCart.setAdapter(adapter_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    void getTotalPrice() {
        totalPrice = 0;
        for (SanPham sp : dataSP) {
            totalPrice += sp.getGiasanpham() * sp.getSoluong();
        }
        updateTotal(totalPrice);
    }

    public void updateTotal(double total) {
        String formattedTotal = formatCurrency(total);
        cart_total.setText(formattedTotal);
    }

    private String formatCurrency(double amount) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(amount);
    }


    private void anhxa() {
        rvCart = findViewById(R.id.rvCart);
        cart_total = findViewById(R.id.cart_total);
        cart_imgback = findViewById(R.id.cart_imgback);
        btnThanhtoan = findViewById(R.id.btnthanhtoan);
        btnContinue = findViewById(R.id.btncontinue);

        // Lấy bundle và gán giá trị cho TextView hiển thị tổng giá trị giỏ hàng
        intent =getIntent();
        bundle = intent.getExtras();
        cart_total.setText(totalPrice+"");

        // Khởi tạo đối tượng productsDAO để thao tác với cơ sở dữ liệu
        productsDAO = new productsDAO(this);
        // Khởi tạo cơ sở dữ liệu SQLite
        try {
            DBHelper dbHelper = new DBHelper(this);
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }

}