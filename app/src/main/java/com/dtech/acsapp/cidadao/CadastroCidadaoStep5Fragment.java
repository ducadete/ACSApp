package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dtech.acsapp.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroCidadaoStep5Fragment extends Fragment {

    private CadastroCidadaoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroCidadaoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup radioGroupPeso = view.findViewById(R.id.radio_group_peso);
        TextInputLayout layoutCausaInternacao = view.findViewById(R.id.layout_causa_internacao);
        TextInputEditText inputCausaInternacao = view.findViewById(R.id.input_causa_internacao);

        radioGroupPeso.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = view.findViewById(checkedId);
            viewModel.peso.setValue(selected.getText().toString());
        });

        setupToggleGroup(view, R.id.toggle_doenca_respiratoria, R.id.btn_resp_sim, R.id.btn_resp_nao, isYes -> viewModel.doencaRespiratoria.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_rins, R.id.btn_rins_sim, R.id.btn_rins_nao, isYes -> viewModel.problemasRins.setValue(isYes));
        setupToggleGroup(view, R.id.toggle_cardiaca, R.id.btn_cardiaca_sim, R.id.btn_cardiaca_nao, isYes -> viewModel.doencaCardiaca.setValue(isYes));

        MaterialButtonToggleGroup toggleInternacao = view.findViewById(R.id.toggle_internacao);
        toggleInternacao.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                boolean teveInternacao = checkedId == R.id.btn_internacao_sim;
                viewModel.teveInternacao.setValue(teveInternacao);
                layoutCausaInternacao.setVisibility(teveInternacao ? View.VISIBLE : View.GONE);
                if (!teveInternacao) inputCausaInternacao.setText("");
            }
        });

        inputCausaInternacao.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { viewModel.causaInternacao.setValue(s.toString()); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void setupToggleGroup(View rootView, int groupId, int btnSimId, int btnNaoId, OnToggleChecked listener) {
        MaterialButtonToggleGroup toggleGroup = rootView.findViewById(groupId);
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if(isChecked) listener.onChecked(checkedId == btnSimId);
        });
    }
    interface OnToggleChecked { void onChecked(boolean isYes); }
}