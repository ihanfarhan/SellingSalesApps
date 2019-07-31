package com.ihanfarhan.sellingsales.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.ihanfarhan.sellingsales.InputBarangActivity;
import com.ihanfarhan.sellingsales.R;
import com.ihanfarhan.sellingsales.db.BarangEntity;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {

    private List<BarangEntity> listBarang = new ArrayList<>();

    public BarangAdapter(List<BarangEntity> listBarang) {
        this.listBarang = listBarang;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BarangViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        holder.bind(listBarang.get(position));
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class BarangViewHolder extends RecyclerView.ViewHolder {

        private TextView tNamaBarang, tKategoriBarang, tHarga, tStok;

        BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tNamaBarang = itemView.findViewById(R.id.tNamaBarang);
            tKategoriBarang = itemView.findViewById(R.id.tKategoriBarang);
            tHarga = itemView.findViewById(R.id.tHarga);
            tStok = itemView.findViewById(R.id.tStok);
        }

        void bind(final BarangEntity item) {
            tNamaBarang.setText("Nama Barang: " + item.getNamaBarang());
            tKategoriBarang.setText("Kategori: " + item.getKategoriBarang().getNamaKategoriBarang());
            tHarga.setText("Harga: " + item.getHarga());
            tStok.setText("Stok: " + item.getStok());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), InputBarangActivity.class);
                    i.putExtra("id", item.getIdBarang());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
