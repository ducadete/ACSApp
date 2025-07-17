package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dtech.acsapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroCidadaoStep1Fragment extends Fragment {

    private CadastroCidadaoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroCidadaoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- Componentes da UI ---
        TextInputEditText inputCpfCns = view.findViewById(R.id.input_cpf_cns);
        TextInputEditText inputNomeCompleto = view.findViewById(R.id.input_nome_completo);
        TextInputEditText inputNomeSocial = view.findViewById(R.id.input_nome_social);
        RadioGroup radioGroupSexo = view.findViewById(R.id.radio_group_sexo);
        RadioGroup radioGroupResponsavel = view.findViewById(R.id.radio_group_responsavel);
        TextInputEditText inputDataNascimento = view.findViewById(R.id.input_data_nascimento);
        AutoCompleteTextView inputRacaCor = view.findViewById(R.id.input_raca_cor);
        AutoCompleteTextView inputEtnia = view.findViewById(R.id.input_etnia);

        // --- Popula Dropdowns ---
        populateDropdown(inputRacaCor, new String[]{"Branca", "Preta", "Parda", "Amarela", "Indígena"});
        populateDropdown(inputEtnia, new String[]{"Tupi", "Guarani", "Não se aplica"});

        // --- Conecta a UI ao ViewModel ---
        addTextWatcher(inputCpfCns, text -> viewModel.cpf.setValue(text));
        addTextWatcher(inputNomeCompleto, text -> viewModel.nomeCompleto.setValue(text));
        addTextWatcher(inputNomeSocial, text -> viewModel.nomeSocial.setValue(text));
        addTextWatcher(inputDataNascimento, text -> viewModel.dataNascimento.setValue(text));

        radioGroupSexo.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = view.findViewById(checkedId);
            viewModel.sexo.setValue(selected.getText().toString());
        });

        radioGroupResponsavel.setOnCheckedChangeListener((group, checkedId) -> viewModel.responsavelFamiliar.setValue(checkedId == R.id.radio_responsavel_sim));

        inputRacaCor.setOnItemClickListener((parent, v, position, id) -> viewModel.racaOuCor.setValue(parent.getItemAtPosition(position).toString()));
        inputEtnia.setOnItemClickListener((parent, v, position, id) -> viewModel.etnia.setValue(parent.getItemAtPosition(position).toString()));
    }

    // Métodos auxiliares
    private void populateDropdown(AutoCompleteTextView dropdown, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);
    }
    private void addTextWatcher(TextInputEditText editText, OnTextChanged listener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { listener.onChanged(s.toString()); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
    interface OnTextChanged { void onChanged(String text); }
}