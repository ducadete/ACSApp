package com.dtech.acsapp.cidadao;

import android.os.Bundle;
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

public class CadastroCidadaoStep3Fragment extends Fragment {

    private CadastroCidadaoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CadastroCidadaoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Componentes da UI
        RadioGroup radioGroupEscola = view.findViewById(R.id.radio_group_escola);
        AutoCompleteTextView inputGrauInstrucao = view.findViewById(R.id.input_grau_instrucao);
        AutoCompleteTextView inputSituacaoTrabalho = view.findViewById(R.id.input_situacao_trabalho);
        AutoCompleteTextView inputOcupacao = view.findViewById(R.id.input_ocupacao);
        AutoCompleteTextView inputParentesco = view.findViewById(R.id.input_parentesco);

        // Popula Dropdowns
        populateDropdown(inputGrauInstrucao, new String[]{"Creche", "Pré-escola", "Ensino Fundamental", "Ensino Médio", "Superior", "Pós-graduação", "Analfabeto"});
        populateDropdown(inputSituacaoTrabalho, new String[]{"Assalariado com carteira", "Assalariado sem carteira", "Autônomo", "Desempregado", "Aposentado/Pensionista", "Outro"});
        populateDropdown(inputOcupacao, new String[]{"Médico", "Professor", "Agricultor", "Acrobata", "Outro"});
        populateDropdown(inputParentesco, new String[]{"Filho(a)", "Cônjuge/Companheiro(a)", "Pai/Mãe", "Irmão/Irmã", "Outro"});

        // Conecta a UI ao ViewModel
        radioGroupEscola.setOnCheckedChangeListener((group, checkedId) -> viewModel.frequentaEscola.setValue(checkedId == R.id.radio_escola_sim));
        inputGrauInstrucao.setOnItemClickListener((parent, v, pos, id) -> viewModel.grauInstrucao.setValue(parent.getItemAtPosition(pos).toString()));
        inputSituacaoTrabalho.setOnItemClickListener((parent, v, pos, id) -> viewModel.situacaoMercadoTrabalho.setValue(parent.getItemAtPosition(pos).toString()));
        inputOcupacao.setOnItemClickListener((parent, v, pos, id) -> viewModel.ocupacao.setValue(parent.getItemAtPosition(pos).toString()));
        inputParentesco.setOnItemClickListener((parent, v, pos, id) -> viewModel.parentescoResponsavel.setValue(parent.getItemAtPosition(pos).toString()));
    }

    private void populateDropdown(AutoCompleteTextView dropdown, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);
    }
}