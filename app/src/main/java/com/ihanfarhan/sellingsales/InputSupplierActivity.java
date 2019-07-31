package com.ihanfarhan.sellingsales;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ihanfarhan.sellingsales.db.SellingSalesDB;
import com.ihanfarhan.sellingsales.db.SupplierEntity;

public class InputSupplierActivity extends AppCompatActivity {

    private EditText inNamaSupplier, inAlamat, inTelp;
    private Button bBatal, bSimpan;
    private SupplierEntity item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_supplier);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inNamaSupplier = findViewById(R.id.inNamaSupplier);
        inAlamat = findViewById(R.id.inAlamat);
        inTelp = findViewById(R.id.inTelp);
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
                SupplierEntity data = new SupplierEntity(inNamaSupplier.getText().toString(),
                        inAlamat.getText().toString(),
                        inTelp.getText().toString());
                if (item != null) {
                    data.setIdSupplier(item.getIdSupplier());
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().updateSupplier(data);
                }
                else
                    SellingSalesDB.getInstance(getApplicationContext()).getDao().insertSupplier(data);
                Toast.makeText(InputSupplierActivity.this, "Berhasil simpan supplier", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("id")) {
            item = SellingSalesDB.getInstance(getApplicationContext()).getDao().getSupplier(b.getInt("id"));
            setItem();
        }
    }

    private void setItem() {
        inNamaSupplier.setText(item.getNamaSupplier());
        inAlamat.setText(item.getAlamat());
        inTelp.setText(item.getTelp());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
