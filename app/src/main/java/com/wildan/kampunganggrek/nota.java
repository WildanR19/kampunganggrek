package com.wildan.kampunganggrek;

import android.print.PrinterId;

public class nota {
    private int id;
    private String nama;
    private String harga;
    private String date;

    public nota(int id, String nama, String harga, String date) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga(){
        return harga;
    }

    public void setHarga(String harga){
        this.harga = harga;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
