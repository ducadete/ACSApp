package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.dtech.acsapp.R;

public class CadastroCidadaoStep3Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Popula os menus dropdown com as opções
        populateDropdown(view, R.id.input_grau_instrucao, new String[]{"Creche", "Pré-escola", "Ensino Fundamental", "Ensino Médio", "Superior", "Pós-graduação", "Analfabeto"});
        populateDropdown(view, R.id.input_situacao_trabalho, new String[]{"Assalariado com carteira", "Assalariado sem carteira", "Autônomo", "Desempregado", "Aposentado/Pensionista", "Outro"});
        populateDropdown(view, R.id.input_ocupacao, new String[]{"Médico", "Professor", "Agricultor", "Acrobata", "Outro"}); // Adicionar mais ocupações
        populateDropdown(view, R.id.input_parentesco, new String[]{"Filho(a)", "Cônjuge/Companheiro(a)", "Pai/Mãe", "Irmão/Irmã", "Outro"});
    }

    private void populateDropdown(View rootView, int dropdownId, String[] items) {
        AutoCompleteTextView dropdown = rootView.findViewById(dropdownId);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                items
        );
        dropdown.setAdapter(adapter);
    }
}