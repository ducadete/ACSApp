package com.dtech.acsapp.logradouro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.AppDatabase;
import com.dtech.acsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

public class LogradouroListFragment extends Fragment implements LogradouroAdapter.OnDeleteClickListener,
        LogradouroAdapter.OnLogradouroClickListener,
        LogradouroAdapter.OnEditClickListener {

    // Variáveis da UI e de dados
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SearchView searchView;
    private Toolbar toolbar;
    private LogradouroAdapter adapter;
    private final List<Logradouro> fullLogradouroList = new ArrayList<>();
    private AppDatabase db;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private ActivityResultLauncher<Intent> importCsvLauncher;
    private ActivityResultLauncher<Intent> exportCsvLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFileLaunchers();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = AppDatabase.getDatabase(getContext());
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_logradouro_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(view);
        setupRecyclerView();
        observeLogradouros();
        setupSearch();
        setupFab();
    }



    // --- MÉTODOS DE SETUP ---

    private void setupUI(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewLogradouros);
        fab = view.findViewById(R.id.fabAdd);
        searchView = view.findViewById(R.id.searchView);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Logradouros");
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    private void setupRecyclerView() {
        adapter = new LogradouroAdapter(new ArrayList<>(), this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void setupFab() {
        fab.setOnClickListener(v -> showAddLogradouroDialog());
    }
    private void observeLogradouros() {
        // O método do DAO agora retorna LiveData, que podemos observar.
        // O getViewLifecycleOwner() garante que a observação só acontece quando a tela está visível.
        db.logradouroDao().getAllLogradouros().observe(getViewLifecycleOwner(), new Observer<List<Logradouro>>() {
            @Override
            public void onChanged(List<Logradouro> logradouros) {
                // Este código será executado AUTOMATICAMENTE sempre que os dados mudarem no banco.
                if (logradouros != null) {
                    fullLogradouroList.clear();
                    fullLogradouroList.addAll(logradouros);
                    adapter.updateList(logradouros);
                }
            }
        });
    }

    // --- LÓGICA DE DADOS ---


    private void filterList(String query) {
        List<Logradouro> filteredList = new ArrayList<>();
        for (Logradouro logradouro : fullLogradouroList) {
            if (logradouro.getNome().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(logradouro);
            }
        }
        adapter.updateList(filteredList);
    }

    private void deleteLogradouroFromDb(Logradouro logradouro) {
        // Apenas deletamos do banco. O LiveData cuidará da atualização.
        AppDatabase.databaseWriteExecutor.execute(() -> db.logradouroDao().delete(logradouro));
        Toast.makeText(getContext(), "Logradouro deletado.", Toast.LENGTH_SHORT).show();
    }


    // --- LISTENERS DA INTERFACE ---

    @Override
    public void onLogradouroClick(Logradouro logradouro) {
        Bundle args = new Bundle();
        args.putInt("logradouroId", logradouro.getId());
        args.putString("logradouroNome", logradouro.getNome());
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_logradouroListFragment_to_imovelListFragment, args);
    }

    @Override
    public void onDeleteClick(Logradouro logradouro) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirmar Exclusão")
                .setMessage("Você realmente quer deletar o logradouro '" + logradouro.getNome() + "'?")
                .setPositiveButton("Deletar", (dialog, which) -> deleteLogradouroFromDb(logradouro))
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    if (adapter != null) adapter.exitEditMode();
                })
                .setOnCancelListener(dialog -> {
                    if (adapter != null) adapter.exitEditMode();
                })
                .show();
    }

    @Override
    public void onEditClick(Logradouro logradouro) {
        showEditLogradouroDialog(logradouro);
    }


    // --- DIÁLOGOS (POP-UPS) ---

    private void showAddLogradouroDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Adicionar Novo Logradouro");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        builder.setView(input);
        builder.setPositiveButton("Adicionar", (dialog, which) -> {
            String nomeLogradouro = input.getText().toString().trim();
            if (!nomeLogradouro.isEmpty()) {
                Logradouro novo = new Logradouro(nomeLogradouro, 0);
                // Apenas inserimos no banco. O LiveData cuidará da atualização da tela.
                AppDatabase.databaseWriteExecutor.execute(() -> db.logradouroDao().insert(novo));
                Toast.makeText(getContext(), "Logradouro adicionado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "O nome não pode ser vazio.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showEditLogradouroDialog(Logradouro logradouro) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Editar Logradouro");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        input.setText(logradouro.getNome());
        builder.setView(input);
        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String novoNome = input.getText().toString().trim();
            if (!novoNome.isEmpty()) {
                logradouro.setNome(novoNome);
                AppDatabase.databaseWriteExecutor.execute(() -> db.logradouroDao().update(logradouro));
                Toast.makeText(getContext(), "Logradouro atualizado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "O nome não pode ser vazio.", Toast.LENGTH_SHORT).show();
            }
            adapter.exitEditMode();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.cancel();
            adapter.exitEditMode();
        });
        builder.show();
    }


    // --- IMPORTAÇÃO E EXPORTAÇÃO DE CSV ---

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_export_csv) {
            triggerExportCsv();
            return true;
        } else if (itemId == R.id.action_import_csv) {
            triggerImportCsv();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupFileLaunchers() {
        importCsvLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) readCsvFile(uri);
                    }
                });

        exportCsvLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) writeCsvFile(uri);
                    }
                });
    }

    private void triggerImportCsv() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        String[] mimeTypes = {"text/csv", "text/comma-separated-values", "application/csv"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        importCsvLauncher.launch(intent);
    }

    private void triggerExportCsv() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/csv");
        intent.putExtra(Intent.EXTRA_TITLE, "logradouros_export.csv");
        exportCsvLauncher.launch(intent);
    }

    private void readCsvFile(Uri uri) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try (InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String nomeLogradouro = line.trim();
                    if (!nomeLogradouro.isEmpty()) {
                        db.logradouroDao().insert(new Logradouro(nomeLogradouro, 0));
                    }
                }
                // O LiveData já atualiza a tela, então só mostramos a mensagem.
                mainThreadHandler.post(() -> Toast.makeText(getContext(), "Importação concluída!", Toast.LENGTH_SHORT).show());
            } catch (IOException e) {
                e.printStackTrace();
                mainThreadHandler.post(() -> Toast.makeText(getContext(), "Falha ao importar arquivo.", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void writeCsvFile(Uri uri) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try (OutputStream outputStream = requireContext().getContentResolver().openOutputStream(uri)) {
                // Aqui usamos a lista local para exportar, garantindo que reflete a tela
                StringBuilder csvContent = new StringBuilder();
                csvContent.append("nome_logradouro,total_imoveis\n");
                for (Logradouro log : fullLogradouroList) {
                    csvContent.append("\"").append(log.getNome().replace("\"", "\"\"")).append("\"");
                    csvContent.append(",");
                    csvContent.append(log.getImoveisCount());
                    csvContent.append("\n");
                }
                outputStream.write(csvContent.toString().getBytes(StandardCharsets.UTF_8));
                mainThreadHandler.post(() -> Toast.makeText(getContext(), "Arquivo exportado com sucesso!", Toast.LENGTH_SHORT).show());
            } catch (IOException e) {
                e.printStackTrace();
                mainThreadHandler.post(() -> Toast.makeText(getContext(), "Falha ao exportar arquivo.", Toast.LENGTH_SHORT).show());
            }
        });
    }

}