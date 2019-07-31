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

import com.ihanfarhan.sellingsales.adapter.SupplierAdapter;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;
import com.ihanfarhan.sellingsales.db.SupplierEntity;

public class SupplierActivity extends AppCompatActivity {

    private EditText inKeyword;
    private Button bCari;
    private List<SupplierEntity> supplierList = new ArrayList<>();
    private SupplierAdapter listSupplierAdapter;
    private RecyclerView listSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inKeyword = findViewById(R.id.inKeyword);
        bCari = findViewById(R.id.bCari);
        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSupplier(inKeyword.getText().toString());
            }
        });
        listSupplierAdapter = new SupplierAdapter(supplierList);
        listSupplier = findViewById(R.id.listSupplier);
        listSupplier.setAdapter(listSupplierAdapter);
        listSupplier.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listSupplier.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }

    private void loadSupplier(String keyword) {
        supplierList.clear();
        supplierList.addAll(SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllSupplier("%" + keyword + "%"));
        listSupplierAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listSupplier != null)
            loadSupplier(inKeyword.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void inputClick(View v) {
        Intent i = new Intent(this, InputSupplierActivity.class);
        startActivity(i);
    }
}
