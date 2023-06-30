package com.example.apptrangsuc.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptrangsuc.model.SanPham;
import com.example.apptrangsuc.sqlite.DBHelper;

import java.util.ArrayList;

public class productsDAO {
    SQLiteDatabase db;

    public productsDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<SanPham> getProducts() {
        ArrayList<SanPham> listProducts = new ArrayList<>();
        Cursor cursor = db.query("CART", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            SanPham sp = new SanPham();
            sp.setIdsanpham(cursor.getString(0));
            sp.setTensanpham(cursor.getString(1));
            sp.setHinhsanpham(cursor.getString(2));
            sp.setGiasanpham(cursor.getInt(3));
            sp.setSoluong(cursor.getInt(4));
            listProducts.add(sp);
        }
        cursor.close();
        return listProducts;
    }
    public  void insertProduct(String id, String name, String image, int price, int quantity){
        Cursor cursor = db.query("CART", null,"maSanPham=?", new String[]{id},null,null,null);
        if(cursor.moveToNext()){
            int currenQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
            int newQuantity = currenQuantity + quantity;

            ContentValues values = new ContentValues();
            values.put("soLuong", newQuantity);

            db.update("CART",values, "maSanPham=?", new String[]{id});

        }else {
            ContentValues values = new ContentValues();
            values.put("maSanPham", id);
            values.put("tenSanPham", name);
            values.put("hinhSanPham", image);
            values.put("giaSanPham", price);
            values.put("soLuong", quantity);

            db.insert("CART", null,values);
        }
        cursor.close();
    }
    public  void deleteProductById(String id){
        db.delete("CART","maSanPham=?", new String[]{id});
        db.close();
    }
    public  void updateProductQuantityById(String id, int quantity){
        ContentValues values = new ContentValues();
        values.put("soLuong",quantity);
        db.update("CART", values,"maSanPham=?", new String[]{id});
    }
    public void reloadcart(){
        db.delete("CART",null,null);
        db.close();
    }
}