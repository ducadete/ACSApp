package com.dtech.acsapp.cidadao;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CadastroCidadaoViewModel extends ViewModel {

    // Etapa 1
    public final MutableLiveData<String> cpf = new MutableLiveData<>();
    public final MutableLiveData<String> cns = new MutableLiveData<>();
    public final MutableLiveData<String> nomeCompleto = new MutableLiveData<>();
    public final MutableLiveData<String> nomeSocial = new MutableLiveData<>();
    public final MutableLiveData<String> dataNascimento = new MutableLiveData<>();
    public final MutableLiveData<String> sexo = new MutableLiveData<>();
    public final MutableLiveData<Boolean> responsavelFamiliar = new MutableLiveData<>();
    public final MutableLiveData<String> racaOuCor = new MutableLiveData<>();
    public final MutableLiveData<String> etnia = new MutableLiveData<>();

    // Etapa 2
    public final MutableLiveData<String> telefoneCelular = new MutableLiveData<>();
    public final MutableLiveData<String> email = new MutableLiveData<>();
    public final MutableLiveData<String> nomeMae = new MutableLiveData<>();
    public final MutableLiveData<String> nomePai = new MutableLiveData<>();
    public final MutableLiveData<Boolean> desconhecePai = new MutableLiveData<>(false);
    public final MutableLiveData<String> nis = new MutableLiveData<>();

    // Etapa 3
    public final MutableLiveData<Boolean> frequentaEscola = new MutableLiveData<>();
    public final MutableLiveData<String> grauInstrucao = new MutableLiveData<>();
    public final MutableLiveData<String> situacaoMercadoTrabalho = new MutableLiveData<>();
    public final MutableLiveData<String> ocupacao = new MutableLiveData<>();
    public final MutableLiveData<String> parentescoResponsavel = new MutableLiveData<>();

    // Etapa 4
    public final MutableLiveData<Boolean> possuiDeficiencia = new MutableLiveData<>();
    public final MutableLiveData<Boolean> frequentaCuidador = new MutableLiveData<>();
    public final MutableLiveData<Boolean> participaGrupoComunitario = new MutableLiveData<>();
    public final MutableLiveData<Boolean> possuiPlanoSaude = new MutableLiveData<>();

    // Etapa 5
    public final MutableLiveData<String> peso = new MutableLiveData<>();
    public final MutableLiveData<Boolean> doencaRespiratoria = new MutableLiveData<>();
    public final MutableLiveData<Boolean> problemasRins = new MutableLiveData<>();
    public final MutableLiveData<Boolean> doencaCardiaca = new MutableLiveData<>();
    public final MutableLiveData<Boolean> teveInternacao = new MutableLiveData<>();
    public final MutableLiveData<String> causaInternacao = new MutableLiveData<>();

    // Etapa 6
    public final MutableLiveData<Boolean> fumante = new MutableLiveData<>();
    public final MutableLiveData<Boolean> dependenteAlcool = new MutableLiveData<>();
    public final MutableLiveData<Boolean> dependenteDrogas = new MutableLiveData<>();
    public final MutableLiveData<Boolean> temHipertensao = new MutableLiveData<>();
    public final MutableLiveData<Boolean> temDiabetes = new MutableLiveData<>();
    public final MutableLiveData<Boolean> teveAvcDerrame = new MutableLiveData<>();
    public final MutableLiveData<Boolean> teveInfarto = new MutableLiveData<>();
    public final MutableLiveData<Boolean> temHanseniase = new MutableLiveData<>();
    public final MutableLiveData<Boolean> temTeveCancer = new MutableLiveData<>();
}
