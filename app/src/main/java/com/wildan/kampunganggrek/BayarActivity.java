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

    private TextView nama,harga, bayar, jmlBeli;
    private Button btn_simpan, plus, minus;
    private DBHandler dbhelp;
    private NOTAHelper notaHelper;
    private Anggrek anggrek;
    private String jumlah, hargaa;

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
        jmlBeli = (TextView) findViewById(R.id.jmlBeli);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);

        jumlah = jmlBeli.getText().toString();

        dbhelp = new DBHandler(this);
        notaHelper = new NOTAHelper(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            anggrek = dbhelp.getAnggrek(bundle.getInt("ID"));
            nama.setText(anggrek.getNama());
            harga.setText("RP. "+anggrek.getHarga());
            bayar.setText("RP. "+anggrek.getHarga());
            hargaa = anggrek.getHarga();
        }

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus.setEnabled(true);

                NumberFormat nf = NumberFormat.getNumberInstance();
                nf.setMaximumFractionDigits(0);

                String sumStr = jmlBeli.getText().toString();        //ambil jumlah beli dari xml view
                int sum = Integer.valueOf(sumStr);          //convert to int
                if(sum == (Integer.parseInt(jumlah)-1)){        //jika jumlah barang == stok maka tombol tambah mati
                    plus.setEnabled(false);
                }
                //fungsi tambah barang
                sum += 1;
                long sumPrc = Integer.parseInt(hargaa.replace(",",""));
                sumPrc *= sum;
                sumStr = String.valueOf(sum);
                String sumPrcStr = nf.format(sumPrc);
                jmlBeli.setText(sumStr);
                bayar.setText("Rp. "+sumPrcStr);

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus.setEnabled(true);

                NumberFormat nf = NumberFormat.getNumberInstance();
                nf.setMaximumFractionDigits(0);

                String sumStr = jmlBeli.getText().toString();
                int sum = Integer.valueOf(sumStr);
                if(sum <= 1){
                    minus.setEnabled(false);
                }else{
                    sum -= 1;
                    long sumPrc = Integer.parseInt(hargaa.replace(",",""));
                    sumPrc *= sum;
                    sumStr = String.valueOf(sum);
                    String sumPrcStr = nf.format(sumPrc);
                    jmlBeli.setText(sumStr);
                    bayar.setText("Rp. "+sumPrcStr);
                }
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(BayarActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Pembayaran Berhasil")
                        .setConfirmText("Lihat Nota Pembayaran")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                notaHelper.insertData(nama.getText().toString(), bayar.getText().toString());
                                Intent i = new Intent(BayarActivity.this, notaActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }).show();

            }
        });
    }
}
