package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.dtech.acsapp.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroCidadaoStep5Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButtonToggleGroup toggleInternacao = view.findViewById(R.id.toggle_internacao);
        TextInputLayout layoutCausaInternacao = view.findViewById(R.id.layout_causa_internacao);

        // Adiciona um listener para o grupo de botões da internação
        toggleInternacao.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.btn_internacao_sim) {
                    // Se o botão "Sim" for selecionado, mostra o campo de texto
                    layoutCausaInternacao.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.btn_internacao_nao) {
                    // Se o botão "Não" for selecionado, esconde o campo de texto
                    layoutCausaInternacao.setVisibility(View.GONE);
                }
            } else {
                // Se nenhum botão estiver selecionado, esconde o campo
                layoutCausaInternacao.setVisibility(View.GONE);
            }
        });
    }
}