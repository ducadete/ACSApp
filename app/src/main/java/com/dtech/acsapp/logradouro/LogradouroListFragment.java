package com.dtech.acsapp.logradouro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.R; // Certifique-se de que R está sendo importado corretamente
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LogradouroListFragment extends Fragment {

    // Se você precisar de um construtor, que seja um construtor vazio padrão.
    public LogradouroListFragment() {
        // Construtor público vazio é obrigatório para Fragmentos
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        // Infla o layout para este fragmento
        return inflater.inflate(R.layout.fragment_logradouro_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Aqui você moveria a lógica do RecyclerView e do FAB da sua antiga Activity
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLogradouros);
        // Configure seu RecyclerView aqui (LayoutManager, Adapter, etc.)
        // Exemplo: recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Exemplo: recyclerView.setAdapter(new SeuAdapter());

        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica do clique do FAB
            }
        });

        // O paddingBottom para o RecyclerView deve ser ajustado para considerar o BottomNavigationView
        recyclerView.setClipToPadding(false); // Já configurado no XML, mas bom ter aqui tbm
        recyclerView.setPadding(
                recyclerView.getPaddingLeft(),
                recyclerView.getPaddingTop(),
                recyclerView.getPaddingRight(),
                // Ajuste este padding para acomodar a barra de navegação inferior
                getResources().getDimensionPixelSize(R.dimen.bottom_nav_padding_recyclerview)
        );
    }
}