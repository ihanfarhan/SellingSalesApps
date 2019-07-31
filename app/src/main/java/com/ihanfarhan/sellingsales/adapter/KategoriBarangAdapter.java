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

import com.ihanfarhan.sellingsales.InputKategoriBarangActivity;
import com.ihanfarhan.sellingsales.R;
import com.ihanfarhan.sellingsales.db.KategoriBarangEntity;

public class KategoriBarangAdapter extends RecyclerView.Adapter<KategoriBarangAdapter.KategoriBarangViewHolder> {

    private List<KategoriBarangEntity> listKategoriBarang = new ArrayList<>();

    public KategoriBarangAdapter(List<KategoriBarangEntity> listKategoriBarang) {
        this.listKategoriBarang = listKategoriBarang;
    }

    @NonNull
    @Override
    public KategoriBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KategoriBarangViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori_barang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriBarangViewHolder holder, int position) {
        holder.bind(listKategoriBarang.get(position));
    }

    @Override
    public int getItemCount() {
        return listKategoriBarang.size();
    }

    class KategoriBarangViewHolder extends RecyclerView.ViewHolder {

        private TextView tNamaKategoriBarang;

        KategoriBarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tNamaKategoriBarang = itemView.findViewById(R.id.tKategoriBarang);
        }

        void bind(final KategoriBarangEntity item) {
            tNamaKategoriBarang.setText("Nama Kategori Barang: " + item.getNamaKategoriBarang());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), InputKategoriBarangActivity.class);
                    i.putExtra("id", item.getIdKategoriBarang());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
