package com.ihanfarhan.sellingsales.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;



@Dao
public interface SellingSalesDao {

    @Query("SELECT * FROM barang")
    List<BarangEntity> getAllBarang();

    @Query("SELECT * FROM barang WHERE stok > 0")
    List<BarangEntity> getAllBarangInStok();

    @Query("SELECT * FROM barang WHERE namaBarang LIKE :nama")
    List<BarangEntity> getAllBarang(String nama);

    @Query("SELECT * FROM barang WHERE idBarang = :id LIMIT 0,1")
    BarangEntity getBarang(int id);

    @Insert
    void insertBarang(BarangEntity data);

    @Update
    void updateBarang(BarangEntity data);

    @Query("SELECT * FROM kategori_barang")
    List<KategoriBarangEntity> getAllKategoriBarang();

    @Query("SELECT * FROM kategori_barang WHERE namaKategoriBarang LIKE :nama")
    List<KategoriBarangEntity> getAllKategoriBarang(String nama);

    @Query("SELECT * FROM kategori_barang WHERE idKategoriBarang = :id LIMIT 0,1")
    KategoriBarangEntity getKategoriBarang(int id);

    @Insert
    void insertKategoriBarang(KategoriBarangEntity data);

    @Update
    void updateKategoriBarang(KategoriBarangEntity data);

    @Query("SELECT * FROM supplier")
    List<SupplierEntity> getAllSupplier();

    @Query("SELECT * FROM supplier WHERE namaSupplier LIKE :nama")
    List<SupplierEntity> getAllSupplier(String nama);

    @Query("SELECT * FROM supplier WHERE idSupplier = :id LIMIT 0,1")
    SupplierEntity getSupplier(int id);

    @Insert
    void insertSupplier(SupplierEntity data);

    @Update
    void updateSupplier(SupplierEntity data);

    @Query("SELECT * FROM pembelian")
    List<PembelianEntity> getAllPembelian();

    @Query("SELECT * FROM pembelian WHERE namaBarang LIKE :nama OR namaKategoriBarang LIKE :nama OR namaSupplier LIKE :nama")
    List<PembelianEntity> getAllPembelian(String nama);

    @Query("SELECT * FROM pembelian WHERE idPembelian = :id LIMIT 0,1")
    PembelianEntity getPembelian(int id);

    @Insert
    void insertPembelian(PembelianEntity data);

    @Query("SELECT * FROM penjualan")
    List<PenjualanEntity> getAllPenjualan();

    @Query("SELECT * FROM penjualan WHERE namaBarang LIKE :nama OR namaKategoriBarang LIKE :nama")
    List<PenjualanEntity> getAllPenjualan(String nama);

    @Query("SELECT * FROM penjualan WHERE idPenjualan = :id LIMIT 0,1")
    PenjualanEntity getPenjualan(int id);

    @Insert
    void insertPenjualan(PenjualanEntity data);
}
