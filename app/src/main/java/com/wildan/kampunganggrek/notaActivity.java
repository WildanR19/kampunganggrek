package com.wildan.kampunganggrek;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class notaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<nota> list = new ArrayList<>();
    private NOTAHelper notaHelp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        getSupportActionBar().setTitle("Nota Pembelian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notaHelp = new NOTAHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.rv_nota);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = notaHelp.getAll();
        adapter = new ListAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder>{
        Context context;
        List<nota> list;

        public ListAdapter(Context context, List<nota> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nota_list, parent, false);
            return new ListHolder(v);
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position) {
            final nota model = list.get(position);
            holder.namaA.setText(model.getNama());
            holder.hargaA.setText(model.getHarga());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ListHolder extends RecyclerView.ViewHolder{
            TextView namaA, hargaA;
            public ListHolder(View itemView) {
                super(itemView);
                namaA = (TextView)itemView.findViewById(R.id.namaA);
                hargaA = (TextView)itemView.findViewById(R.id.hargaA);
            }
        }
    }
    
}
