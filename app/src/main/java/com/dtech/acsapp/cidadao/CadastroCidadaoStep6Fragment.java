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

public class CadastroCidadaoStep6Fragment extends Fragment {

    private CadastroCidadaoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroCidadaoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step6, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToggleGroup(view, R.id.toggle_alcool, R.id.btn_alcool_sim, R.id.btn_alcool_nao, isYes -> viewModel.dependenteAlcool.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_drogas, R.id.btn_drogas_sim, R.id.btn_drogas_nao, isYes -> viewModel.dependenteDrogas.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_fumante, R.id.btn_fumante_sim, R.id.btn_fumante_nao, isYes -> viewModel.fumante.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_hipertensao, R.id.btn_hipertensao_sim, R.id.btn_hipertensao_nao, isYes -> viewModel.temHipertensao.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_diabetes, R.id.btn_diabetes_sim, R.id.btn_diabetes_nao, isYes -> viewModel.temDiabetes.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_avc, R.id.btn_avc_sim, R.id.btn_avc_nao, isYes -> viewModel.teveAvcDerrame.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_infarto, R.id.btn_infarto_sim, R.id.btn_infarto_nao, isYes -> viewModel.teveInfarto.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_hanseniase, R.id.btn_hanseniase_sim, R.id.btn_hanseniase_nao, isYes -> viewModel.temHanseniase.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_cancer, R.id.btn_cancer_sim, R.id.btn_cancer_nao, isYes -> viewModel.temTeveCancer.setValue(isYes));
    }

    private void setupToggleGroup(View rootView, int groupId, int btnSimId, int btnNaoId, OnToggleChecked listener) {
        MaterialButtonToggleGroup toggleGroup = rootView.findViewById(groupId);
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if(isChecked) listener.onChecked(checkedId == btnSimId);
        });
    }
    interface OnToggleChecked { void onChecked(boolean isYes); }
}