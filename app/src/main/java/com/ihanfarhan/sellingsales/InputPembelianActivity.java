package com.ihanfarhan.sellingsales;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ihanfarhan.sellingsales.db.BarangEntity;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;
import com.ihanfarhan.sellingsales.db.PembelianEntity;
import com.ihanfarhan.sellingsales.db.SupplierEntity;

public class InputPembelianActivity extends AppCompatActivity {

    private EditText inJumlah;
    private Spinner inNamaSupplier, inNamaBarang;
    private Button bBatal, bSimpan;
    private ArrayAdapter<SupplierEntity> supplierArrayAdapter;
    private ArrayAdapter<BarangEntity> barangArrayAdapter;
    private PembelianEntity item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pembelian);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inNamaSupplier = findViewById(R.id.inNamaSupplier);
        inNamaBarang = findViewById(R.id.inNamaBarang);
        inJumlah = findViewById(R.id.inJumlah);
        bBatal = findViewById(R.id.bBatal);
        bSimpan = findViewById(R.id.bSimpan);

        bBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PembelianEntity data = new PembelianEntity((SupplierEntity) inNamaSupplier.getSelectedItem(),
                        (BarangEntity) inNamaBarang.getSelectedItem(),
                        Integer.parseInt(inJumlah.getText().toString()),
                        new SimpleDateFormat().format(new Date()));
                SellingSalesDB.getInstance(getApplicationContext()).getDao().insertPembelian(data);
                int stokBaru = data.getBarang().getStok() + Integer.parseInt(inJumlah.getText().toString());
                data.getBarang().setStok(stokBaru);
                SellingSalesDB.getInstance(getApplicationContext()).getDao().updateBarang(data.getBarang());
                Toast.makeText(InputPembelianActivity.this, "Berhasil simpan pembelian", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        supplierArrayAdapter = new ArrayAdapter<SupplierEntity>
                (this, android.R.layout.simple_spinner_item,
                        SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllSupplier());
        supplierArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inNamaSupplier.setAdapter(supplierArrayAdapter);
        barangArrayAdapter = new ArrayAdapter<BarangEntity>
                (this, android.R.layout.simple_spinner_item,
                        SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllBarangInStok());
        barangArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inNamaBarang.setAdapter(barangArrayAdapter);

        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("id")) {
            item = SellingSalesDB.getInstance(getApplicationContext()).getDao().getPembelian(b.getInt("id"));
            setItem();
        }
    }

    private void setItem() {
        inNamaSupplier.setSelection(supplierArrayAdapter.getPosition(item.getSupplier()));
        inNamaBarang.setSelection(barangArrayAdapter.getPosition(item.getBarang()));
        inJumlah.setText(item.getJumlah() + "");
        inNamaSupplier.setEnabled(false);
        inNamaBarang.setEnabled(false);
        inJumlah.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
