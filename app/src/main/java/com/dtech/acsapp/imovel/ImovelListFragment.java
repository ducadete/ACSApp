package com.dtech.acsapp.imovel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.acsapp.AppDatabase;
import com.dtech.acsapp.R;
import com.dtech.acsapp.logradouro.Logradouro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

// 1. ADICIONE A NOVA INTERFACE AQUI
public class ImovelListFragment extends Fragment implements ImovelAdapter.OnDeleteClickListener, ImovelAdapter.OnEditClickListener, ImovelAdapter.OnImovelClickListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ImovelAdapter adapter;
    private AppDatabase db;
    private int logradouroId;
    private String logradouroNome;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(getContext());
        if (getArguments() != null) {
            logradouroId = getArguments().getInt("logradouroId");
            logradouroNome = getArguments().getString("logradouroNome");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_imovel_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(view);
        setupRecyclerView();
        observeImoveis();
    }

    private void setupUI(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewImoveis);
        fab = view.findViewById(R.id.fabAddImovel);
        toolbar = view.findViewById(R.id.toolbarImoveis);

        if (logradouroNome != null) {
            toolbar.setTitle(logradouroNome);
        }
        toolbar.setNavigationOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CadastroImovelActivity.class);
            intent.putExtra("logradouroId", logradouroId);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        // 2. CORREÇÃO NA CRIAÇÃO DO ADAPTADOR, PASSANDO OS 3 LISTENERS
        adapter = new ImovelAdapter(new ArrayList<>(), this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void observeImoveis() {
        db.imovelDao().getImoveisByLogradouro(logradouroId).observe(getViewLifecycleOwner(), new Observer<List<Imovel>>() {
            @Override
            public void onChanged(List<Imovel> imoveis) {
                if (imoveis != null) {
                    adapter.updateList(imoveis);
                }
            }
        });
    }

    // --- IMPLEMENTAÇÃO DOS CLIQUES ---

    @Override
    public void onImovelClick(Imovel imovel) {
        Bundle args = new Bundle();
        args.putInt("imovelId", imovel.getId());
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_imovelListFragment_to_imovelDetailFragment, args);
    }

    @Override
    public void onEditClick(Imovel imovel) {
        showAddOrEditImovelDialog(imovel);
    }

    @Override
    public void onDeleteClick(Imovel imovel) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirmar Exclusão")
                .setMessage("Deseja deletar o imóvel Nº " + imovel.getNumero() + "?")
                .setPositiveButton("Deletar", (dialog, which) -> deleteImovelFromDb(imovel))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    // --- MÉTODOS AUXILIARES ---

    private void showAddOrEditImovelDialog(@Nullable Imovel imovel) {
        boolean isEditing = imovel != null;
        String dialogTitle = isEditing ? "Editar Imóvel" : "Adicionar Novo Imóvel";
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(dialogTitle);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_imovel, null);
        final EditText inputNumero = dialogView.findViewById(R.id.input_numero);
        final EditText inputComplemento = dialogView.findViewById(R.id.input_complemento);
        if (isEditing) {
            inputNumero.setText(imovel.getNumero());
            inputComplemento.setText(imovel.getComplemento());
        }
        builder.setView(dialogView);
        builder.setPositiveButton(isEditing ? "Salvar" : "Adicionar", (dialog, which) -> {
            String numero = inputNumero.getText().toString().trim();
            String complemento = inputComplemento.getText().toString().trim();
            if (numero.isEmpty()) {
                Toast.makeText(getContext(), "O número do imóvel é obrigatório.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEditing) {
                imovel.setNumero(numero);
                imovel.setComplemento(complemento);
                updateImovelInDb(imovel);
            } else {
                Imovel novoImovel = new Imovel();
                novoImovel.setLogradouroId(logradouroId);
                novoImovel.setNumero(numero);
                novoImovel.setComplemento(complemento);
                insertImovelInDb(novoImovel);
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void insertImovelInDb(Imovel imovel) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            db.imovelDao().insert(imovel);
            updateLogradouroCount();
        });
        mainThreadHandler.post(() -> Toast.makeText(getContext(), "Imóvel adicionado!", Toast.LENGTH_SHORT).show());
    }

    private void updateImovelInDb(Imovel imovel) {
        AppDatabase.databaseWriteExecutor.execute(() -> db.imovelDao().update(imovel));
        mainThreadHandler.post(() -> Toast.makeText(getContext(), "Imóvel atualizado!", Toast.LENGTH_SHORT).show());
    }

    private void deleteImovelFromDb(Imovel imovel) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            db.imovelDao().delete(imovel);
            updateLogradouroCount();
        });
        mainThreadHandler.post(() -> Toast.makeText(getContext(), "Imóvel deletado!", Toast.LENGTH_SHORT).show());
    }

    private void updateLogradouroCount() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Logradouro logradouroPai = db.logradouroDao().getLogradouroById(logradouroId);
            if (logradouroPai != null) {
                int novoTotal = db.imovelDao().countImoveisByLogradouro(logradouroId);
                logradouroPai.setImoveisCount(novoTotal);
                db.logradouroDao().update(logradouroPai);
            }
        });
    }
}