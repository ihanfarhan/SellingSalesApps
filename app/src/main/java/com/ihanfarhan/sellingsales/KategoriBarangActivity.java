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

import com.ihanfarhan.sellingsales.adapter.KategoriBarangAdapter;
import com.ihanfarhan.sellingsales.db.KategoriBarangEntity;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;

public class KategoriBarangActivity extends AppCompatActivity {

    private EditText inKeyword;
    private Button bCari;
    private List<KategoriBarangEntity> kategoriBarangList = new ArrayList<>();
    private KategoriBarangAdapter listKategoriBarangAdapter;
    private RecyclerView listKategoriBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_barang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inKeyword = findViewById(R.id.inKeyword);
        bCari = findViewById(R.id.bCari);
        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadKategoriBarang(inKeyword.getText().toString());
            }
        });
        listKategoriBarangAdapter = new KategoriBarangAdapter(kategoriBarangList);
        listKategoriBarang = findViewById(R.id.listKategoriBarang);
        listKategoriBarang.setAdapter(listKategoriBarangAdapter);
        listKategoriBarang.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listKategoriBarang.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

    }

    private void loadKategoriBarang(String keyword) {
        kategoriBarangList.clear();
        kategoriBarangList.addAll(SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllKategoriBarang("%" + keyword + "%"));
        listKategoriBarangAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listKategoriBarang != null)
            loadKategoriBarang(inKeyword.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void inputClick(View v) {
        Intent i = new Intent(this, InputKategoriBarangActivity.class);
        startActivity(i);
    }
}
