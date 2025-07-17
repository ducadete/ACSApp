package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dtech.acsapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroCidadaoStep2Fragment extends Fragment {

    private CadastroCidadaoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroCidadaoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText inputTelefone = view.findViewById(R.id.input_telefone);
        TextInputEditText inputEmail = view.findViewById(R.id.input_email);
        TextInputEditText inputNomeMae = view.findViewById(R.id.input_nome_mae);
        TextInputEditText inputNomePai = view.findViewById(R.id.input_nome_pai);
        CheckBox checkboxDesconhecePai = view.findViewById(R.id.checkbox_desconhece_pai);
        TextInputEditText inputNis = view.findViewById(R.id.input_nis);

        // Conecta a UI ao ViewModel
        addTextWatcher(inputTelefone, text -> viewModel.telefoneCelular.setValue(text));
        addTextWatcher(inputEmail, text -> viewModel.email.setValue(text));
        addTextWatcher(inputNomeMae, text -> viewModel.nomeMae.setValue(text));
        addTextWatcher(inputNomePai, text -> viewModel.nomePai.setValue(text));
        addTextWatcher(inputNis, text -> viewModel.nis.setValue(text));

        checkboxDesconhecePai.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.desconhecePai.setValue(isChecked);
            inputNomePai.setEnabled(!isChecked);
            if (isChecked) inputNomePai.setText("");
        });
    }

    // Método auxiliar
    private void addTextWatcher(TextInputEditText editText, OnTextChanged listener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { listener.onChanged(s.toString()); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
    interface OnTextChanged { void onChanged(String text); }
}