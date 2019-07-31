package com.ihanfarhan.sellingsales.db;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "pembelian")
public class PembelianEntity {

    @PrimaryKey(autoGenerate = true)
    private int idPembelian;

    @Embedded
    private SupplierEntity supplier;

    @Embedded
    private BarangEntity barang;

    private int jumlah;

    private String date;

    public PembelianEntity(SupplierEntity supplier, BarangEntity barang, int jumlah, String date) {
        this.supplier = supplier;
        this.barang = barang;
        this.jumlah = jumlah;
        this.date = date;
    }

    public int getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(int idPembelian) {
        this.idPembelian = idPembelian;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public BarangEntity getBarang() {
        return barang;
    }

    public void setBarang(BarangEntity barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
