package com.ihanfarhan.sellingsales;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.ihanfarhan.sellingsales.adapter.PembelianAdapter;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;
import com.ihanfarhan.sellingsales.db.PembelianEntity;

public class PembelianActivity extends AppCompatActivity {

    private EditText inKeyword;
    private Button bCari;
    private List<PembelianEntity> pembelianList = new ArrayList<>();
    private PembelianAdapter listPembelianAdapter;
    private RecyclerView listPembelian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembelian);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inKeyword = findViewById(R.id.inKeyword);
        bCari = findViewById(R.id.bCari);
        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPembelian(inKeyword.getText().toString());
            }
        });
        listPembelianAdapter = new PembelianAdapter(pembelianList);
        listPembelian = findViewById(R.id.listPembelian);
        listPembelian.setAdapter(listPembelianAdapter);
        listPembelian.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listPembelian.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }

    private void loadPembelian(String keyword) {
        pembelianList.clear();
        pembelianList.addAll(SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllPembelian("%" + keyword + "%"));
        listPembelianAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listPembelian != null)
            loadPembelian(inKeyword.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void inputClick(View v) {
        Intent i = new Intent(this, InputPembelianActivity.class);
        startActivity(i);
    }
}
