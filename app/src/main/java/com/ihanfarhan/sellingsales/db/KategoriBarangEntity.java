package com.ihanfarhan.sellingsales.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "kategori_barang")
public class KategoriBarangEntity {

    @PrimaryKey(autoGenerate = true)
    private int idKategoriBarang;

    private String namaKategoriBarang;

    public KategoriBarangEntity(String namaKategoriBarang) {
        this.namaKategoriBarang = namaKategoriBarang;
    }

    public int getIdKategoriBarang() {
        return idKategoriBarang;
    }

    public void setIdKategoriBarang(int idKategoriBarang) {
        this.idKategoriBarang = idKategoriBarang;
    }

    public String getNamaKategoriBarang() {
        return namaKategoriBarang;
    }

    public void setNamaKategoriBarang(String namaKategoriBarang) {
        this.namaKategoriBarang = namaKategoriBarang;
    }

    @NonNull
    @Override
    public String toString() {
        return getNamaKategoriBarang();
    }

}
