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

import com.ihanfarhan.sellingsales.InputSupplierActivity;
import com.ihanfarhan.sellingsales.R;
import com.ihanfarhan.sellingsales.db.SupplierEntity;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {

    private List<SupplierEntity> listSupplier = new ArrayList<>();

    public SupplierAdapter(List<SupplierEntity> listSupplier) {
        this.listSupplier = listSupplier;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SupplierViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supplier, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        holder.bind(listSupplier.get(position));
    }

    @Override
    public int getItemCount() {
        return listSupplier.size();
    }

    class SupplierViewHolder extends RecyclerView.ViewHolder {

        private TextView tSupplier, tAlamat, tTelp;

        SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            tSupplier = itemView.findViewById(R.id.tSupplier);
            tAlamat = itemView.findViewById(R.id.tAlamat);
            tTelp = itemView.findViewById(R.id.tTelp);
        }

        void bind(final SupplierEntity item) {
            tSupplier.setText("Nama Supplier: " + item.getNamaSupplier());
            tAlamat.setText("Alamat: " + item.getAlamat());
            tTelp.setText("No Telepon: " + item.getTelp());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), InputSupplierActivity.class);
                    i.putExtra("id", item.getIdSupplier());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
