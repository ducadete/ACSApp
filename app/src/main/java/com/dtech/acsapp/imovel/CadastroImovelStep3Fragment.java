package com.dtech.acsapp.imovel;

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

public class CadastroImovelStep3Fragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_cadastro_imovel_step3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- ENCONTRA OS COMPONENTES ---
        AutoCompleteTextView materialParedesDropdown = view.findViewById(R.id.input_material_paredes);
        AutoCompleteTextView abastecimentoAguaDropdown = view.findViewById(R.id.input_abastecimento_agua);
        AutoCompleteTextView aguaConsumoDropdown = view.findViewById(R.id.input_agua_consumo);
        AutoCompleteTextView escoamentoBanheiroDropdown = view.findViewById(R.id.input_escoamento_banheiro);
        RadioGroup radioGroupEnergia = view.findViewById(R.id.radio_group_energia);

        // --- POPULA OS DROPDOWNS ---
        populateDropdown(materialParedesDropdown, new String[]{"Alvenaria/Tijolo com revestimento", "Alvenaria/Tijolo sem revestimento", "Madeira", "Material aproveitado"});
        populateDropdown(abastecimentoAguaDropdown, new String[]{"Rede encanada", "Poço / Nascente", "Outro"});
        populateDropdown(aguaConsumoDropdown, new String[]{"Filtrada", "Fervida", "Clorada", "Mineral", "Sem tratamento"});
        populateDropdown(escoamentoBanheiroDropdown, new String[]{"Rede coletora de esgoto ou pluvial", "Fossa séptica", "Fossa rudimentar", "Céu aberto"});

        // --- CONECTA A UI AO VIEWMODEL ---
        materialParedesDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.materialParedes.setValue(parent.getItemAtPosition(position).toString()));
        abastecimentoAguaDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.abastecimentoAgua.setValue(parent.getItemAtPosition(position).toString()));
        aguaConsumoDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.aguaConsumo.setValue(parent.getItemAtPosition(position).toString()));
        escoamentoBanheiroDropdown.setOnItemClickListener((parent, v, position, id) -> viewModel.escoamentoBanheiro.setValue(parent.getItemAtPosition(position).toString()));

        radioGroupEnergia.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_energia_sim) {
                viewModel.possuiEnergiaEletrica.setValue(true);
            } else if (checkedId == R.id.radio_energia_nao) {
                viewModel.possuiEnergiaEletrica.setValue(false);
            }
        });
    }

    private void populateDropdown(AutoCompleteTextView dropdown, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);
    }
}