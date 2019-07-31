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

import com.ihanfarhan.sellingsales.InputPenjualanActivity;
import com.ihanfarhan.sellingsales.R;
import com.ihanfarhan.sellingsales.db.PenjualanEntity;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.PenjualanViewHolder> {

    private List<PenjualanEntity> listPenjualan = new ArrayList<>();

    public PenjualanAdapter(List<PenjualanEntity> listPenjualan) {
        this.listPenjualan = listPenjualan;
    }

    @NonNull
    @Override
    public PenjualanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PenjualanViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penjualan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PenjualanViewHolder holder, int position) {
        holder.bind(listPenjualan.get(position));
    }

    @Override
    public int getItemCount() {
        return listPenjualan.size();
    }

    class PenjualanViewHolder extends RecyclerView.ViewHolder {

        private TextView tNamaBarang, tJumlah, tTotal, tBayar, tKembali;

        PenjualanViewHolder(@NonNull View itemView) {
            super(itemView);
            tNamaBarang = itemView.findViewById(R.id.tNamaBarang);
            tJumlah = itemView.findViewById(R.id.tJumlah);
            tTotal = itemView.findViewById(R.id.tTotal);
            tBayar = itemView.findViewById(R.id.tBayar);
            tKembali = itemView.findViewById(R.id.tKembali);
        }

        void bind(final PenjualanEntity item) {
            tNamaBarang.setText("Nama Barang: " + item.getBarang().getNamaBarang());
            tJumlah.setText("Jumlah: " + item.getJumlah());
            tTotal.setText("Total: " + item.getTotal());
            tBayar.setText("Bayar: " + item.getBayar());
            tKembali.setText("Kembali: " + item.getKembali());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), InputPenjualanActivity.class);
                    i.putExtra("id", item.getIdPenjualan());
                    i.putExtra("enabled", false);
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
