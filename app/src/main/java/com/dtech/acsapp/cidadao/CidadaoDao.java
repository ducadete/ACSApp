package com.dtech.acsapp.cidadao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CidadaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cidadao cidadao);

    @Query("SELECT * FROM cidadao_table ORDER BY nomeCompleto ASC")
    LiveData<List<Cidadao>> getAllCidadaos();

    // Futuramente, podemos adicionar buscas por CPF, nome, etc.
}