package com.ihanfarhan.sellingsales.db;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barang")
public class BarangEntity {

    @PrimaryKey(autoGenerate = true)
    private int idBarang;

    private String namaBarang;

    @Embedded
    private KategoriBarangEntity kategoriBarang;

    private int harga;

    private int stok;

    public BarangEntity(String namaBarang, KategoriBarangEntity kategoriBarang, int harga, int stok) {
        this.namaBarang = namaBarang;
        this.kategoriBarang = kategoriBarang;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public KategoriBarangEntity getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(KategoriBarangEntity kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @NonNull
    @Override
    public String toString() {
        return getNamaBarang();
    }
}
