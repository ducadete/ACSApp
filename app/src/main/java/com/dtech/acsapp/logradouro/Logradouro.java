package com.dtech.acsapp.logradouro;

public class Logradouro {
    private int id;
    private String nome;
    private int imoveisCount;

    public Logradouro(int id, String nome, int imoveisCount) {
        this.id = id;
        this.nome = nome;
        this.imoveisCount = imoveisCount;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getImoveisCount() {
        return imoveisCount;
    }
}
