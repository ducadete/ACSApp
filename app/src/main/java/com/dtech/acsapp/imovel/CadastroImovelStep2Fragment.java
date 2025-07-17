package com.dtech.acsapp.imovel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dtech.acsapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroImovelStep2Fragment extends Fragment {

    private CadastroImovelViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtém a mesma instância compartilhada do ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroImovelViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_imovel_step2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- ENCONTRA OS COMPONENTES ---
        RadioGroup radioGroupLocalizacao = view.findViewById(R.id.radio_group_localizacao);
        AutoCompleteTextView situacaoMoradiaDropdown = view.findViewById(R.id.input_situacao_moradia);
        AutoCompleteTextView tipoAcessoDropdown = view.findViewById(R.id.input_tipo_acesso);
        AutoCompleteTextView tipoDomicilioDropdown = view.findViewById(R.id.input_tipo_domicilio);
        TextInputEditText inputNumeroComodos = view.findViewById(R.id.input_numero_comodos);

        // --- POPULA OS DROPDOWNS ---
        populateDropdown(situacaoMoradiaDropdown, new String[]{"Próprio", "Financiado", "Alugado", "Arrendado", "Cedido"});
        populateDropdown(tipoAcessoDropdown, new String[]{"Pavimento", "Chão batido"});
        populateDropdown(tipoDomicilioDropdown, new String[]{"Casa", "Apartamento", "Cômodo"});

        // --- CONECTA A UI AO VIEWMODEL ---
        radioGroupLocalizacao.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_urbana) {
                viewModel.localizacao.setValue("Urbana");
            } else if (checkedId == R.id.radio_rural) {
                viewModel.localizacao.setValue("Rural");
            }
        });

        situacaoMoradiaDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.situacaoMoradia.setValue(parent.getItemAtPosition(position).toString()));
        tipoAcessoDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.tipoAcesso.setValue(parent.getItemAtPosition(position).toString()));
        tipoDomicilioDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.tipoDomicilio.setValue(parent.getItemAtPosition(position).toString()));

        inputNumeroComodos.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.numeroComodos.setValue(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void populateDropdown(AutoCompleteTextView dropdown, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);
    }
}