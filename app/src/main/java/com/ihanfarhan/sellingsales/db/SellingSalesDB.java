package com.ihanfarhan.sellingsales.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {BarangEntity.class, KategoriBarangEntity.class, PembelianEntity.class, PenjualanEntity.class, SupplierEntity.class}, version = 1)
public abstract class SellingSalesDB extends RoomDatabase {

    private static volatile SellingSalesDB instance;

    public static synchronized SellingSalesDB getInstance(Context context) {
        if (instance == null)
            instance = create(context);
        return instance;
    }

    private static SellingSalesDB create(Context context) {
        return Room.databaseBuilder(context, SellingSalesDB.class, "sellingsales_db").allowMainThreadQueries().build();
    }

    public abstract SellingSalesDao getDao();
}
