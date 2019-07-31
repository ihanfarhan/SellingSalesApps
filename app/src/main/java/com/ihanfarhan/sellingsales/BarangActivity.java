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

import com.ihanfarhan.sellingsales.adapter.BarangAdapter;
import com.ihanfarhan.sellingsales.db.BarangEntity;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;

public class BarangActivity extends AppCompatActivity {

    private EditText inKeyword;
    private Button bCari;
    private List<BarangEntity> barangList = new ArrayList<>();
    private BarangAdapter listBarangAdapter;
    private RecyclerView listBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inKeyword = findViewById(R.id.inKeyword);
        bCari = findViewById(R.id.bCari);
        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBarang(inKeyword.getText().toString());
            }
        });
        listBarangAdapter = new BarangAdapter(barangList);
        listBarang = findViewById(R.id.listBarang);
        listBarang.setAdapter(listBarangAdapter);
        listBarang.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listBarang.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }

    private void loadBarang(String keyword) {
        barangList.clear();
        barangList.addAll(SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllBarang("%" + keyword + "%"));
        listBarangAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listBarang != null)
            loadBarang(inKeyword.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void inputClick(View v) {
        Intent i = new Intent(this, InputBarangActivity.class);
        startActivity(i);
    }
}
