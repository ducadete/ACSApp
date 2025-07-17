package com.dtech.acsapp.imovel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImovelDao {

    @Insert
    void insert(Imovel imovel);

    // Query que busca todos os imóveis de um logradouro específico
    @Query("SELECT * FROM imovel_table WHERE logradouro_id = :logradouroId ORDER BY numero ASC")
    LiveData<List<Imovel>> getImoveisByLogradouro(int logradouroId);

    // Em imovel/ImovelDao.java
    @Query("SELECT COUNT(id) FROM imovel_table WHERE logradouro_id = :logradouroId")
    int countImoveisByLogradouro(int logradouroId);

    @Update
    void update(Imovel imovel);

    @Delete
    void delete(Imovel imovel);

    @Query("SELECT * FROM imovel_table WHERE id = :imovelId LIMIT 1")
    Imovel getImovelById(int imovelId);
}