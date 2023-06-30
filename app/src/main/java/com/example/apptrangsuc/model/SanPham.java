package com.example.apptrangsuc.model;

import java.io.Serializable;

public class SanPham implements Serializable {

    public String idsanpham;
    public String idchude;
    public String tensanpham;
    public int giasanpham;
    public String hinhsanpham;
    public String motasanpham;
    public int soluong;

    public SanPham(){}

    public SanPham(String idsanpham, String idchude, String tensanpham, int giasanpham, String hinhsanpham, String motasanpham, int soluong) {
        this.idsanpham = idsanpham;
        this.idchude = idchude;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhsanpham = hinhsanpham;
        this.motasanpham = motasanpham;
        this.soluong = soluong;
    }

    public SanPham(String idsanpham, String idchude, String tensanpham, int giasanpham, String hinhsanpham, String motasanpham) {
        this.idsanpham = idsanpham;
        this.idchude = idchude;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhsanpham = hinhsanpham;
        this.motasanpham = motasanpham;

    }

    public SanPham(String idsanpham, String idchude, String tensanpham, String hinhsanpham, String motasanpham, int giasanpham) {
        this.idsanpham = idsanpham;
        this.idchude = idchude;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhsanpham = hinhsanpham;
        this.motasanpham = motasanpham;
    }

    public String getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
    }

    public String getIdchude() {
        return idchude;
    }

    public void setIdchude(String idchude) {
        this.idchude = idchude;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getHinhsanpham() {
        return hinhsanpham;
    }

    public void setHinhsanpham(String hinhsanpham) {
        this.hinhsanpham = hinhsanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}

