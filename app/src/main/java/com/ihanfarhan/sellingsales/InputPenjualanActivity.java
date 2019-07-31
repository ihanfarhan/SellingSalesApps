package com.ihanfarhan.sellingsales;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ihanfarhan.sellingsales.db.BarangEntity;
import com.ihanfarhan.sellingsales.db.SellingSalesDB;
import com.ihanfarhan.sellingsales.db.PenjualanEntity;

public class InputPenjualanActivity extends AppCompatActivity {

    private EditText inJumlah, inBayar;
    private TextView tTotal, tKembali;
    private Spinner inNamaBarang;
    private Button bBatal, bSimpan;
    private ArrayAdapter<BarangEntity> barangArrayAdapter;
    private PenjualanEntity item;
    private int jml, byr, total, kembali;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                jml = Integer.parseInt(inJumlah.getText().toString().trim().length() > 0 ? inJumlah.getText().toString().trim() : "0");
                byr = Integer.parseInt(inBayar.getText().toString().trim().length() > 0 ? inBayar.getText().toString().trim() : "0");
                total = ((BarangEntity) inNamaBarang.getSelectedItem()).getHarga() * jml;
                kembali = byr - total;
                tTotal.setText("Total: " + total);
                tKembali.setText("Kembali: " + kembali);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penjualan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inNamaBarang = findViewById(R.id.inNamaBarang);
        inJumlah = findViewById(R.id.inJumlah);
        inBayar = findViewById(R.id.inBayar);
        tTotal = findViewById(R.id.tTotal);
        tKembali = findViewById(R.id.tKembali);
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
                if (kembali >= 0) {
                    PenjualanEntity data = new PenjualanEntity((BarangEntity) inNamaBarang.getSelectedItem(), jml, byr, total, kembali);
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().insertPenjualan(data);
                    int stokBaru = data.getBarang().getStok() - Integer.parseInt(inJumlah.getText().toString());
                    data.getBarang().setStok(stokBaru >= 0 ? stokBaru : 0);
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().updateBarang(data.getBarang());
                    Toast.makeText(InputPenjualanActivity.this, "Berhasil simpan penjualan", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(InputPenjualanActivity.this, "Uang bayar kurang", Toast.LENGTH_SHORT).show();
            }
        });
        barangArrayAdapter = new ArrayAdapter<BarangEntity>
                (this, android.R.layout.simple_spinner_item,
                        SellingSalesDB.getInstance(getApplicationContext()).getDao().getAllBarangInStok());
        barangArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inNamaBarang.setAdapter(barangArrayAdapter);
        inJumlah.addTextChangedListener(textWatcher);
        inBayar.addTextChangedListener(textWatcher);
        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("id")) {
            item = SellingSalesDB.getInstance(getApplicationContext()).getDao().getPenjualan(b.getInt("id"));
            setItem();
        }
    }

    private void setItem() {
        inNamaBarang.setSelection(barangArrayAdapter.getPosition(item.getBarang()));
        inJumlah.setText(item.getJumlah() + "");
        inBayar.setText(item.getBayar() + "");
        tTotal.setText("Total: " + item.getTotal());
        tKembali.setText("Kembali: " + item.getKembali());
        inNamaBarang.setEnabled(false);
        inJumlah.setEnabled(false);
        inBayar.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
