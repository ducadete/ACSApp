package com.dtech.acsapp.cidadao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cidadao_table")
public class Cidadao {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // --- Etapa 1: Identificação (Parte 1) ---
    private String cpf;
    private String cns;
    private String nomeCompleto;
    private String nomeSocial;
    private String dataNascimento;
    private String sexo;
    private boolean responsavelFamiliar;
    private String racaOuCor;
    private String etnia;

    // --- Etapa 2: Identificação (Parte 2) ---
    private String telefoneCelular;
    private String email;
    private String nomeMae;
    private String nomePai;
    private String nis;
    private String nacionalidade;
    private String paisNascimento;
    private String municipioNascimento;
    private String ufNascimento;

    // --- Etapa 3: Sociodemográfico (Parte 1) ---
    private boolean frequentaEscola;
    private String grauInstrucao;
    private String situacaoMercadoTrabalho;
    private String ocupacao;
    private String parentescoResponsavel;

    // --- Etapa 4: Sociodemográfico (Parte 2) ---
    private boolean possuiDeficiencia;
    private boolean frequentaCuidador;
    private boolean participaGrupoComunitario;
    private boolean possuiPlanoSaude;
    private boolean pertenceComunidadeTradicional;
    private String nomeComunidade;
    private String orientacaoSexual;
    private String identidadeGenero;

    // --- Etapa 5: Condições de Saúde ---
    private String peso; // "Abaixo", "Adequado", "Acima"
    private boolean doencaRespiratoria;
    private boolean problemasRins;
    private boolean doencaCardiaca;
    private boolean usaPlantasMedicinais;
    private boolean teveInternacao;
    private String causaInternacao;

    // --- Etapa 6: Condições Gerais ---
    private boolean dependenteAlcool;
    private boolean naoEstaDomiciliado;
    private boolean dependenteDrogas;
    private boolean fumante;
    private boolean estaAcamado;
    private boolean temHanseniase;
    private boolean teveAvcDerrame;
    private boolean temHipertensao;
    private boolean temTeveCancer;
    private boolean teveInfarto;
    private boolean temDiabetes;
    private boolean usaPraticasIntegrativas;

    // Construtor vazio para o Room
    public Cidadao() {}

    // --- GETTERS E SETTERS GERADOS PARA TODOS OS CAMPOS ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCns() { return cns; }
    public void setCns(String cns) { this.cns = cns; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getNomeSocial() { return nomeSocial; }
    public void setNomeSocial(String nomeSocial) { this.nomeSocial = nomeSocial; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public boolean isResponsavelFamiliar() { return responsavelFamiliar; }
    public void setResponsavelFamiliar(boolean responsavelFamiliar) { this.responsavelFamiliar = responsavelFamiliar; }

    public String getRacaOuCor() { return racaOuCor; }
    public void setRacaOuCor(String racaOuCor) { this.racaOuCor = racaOuCor; }

    public String getEtnia() { return etnia; }
    public void setEtnia(String etnia) { this.etnia = etnia; }

    public String getTelefoneCelular() { return telefoneCelular; }
    public void setTelefoneCelular(String telefoneCelular) { this.telefoneCelular = telefoneCelular; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNomeMae() { return nomeMae; }
    public void setNomeMae(String nomeMae) { this.nomeMae = nomeMae; }

    public String getNomePai() { return nomePai; }
    public void setNomePai(String nomePai) { this.nomePai = nomePai; }

    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getPaisNascimento() { return paisNascimento; }
    public void setPaisNascimento(String paisNascimento) { this.paisNascimento = paisNascimento; }

    public String getMunicipioNascimento() { return municipioNascimento; }
    public void setMunicipioNascimento(String municipioNascimento) { this.municipioNascimento = municipioNascimento; }

    public String getUfNascimento() { return ufNascimento; }
    public void setUfNascimento(String ufNascimento) { this.ufNascimento = ufNascimento; }

    public boolean isFrequentaEscola() { return frequentaEscola; }
    public void setFrequentaEscola(boolean frequentaEscola) { this.frequentaEscola = frequentaEscola; }

    public String getGrauInstrucao() { return grauInstrucao; }
    public void setGrauInstrucao(String grauInstrucao) { this.grauInstrucao = grauInstrucao; }

    public String getSituacaoMercadoTrabalho() { return situacaoMercadoTrabalho; }
    public void setSituacaoMercadoTrabalho(String situacaoMercadoTrabalho) { this.situacaoMercadoTrabalho = situacaoMercadoTrabalho; }

    public String getOcupacao() { return ocupacao; }
    public void setOcupacao(String ocupacao) { this.ocupacao = ocupacao; }

    public String getParentescoResponsavel() { return parentescoResponsavel; }
    public void setParentescoResponsavel(String parentescoResponsavel) { this.parentescoResponsavel = parentescoResponsavel; }

    public boolean isPossuiDeficiencia() { return possuiDeficiencia; }
    public void setPossuiDeficiencia(boolean possuiDeficiencia) { this.possuiDeficiencia = possuiDeficiencia; }

    public boolean isFrequentaCuidador() { return frequentaCuidador; }
    public void setFrequentaCuidador(boolean frequentaCuidador) { this.frequentaCuidador = frequentaCuidador; }

    public boolean isParticipaGrupoComunitario() { return participaGrupoComunitario; }
    public void setParticipaGrupoComunitario(boolean participaGrupoComunitario) { this.participaGrupoComunitario = participaGrupoComunitario; }

    public boolean isPossuiPlanoSaude() { return possuiPlanoSaude; }
    public void setPossuiPlanoSaude(boolean possuiPlanoSaude) { this.possuiPlanoSaude = possuiPlanoSaude; }

    public boolean isPertenceComunidadeTradicional() { return pertenceComunidadeTradicional; }
    public void setPertenceComunidadeTradicional(boolean pertenceComunidadeTradicional) { this.pertenceComunidadeTradicional = pertenceComunidadeTradicional; }

    public String getNomeComunidade() { return nomeComunidade; }
    public void setNomeComunidade(String nomeComunidade) { this.nomeComunidade = nomeComunidade; }

    public String getOrientacaoSexual() { return orientacaoSexual; }
    public void setOrientacaoSexual(String orientacaoSexual) { this.orientacaoSexual = orientacaoSexual; }

    public String getIdentidadeGenero() { return identidadeGenero; }
    public void setIdentidadeGenero(String identidadeGenero) { this.identidadeGenero = identidadeGenero; }

    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }

    public boolean isDoencaRespiratoria() { return doencaRespiratoria; }
    public void setDoencaRespiratoria(boolean doencaRespiratoria) { this.doencaRespiratoria = doencaRespiratoria; }

    public boolean isProblemasRins() { return problemasRins; }
    public void setProblemasRins(boolean problemasRins) { this.problemasRins = problemasRins; }

    public boolean isDoencaCardiaca() { return doencaCardiaca; }
    public void setDoencaCardiaca(boolean doencaCardiaca) { this.doencaCardiaca = doencaCardiaca; }

    public boolean isUsaPlantasMedicinais() { return usaPlantasMedicinais; }
    public void setUsaPlantasMedicinais(boolean usaPlantasMedicinais) { this.usaPlantasMedicinais = usaPlantasMedicinais; }

    public boolean isTeveInternacao() { return teveInternacao; }
    public void setTeveInternacao(boolean teveInternacao) { this.teveInternacao = teveInternacao; }

    public String getCausaInternacao() { return causaInternacao; }
    public void setCausaInternacao(String causaInternacao) { this.causaInternacao = causaInternacao; }

    public boolean isDependenteAlcool() { return dependenteAlcool; }
    public void setDependenteAlcool(boolean dependenteAlcool) { this.dependenteAlcool = dependenteAlcool; }

    public boolean isNaoEstaDomiciliado() { return naoEstaDomiciliado; }
    public void setNaoEstaDomiciliado(boolean naoEstaDomiciliado) { this.naoEstaDomiciliado = naoEstaDomiciliado; }

    public boolean isDependenteDrogas() { return dependenteDrogas; }
    public void setDependenteDrogas(boolean dependenteDrogas) { this.dependenteDrogas = dependenteDrogas; }

    public boolean isFumante() { return fumante; }
    public void setFumante(boolean fumante) { this.fumante = fumante; }

    public boolean isEstaAcamado() { return estaAcamado; }
    public void setEstaAcamado(boolean estaAcamado) { this.estaAcamado = estaAcamado; }

    public boolean isTemHanseniase() { return temHanseniase; }
    public void setTemHanseniase(boolean temHanseniase) { this.temHanseniase = temHanseniase; }

    public boolean isTeveAvcDerrame() { return teveAvcDerrame; }
    public void setTeveAvcDerrame(boolean teveAvcDerrame) { this.teveAvcDerrame = teveAvcDerrame; }

    public boolean isTemHipertensao() { return temHipertensao; }
    public void setTemHipertensao(boolean temHipertensao) { this.temHipertensao = temHipertensao; }

    public boolean isTemTeveCancer() { return temTeveCancer; }
    public void setTemTeveCancer(boolean temTeveCancer) { this.temTeveCancer = temTeveCancer; }

    public boolean isTeveInfarto() { return teveInfarto; }
    public void setTeveInfarto(boolean teveInfarto) { this.teveInfarto = teveInfarto; }

    public boolean isTemDiabetes() { return temDiabetes; }
    public void setTemDiabetes(boolean temDiabetes) { this.temDiabetes = temDiabetes; }

    public boolean isUsaPraticasIntegrativas() { return usaPraticasIntegrativas; }
    public void setUsaPraticasIntegrativas(boolean usaPraticasIntegrativas) { this.usaPraticasIntegrativas = usaPraticasIntegrativas; }
}