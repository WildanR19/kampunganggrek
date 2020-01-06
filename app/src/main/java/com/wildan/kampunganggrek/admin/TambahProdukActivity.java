package com.wildan.kampunganggrek.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wildan.kampunganggrek.DBHandler;
import com.wildan.kampunganggrek.R;

public class TambahProdukActivity extends AppCompatActivity {

    private EditText et_nama, et_harga;
    private Button button_tambahdata;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        dbHandler = new DBHandler(this);
        initComponents();

        getSupportActionBar().setTitle("Tambah Produk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponents(){
        et_nama = (EditText) findViewById(R.id.et_nama);
        et_harga = (EditText) findViewById(R.id.et_harga);
        button_tambahdata = (Button) findViewById(R.id.button_tambahdata);

        et_harga.setInputType(InputType.TYPE_CLASS_NUMBER);
        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiForm();
            }
        });
    }

    // FUNGSI INI UNTUK MEMVALIDASI FORM JIKA ADA YANG KOSONG ATAU TIDAK
    // LALU DILANJUT UNTUK MENJALANKAN PERINTAH SELANJUTNYA
    private void validasiForm(){
        String form_nama = et_nama.getText().toString();
        String form_harga = et_harga.getText().toString();

        if (form_nama.isEmpty()){
            et_nama.setError("Isi nama dulu");
            et_nama.requestFocus();
        } if (form_harga.isEmpty()){
            et_harga.setError("Isi Harga dulu");
            et_harga.requestFocus();
        }else {
            dbHandler.tambahAnggrek(et_nama.getText().toString(), et_harga.getText().toString());

            Toast.makeText(TambahProdukActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(TambahProdukActivity.this, ProdukActivity.class);
            startActivity(i);
            finish();
        }
    }
}
