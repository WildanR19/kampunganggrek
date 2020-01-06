package com.wildan.kampunganggrek.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wildan.kampunganggrek.LoginActivity;
import com.wildan.kampunganggrek.R;
import com.wildan.kampunganggrek.notaActivity;

public class HomeAdmin extends AppCompatActivity {

    private RelativeLayout tambahpd, listpd, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        tambahpd = (RelativeLayout) findViewById(R.id.produk);
        listpd = (RelativeLayout) findViewById(R.id.listproduk);
        logout = (RelativeLayout) findViewById(R.id.logout);

        tambahpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, TambahProdukActivity.class);
                startActivity(i);
            }
        });

        listpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, ProdukActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
