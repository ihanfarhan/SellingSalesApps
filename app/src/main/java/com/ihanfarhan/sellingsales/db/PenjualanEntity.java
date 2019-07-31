package com.ihanfarhan.sellingsales.db;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "penjualan")
public class PenjualanEntity {

    @PrimaryKey(autoGenerate = true)
    private int idPenjualan;

    @Embedded
    private BarangEntity barang;

    private int jumlah;

    private int bayar;

    private int total;

    private int kembali;

    public PenjualanEntity(BarangEntity barang, int jumlah, int bayar, int total, int kembali) {
        this.barang = barang;
        this.jumlah = jumlah;
        this.bayar = bayar;
        this.total = total;
        this.kembali = kembali;
    }

    public int getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
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

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getKembali() {
        return kembali;
    }

    public void setKembali(int kembali) {
        this.kembali = kembali;
    }
}
