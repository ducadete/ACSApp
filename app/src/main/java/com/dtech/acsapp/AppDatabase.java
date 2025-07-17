package com.dtech.acsapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.dtech.acsapp.logradouro.Logradouro;
import com.dtech.acsapp.logradouro.LogradouroDao;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.dtech.acsapp.imovel.Imovel;
import com.dtech.acsapp.imovel.ImovelDao;
import com.dtech.acsapp.cidadao.Cidadao;
import com.dtech.acsapp.cidadao.CidadaoDao;

@Database(entities = {Logradouro.class, Imovel.class, Cidadao.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LogradouroDao logradouroDao();
    public abstract ImovelDao imovelDao();
    public abstract CidadaoDao cidadaoDao();
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "acs_app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}