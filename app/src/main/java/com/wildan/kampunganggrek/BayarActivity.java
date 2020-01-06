package com.wildan.kampunganggrek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BayarActivity extends AppCompatActivity {

    private TextView nama,harga, bayar;
    private Button btn_simpan;
    private DBHandler dbhelp;
    private NOTAHelper notaHelper;
    private Anggrek anggrek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        getSupportActionBar().setTitle("Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama =  (TextView) findViewById(R.id.nama_bunga);
        harga = (TextView) findViewById(R.id.harga_bunga);
        bayar = (TextView) findViewById(R.id.view_harga);
        btn_simpan = (Button) findViewById(R.id.btnbayar);

        dbhelp = new DBHandler(this);
        notaHelper = new NOTAHelper(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            anggrek = dbhelp.getAnggrek(bundle.getInt("ID"));
            nama.setText(anggrek.getNama());
            harga.setText(anggrek.getHarga());
            bayar.setText(anggrek.getHarga());
        }
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(BayarActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Pembayaran Berhasil")
                        .setConfirmText("Lihat Tiket Parkir")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                notaHelper.insertData(nama.getText().toString(), harga.getText().toString());
                                Intent i = new Intent(BayarActivity.this, notaActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }).show();

            }
        });

    }
}
