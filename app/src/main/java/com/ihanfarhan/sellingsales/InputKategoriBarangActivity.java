package com.ihanfarhan.sellingsales;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ihanfarhan.sellingsales.db.KategoriBarangEntity;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;

public class InputKategoriBarangActivity extends AppCompatActivity {

    private EditText inKategoriBarang;
    private Button bBatal, bSimpan;
    private KategoriBarangEntity item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kategori_barang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inKategoriBarang = findViewById(R.id.inKategoriBarang);
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
                KategoriBarangEntity data = new KategoriBarangEntity(inKategoriBarang.getText().toString());
                if (item != null) {
                    data.setIdKategoriBarang(item.getIdKategoriBarang());
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().updateKategoriBarang(data);
                }
                else
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().insertKategoriBarang(data);
                Toast.makeText(InputKategoriBarangActivity.this, "Berhasil simpan kategori barang", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("id")) {
            item = SellingSalesDB.getInstance(getApplicationContext()).getDao().getKategoriBarang(b.getInt("id"));
            setItem();
        }
    }

    private void setItem() {
        inKategoriBarang.setText(item.getNamaKategoriBarang());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
