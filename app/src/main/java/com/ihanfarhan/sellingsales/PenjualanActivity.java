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

import com.ihanfarhan.sellingsales.adapter.PenjualanAdapter;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;
import com.ihanfarhan.sellingsales.db.PenjualanEntity;

public class PenjualanActivity extends AppCompatActivity {

    private EditText inKeyword;
    private Button bCari;
    private List<PenjualanEntity> penjualanList = new ArrayList<>();
    private PenjualanAdapter listPenjualanAdapter;
    private RecyclerView listPenjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inKeyword = findViewById(R.id.inKeyword);
        bCari = findViewById(R.id.bCari);
        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPenjualan(inKeyword.getText().toString());
            }
        });
        listPenjualanAdapter = new PenjualanAdapter(penjualanList);
        listPenjualan = findViewById(R.id.listPenjualan);
        listPenjualan.setAdapter(listPenjualanAdapter);
        listPenjualan.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listPenjualan.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }

    private void loadPenjualan(String keyword) {
        penjualanList.clear();
        penjualanList.addAll(SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllPenjualan("%" + keyword + "%"));
        listPenjualanAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listPenjualan != null)
            loadPenjualan(inKeyword.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void inputClick(View v) {
        Intent i = new Intent(this, InputPenjualanActivity.class);
        startActivity(i);
    }
}
