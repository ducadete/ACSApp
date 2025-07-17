package com.dtech.acsapp.logradouro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logradouro_table") // Define o nome da tabela no banco de dados
public class Logradouro {

    @PrimaryKey(autoGenerate = true) // Define o ID como chave primária autoincrementável
    private int id;

    @ColumnInfo(name = "nome_logradouro") // Define o nome da coluna
    private String nome;

    @ColumnInfo(name = "imoveis_count")
    private int imoveisCount;

    // Construtor é necessário para o Room
    public Logradouro(String nome, int imoveisCount) {
        this.nome = nome;
        this.imoveisCount = imoveisCount;
    }

    // --- Getters e Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getImoveisCount() { return imoveisCount; }
    public void setImoveisCount(int imoveisCount) { this.imoveisCount = imoveisCount; }
}