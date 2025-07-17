package com.dtech.acsapp.imovel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.dtech.acsapp.AppDatabase;
import com.dtech.acsapp.R;
import com.dtech.acsapp.cidadao.CadastroCidadaoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ImovelDetailFragment extends Fragment {

    private AppDatabase db;
    private int imovelId;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(getContext());
        if (getArguments() != null) {
            imovelId = getArguments().getInt("imovelId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_imovel_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbarImovelDetail);
        toolbar.setNavigationOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());
        loadImovelDetails(view);
        FloatingActionButton fabAddFamilia = view.findViewById(R.id.fabAddFamilia);
        fabAddFamilia.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CadastroCidadaoActivity.class);
            // Futuramente, passaremos o ID do imóvel ou da família aqui
            startActivity(intent);
        });
    }


    private void loadImovelDetails(View view) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Imovel imovel = db.imovelDao().getImovelById(imovelId);
            mainThreadHandler.post(() -> {
                if (imovel != null) {
                    populateUI(view, imovel);
                }
            });
        });
    }

    private void populateUI(View view, Imovel imovel) {
        Toolbar toolbar = view.findViewById(R.id.toolbarImovelDetail);
        // Não precisamos mais de uma variável 'endereco' separada

        TextView complemento = view.findViewById(R.id.detail_complemento);
        TextView pontoReferencia = view.findViewById(R.id.detail_ponto_referencia);
        TextView tipoImovel = view.findViewById(R.id.detail_tipo_imovel);
        TextView localizacao = view.findViewById(R.id.detail_localizacao);
        TextView situacao = view.findViewById(R.id.detail_situacao);
        TextView tipoAcesso = view.findViewById(R.id.detail_tipo_acesso);
        TextView tipoDomicilio = view.findViewById(R.id.detail_tipo_domicilio);
        TextView material = view.findViewById(R.id.detail_material);
        TextView comodos = view.findViewById(R.id.detail_comodos);
        TextView abastecimentoAgua = view.findViewById(R.id.detail_abastecimento_agua);
        TextView aguaConsumo = view.findViewById(R.id.detail_agua_consumo);
        TextView esgoto = view.findViewById(R.id.detail_esgoto);
        TextView energia = view.findViewById(R.id.detail_energia);

        // Preenche os campos com os dados
        String tituloToolbar = (imovel.getNomeLogradouro() != null ? imovel.getNomeLogradouro() : "") + ", Nº " + imovel.getNumero();
        toolbar.setTitle(tituloToolbar);

        tipoImovel.setText("Tipo: " + (imovel.getTipoImovel() != null ? imovel.getTipoImovel() : "Não informado"));
        complemento.setText("Complemento: " + (imovel.getComplemento() != null ? imovel.getComplemento() : "Não informado"));
        pontoReferencia.setText("Referência: " + (imovel.getPontoReferencia() != null ? imovel.getPontoReferencia() : "Não informado"));
        localizacao.setText(imovel.getLocalizacao());
        situacao.setText(imovel.getSituacaoMoradia());
        tipoAcesso.setText(imovel.getTipoAcesso());
        tipoDomicilio.setText(imovel.getTipoDomicilio());
        material.setText(imovel.getMaterialParedes());
        comodos.setText(imovel.getNumeroComodos());
        abastecimentoAgua.setText(imovel.getAbastecimentoAgua());
        aguaConsumo.setText(imovel.getAguaConsumo());
        esgoto.setText(imovel.getEscoamentoBanheiro());

        if (imovel.getPossuiEnergiaEletrica() != null) {
            energia.setText(imovel.getPossuiEnergiaEletrica() ? "Sim" : "Não");
        } else {
            energia.setText("Não informado");
        }
    }
}