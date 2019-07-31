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

import com.ihanfarhan.sellingsales.InputPembelianActivity;
import com.ihanfarhan.sellingsales.R;
import com.ihanfarhan.sellingsales.db.PembelianEntity;

public class PembelianAdapter extends RecyclerView.Adapter<PembelianAdapter.PembelianViewHolder> {

    private List<PembelianEntity> listPembelian = new ArrayList<>();

    public PembelianAdapter(List<PembelianEntity> listPembelian) {
        this.listPembelian = listPembelian;
    }

    @NonNull
    @Override
    public PembelianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PembelianViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pembelian, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PembelianViewHolder holder, int position) {
        holder.bind(listPembelian.get(position));
    }

    @Override
    public int getItemCount() {
        return listPembelian.size();
    }

    class PembelianViewHolder extends RecyclerView.ViewHolder {

        private TextView tNamaSupplier, tNamaBarang, tJumlah, tWaktu;

        PembelianViewHolder(@NonNull View itemView) {
            super(itemView);
            tNamaSupplier = itemView.findViewById(R.id.tNamaSupplier);
            tNamaBarang = itemView.findViewById(R.id.tNamaBarang);
            tJumlah = itemView.findViewById(R.id.tJumlah);
            tWaktu = itemView.findViewById(R.id.tWaktu);
        }

        void bind(final PembelianEntity item) {
            tNamaSupplier.setText("Nama Supplier: " + item.getSupplier().getNamaSupplier());
            tNamaBarang.setText("Nama Barang: " + item.getBarang().getNamaBarang());
            tJumlah.setText("Jumlah: " + item.getJumlah());
            tWaktu.setText("Waktu: " + item.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), InputPembelianActivity.class);
                    i.putExtra("id", item.getIdPembelian());
                    i.putExtra("enabled", false);
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
