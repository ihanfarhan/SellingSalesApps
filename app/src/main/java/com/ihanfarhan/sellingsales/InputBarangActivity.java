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

import com.ihanfarhan.sellingsales.db.BarangEntity;
import com.ihanfarhan.sellingsales.db.KategoriBarangEntity;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;

public class InputBarangActivity extends AppCompatActivity {

    private EditText inNamaBarang, inHarga, inStok;
    private Spinner inKategoriBarang;
    private Button bBatal, bSimpan;
    private ArrayAdapter<KategoriBarangEntity> spinnerArrayAdapter;
    private BarangEntity item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inNamaBarang = findViewById(R.id.inNamaBarang);
        inHarga = findViewById(R.id.inHarga);
        inStok = findViewById(R.id.inStok);
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
                BarangEntity data = new BarangEntity(inNamaBarang.getText().toString(),
                        (KategoriBarangEntity) inKategoriBarang.getSelectedItem(),
                        Integer.parseInt(inHarga.getText().toString()),
                        Integer.parseInt(inStok.getText().toString()));
                if (item != null) {
                    data.setIdBarang(item.getIdBarang());
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().updateBarang(data);
                }
                else
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().insertBarang(data);
                Toast.makeText(InputBarangActivity.this, "Berhasil simpan barang", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        spinnerArrayAdapter = new ArrayAdapter<KategoriBarangEntity>
                (this, android.R.layout.simple_spinner_item,
                        SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllKategoriBarang());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inKategoriBarang.setAdapter(spinnerArrayAdapter);

        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("id")) {
            item = SellingSalesDB.getInstance(getApplicationContext()).getDao().getBarang(b.getInt("id"));
            setItem();
        }
    }

    private void setItem() {
        inNamaBarang.setText(item.getNamaBarang());
        inHarga.setText(item.getHarga()+"");
        inStok.setText(item.getStok()+"");
        inKategoriBarang.setSelection(spinnerArrayAdapter.getPosition(item.getKategoriBarang()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
