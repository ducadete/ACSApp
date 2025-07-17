package com.dtech.acsapp.logradouro;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LogradouroDao {

    @Insert
    void insert(Logradouro logradouro);

    @Query("SELECT * FROM logradouro_table ORDER BY nome_logradouro ASC")
    LiveData<List<Logradouro>> getAllLogradouros();

    // ... outros métodos ...

    // --- NOVO MÉTODO ADICIONADO ---
    @Delete
    void delete(Logradouro logradouro);

    @Update
    void update(Logradouro logradouro);

    @Query("SELECT * FROM logradouro_table WHERE id = :logradouroId LIMIT 1")
    Logradouro getLogradouroById(int logradouroId);
}