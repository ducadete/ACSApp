package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.AppDatabase;
import com.dtech.acsapp.R;
import java.util.ArrayList;
import java.util.List;

public class CidadaoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CidadaoAdapter adapter;
    private AppDatabase db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cidadao_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewCidadaos);
        setupRecyclerView();
        observeCidadaos();
    }

    private void setupRecyclerView() {
        adapter = new CidadaoAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void observeCidadaos() {
        db.cidadaoDao().getAllCidadaos().observe(getViewLifecycleOwner(), new Observer<List<Cidadao>>() {
            @Override
            public void onChanged(List<Cidadao> cidadaos) {
                if (cidadaos != null) {
                    adapter.updateList(cidadaos);
                }
            }
        });
    }
}
