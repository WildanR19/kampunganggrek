package com.wildan.kampunganggrek;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class belanja extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Anggrek> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private DBHandler dbHandler;
    //int[] sampleImages = {R.drawable.anggrek1, R.drawable.anggrek2, R.drawable.anggrek3, R.drawable.anggrek4, R.drawable.anggrek5, R.drawable.anggrek6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja);

        getSupportActionBar().setTitle("Belanja Bunga");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHandler = new DBHandler(this);
        recyclerView = (RecyclerView)findViewById(R.id.produk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume(){
        super.onResume();
        list = dbHandler.getSemuaAnggrek();
        adapter = new ListAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder>{
        Context context;
        List<Anggrek> list;

        public ListAdapter(Context context, List<Anggrek> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ListHolder(v);
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position) {
            final Anggrek model = list.get(position);
            holder.nama.setText(model.getNama());
            holder.harga.setText("Rp. "+model.getHarga());
            //holder.gbproduk.setImageResource(model.getGambar());
            holder.beli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(belanja.this, BayarActivity.class);
                    intent.putExtra("ID", model.getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ListHolder extends RecyclerView.ViewHolder{
            TextView nama, harga, date;
            Button beli;
            ImageView gbproduk;
            public ListHolder(View itemView) {
                super(itemView);
                nama = (TextView)itemView.findViewById(R.id.nama);
                harga = (TextView)itemView.findViewById(R.id.harga);
                beli = (Button)itemView.findViewById(R.id.btn_beli);
                gbproduk = (ImageView)itemView.findViewById(R.id.gbproduk);
                date = (TextView)itemView.findViewById(R.id.date_nota);
            }
        }
    }
}
