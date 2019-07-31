package com.ihanfarhan.sellingsales.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "supplier")
public class SupplierEntity {

    @PrimaryKey(autoGenerate = true)
    private int idSupplier;

    private String namaSupplier;

    private String alamat;

    private String telp;

    public SupplierEntity(String namaSupplier, String alamat, String telp) {
        this.namaSupplier = namaSupplier;
        this.alamat = alamat;
        this.telp = telp;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    @NonNull
    @Override
    public String toString() {
        return getNamaSupplier();
    }
}
