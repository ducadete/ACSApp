package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dtech.acsapp.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class CadastroCidadaoStep4Fragment extends Fragment {

    private CadastroCidadaoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroCidadaoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToggleGroup(view, R.id.toggle_deficiencia, R.id.btn_deficiencia_sim, R.id.btn_deficiencia_nao, (isChecked) -> viewModel.possuiDeficiencia.setValue(isChecked));
        setupToggleGroup(view, R.id.toggle_cuidador, R.id.btn_cuidador_sim, R.id.btn_cuidador_nao, (isChecked) -> viewModel.frequentaCuidador.setValue(isChecked));
        setupToggleGroup(view, R.id.toggle_grupo_comunitario, R.id.btn_grupo_sim, R.id.btn_grupo_nao, (isChecked) -> viewModel.participaGrupoComunitario.setValue(isChecked));
        setupToggleGroup(view, R.id.toggle_plano_saude, R.id.btn_plano_sim, R.id.btn_plano_nao, (isChecked) -> viewModel.possuiPlanoSaude.setValue(isChecked));
    }

    private void setupToggleGroup(View rootView, int groupId, int btnSimId, int btnNaoId, OnToggleChecked listener) {
        MaterialButtonToggleGroup toggleGroup = rootView.findViewById(groupId);
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if(isChecked) {
                listener.onChecked(checkedId == btnSimId);
            }
        });
    }
    interface OnToggleChecked { void onChecked(boolean isYes); }
}