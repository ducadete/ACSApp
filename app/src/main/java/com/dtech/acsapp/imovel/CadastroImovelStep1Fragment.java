package com.dtech.acsapp.imovel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider; // Importação para o ViewModel
import com.dtech.acsapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroImovelStep1Fragment extends Fragment {

    private CadastroImovelViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtém uma instância compartilhada do ViewModel ligada à Activity "mãe"
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroImovelViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_imovel_step1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- ENCONTRA OS COMPONENTES ---
        AutoCompleteTextView tipoImovelDropdown = view.findViewById(R.id.input_tipo_imovel);
        TextInputEditText inputNomeLogradouro = view.findViewById(R.id.input_nome_logradouro);
        TextInputEditText inputNumero = view.findViewById(R.id.input_numero);
        CheckBox checkboxSemNumero = view.findViewById(R.id.checkbox_sem_numero);
        TextInputEditText inputComplemento = view.findViewById(R.id.input_complemento);
        TextInputEditText inputPontoReferencia = view.findViewById(R.id.input_ponto_referencia);

        // --- CONFIGURA OS DROPDOWNS ---
        String[] tiposDeImovel = new String[] {"Domicílio", "Comércio", "Terreno Baldio", "Ponto Estratégico", "Escola", "Outros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, tiposDeImovel);
        tipoImovelDropdown.setAdapter(adapter);

        // --- CONECTA A UI AO VIEWMODEL ---
        // Quando o usuário seleciona um item, atualiza o ViewModel
        tipoImovelDropdown.setOnItemClickListener((parent, view1, position, id) -> viewModel.tipoImovel.setValue(parent.getItemAtPosition(position).toString()));

        // Para os campos de texto, usamos um TextWatcher
        addTextWatcher(inputNomeLogradouro, text -> viewModel.nomeLogradouro.setValue(text));
        addTextWatcher(inputNumero, text -> viewModel.numero.setValue(text));
        addTextWatcher(inputComplemento, text -> viewModel.complemento.setValue(text));
        addTextWatcher(inputPontoReferencia, text -> viewModel.pontoReferencia.setValue(text));

        // Para o CheckBox
        checkboxSemNumero.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.semNumero.setValue(isChecked));
    }

    // Método auxiliar para evitar código repetido
    private void addTextWatcher(TextInputEditText editText, OnTextChanged listener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onChanged(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Interface funcional para o listener do TextWatcher
    interface OnTextChanged {
        void onChanged(String text);
    }
}