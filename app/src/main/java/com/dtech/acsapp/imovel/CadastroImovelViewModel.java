package com.dtech.acsapp.imovel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CadastroImovelViewModel extends ViewModel {

    // Usamos MutableLiveData para que a UI possa observar as mudanças nos dados.
    // --- Dados da Etapa 1 ---
    public final MutableLiveData<String> tipoImovel = new MutableLiveData<>();
    public final MutableLiveData<String> nomeLogradouro = new MutableLiveData<>();
    public final MutableLiveData<String> numero = new MutableLiveData<>();
    public final MutableLiveData<Boolean> semNumero = new MutableLiveData<>(false); // Começa como falso
    public final MutableLiveData<String> complemento = new MutableLiveData<>();
    public final MutableLiveData<String> pontoReferencia = new MutableLiveData<>();


    // --- DADOS DA ETAPA 2 (ADICIONADOS AGORA) ---
    public final MutableLiveData<String> localizacao = new MutableLiveData<>();
    public final MutableLiveData<String> situacaoMoradia = new MutableLiveData<>();
    public final MutableLiveData<String> tipoAcesso = new MutableLiveData<>();
    public final MutableLiveData<String> tipoDomicilio = new MutableLiveData<>();
    public final MutableLiveData<String> numeroComodos = new MutableLiveData<>();


    // --- DADOS DA ETAPA 3 (ADICIONADOS AGORA) ---
    public final MutableLiveData<String> materialParedes = new MutableLiveData<>();
    public final MutableLiveData<String> abastecimentoAgua = new MutableLiveData<>();
    public final MutableLiveData<String> aguaConsumo = new MutableLiveData<>();
    public final MutableLiveData<String> escoamentoBanheiro = new MutableLiveData<>();
    public final MutableLiveData<Boolean> possuiEnergiaEletrica = new MutableLiveData<>();


}
