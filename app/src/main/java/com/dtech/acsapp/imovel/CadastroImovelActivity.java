package com.dtech.acsapp.imovel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.dtech.acsapp.AppDatabase;
import com.dtech.acsapp.R;
import com.dtech.acsapp.logradouro.Logradouro;

public class CadastroImovelActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button buttonPrevious, buttonNext;
    private CadastroImovelViewModel viewModel; // "Cérebro" do formulário
    private AppDatabase db;
    private int logradouroId; // ID do logradouro pai

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_imovel);

        // Inicializa o ViewModel e o Banco de Dados
        viewModel = new ViewModelProvider(this).get(CadastroImovelViewModel.class);
        db = AppDatabase.getDatabase(this);
        logradouroId = getIntent().getIntExtra("logradouroId", -1); // Pega o ID passado

        // Configura a UI
        setupToolbar();
        setupViewPager();
        setupButtons();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarCadastro);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewPager);
        CadastroImovelPagerAdapter adapter = new CadastroImovelPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(false);
    }

    private void setupButtons() {
        buttonPrevious = findViewById(R.id.button_previous);
        buttonNext = findViewById(R.id.button_next);

        buttonPrevious.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
        buttonNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < 2) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                saveImovel(); // Chama a função de salvar na última etapa
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                buttonPrevious.setVisibility(position > 0 ? View.VISIBLE : View.INVISIBLE);
                buttonNext.setText(position == 2 ? "Salvar" : "Próximo");
            }
        });
    }

    private void saveImovel() {
        // Coleta todos os dados do ViewModel
        Imovel novoImovel = new Imovel();
        novoImovel.setLogradouroId(logradouroId);

        // Etapa 1
        novoImovel.setTipoImovel(viewModel.tipoImovel.getValue());
        novoImovel.setNomeLogradouro(viewModel.nomeLogradouro.getValue());
        novoImovel.setNumero(viewModel.numero.getValue());
        novoImovel.setSemNumero(Boolean.TRUE.equals(viewModel.semNumero.getValue()));
        novoImovel.setComplemento(viewModel.complemento.getValue());
        novoImovel.setPontoReferencia(viewModel.pontoReferencia.getValue());

        // Etapa 2
        novoImovel.setLocalizacao(viewModel.localizacao.getValue());
        novoImovel.setSituacaoMoradia(viewModel.situacaoMoradia.getValue());
        novoImovel.setTipoAcesso(viewModel.tipoAcesso.getValue());
        novoImovel.setTipoDomicilio(viewModel.tipoDomicilio.getValue());
        novoImovel.setNumeroComodos(viewModel.numeroComodos.getValue());

        // Etapa 3
        novoImovel.setMaterialParedes(viewModel.materialParedes.getValue());
        novoImovel.setAbastecimentoAgua(viewModel.abastecimentoAgua.getValue());
        novoImovel.setAguaConsumo(viewModel.aguaConsumo.getValue());
        novoImovel.setEscoamentoBanheiro(viewModel.escoamentoBanheiro.getValue());
        novoImovel.setPossuiEnergiaEletrica(viewModel.possuiEnergiaEletrica.getValue());

        // Salva no banco de dados em uma thread separada
        AppDatabase.databaseWriteExecutor.execute(() -> {
            db.imovelDao().insert(novoImovel);
            updateLogradouroCount(); // <-- ADICIONE ESTA LINHA
        });

        Toast.makeText(this, "Imóvel salvo com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
    // NOVO MÉTODO (pode copiar da sua ImovelListFragment)
    private void updateLogradouroCount() {
        // Garante que temos um ID válido antes de tentar atualizar
        if (logradouroId == -1) return;

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