package com.dtech.acsapp.imovel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.dtech.acsapp.logradouro.Logradouro;

@Entity(tableName = "imovel_table",
        foreignKeys = @ForeignKey(entity = Logradouro.class,
                parentColumns = "id",
                childColumns = "logradouro_id",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "logradouro_id")})
public class Imovel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "logradouro_id")
    private int logradouroId;

    // --- Campos da Etapa 1 ---
    private String tipoImovel;
    private String nomeLogradouro;
    private String numero;
    private boolean semNumero;
    private String complemento;
    private String pontoReferencia;

    // --- Campos da Etapa 2 ---
    private String localizacao;
    private String situacaoMoradia;
    private String tipoAcesso;
    private String tipoDomicilio;
    private String numeroComodos;

    // --- Campos da Etapa 3 ---
    private String materialParedes;
    private String abastecimentoAgua;
    private String aguaConsumo;
    private String escoamentoBanheiro;
    private Boolean possuiEnergiaEletrica;

    // Construtor vazio (necessário para o Room em alguns casos)
    public Imovel() {}

    // Getters e Setters para todos os campos
    // (Pressione Alt + Insert no Android Studio para gerar automaticamente)

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getLogradouroId() { return logradouroId; }
    public void setLogradouroId(int logradouroId) { this.logradouroId = logradouroId; }
    public String getTipoImovel() { return tipoImovel; }
    public void setTipoImovel(String tipoImovel) { this.tipoImovel = tipoImovel; }
    public String getNomeLogradouro() { return nomeLogradouro; }
    public void setNomeLogradouro(String nomeLogradouro) { this.nomeLogradouro = nomeLogradouro; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public boolean isSemNumero() { return semNumero; }
    public void setSemNumero(boolean semNumero) { this.semNumero = semNumero; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getPontoReferencia() { return pontoReferencia; }
    public void setPontoReferencia(String pontoReferencia) { this.pontoReferencia = pontoReferencia; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getSituacaoMoradia() { return situacaoMoradia; }
    public void setSituacaoMoradia(String situacaoMoradia) { this.situacaoMoradia = situacaoMoradia; }
    public String getTipoAcesso() { return tipoAcesso; }
    public void setTipoAcesso(String tipoAcesso) { this.tipoAcesso = tipoAcesso; }
    public String getTipoDomicilio() { return tipoDomicilio; }
    public void setTipoDomicilio(String tipoDomicilio) { this.tipoDomicilio = tipoDomicilio; }
    public String getNumeroComodos() { return numeroComodos; }
    public void setNumeroComodos(String numeroComodos) { this.numeroComodos = numeroComodos; }
    public String getMaterialParedes() { return materialParedes; }
    public void setMaterialParedes(String materialParedes) { this.materialParedes = materialParedes; }
    public String getAbastecimentoAgua() { return abastecimentoAgua; }
    public void setAbastecimentoAgua(String abastecimentoAgua) { this.abastecimentoAgua = abastecimentoAgua; }
    public String getAguaConsumo() { return aguaConsumo; }
    public void setAguaConsumo(String aguaConsumo) { this.aguaConsumo = aguaConsumo; }
    public String getEscoamentoBanheiro() { return escoamentoBanheiro; }
    public void setEscoamentoBanheiro(String escoamentoBanheiro) { this.escoamentoBanheiro = escoamentoBanheiro; }
    public Boolean getPossuiEnergiaEletrica() { return possuiEnergiaEletrica; }
    public void setPossuiEnergiaEletrica(Boolean possuiEnergiaEletrica) { this.possuiEnergiaEletrica = possuiEnergiaEletrica; }
}