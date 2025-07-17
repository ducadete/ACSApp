package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.text.TextUtils;
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

public class CadastroCidadaoActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button buttonPrevious, buttonNext;
    private CadastroCidadaoViewModel viewModel;
    private AppDatabase db;
    private static final int NUM_PAGES = 6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidadao);

        viewModel = new ViewModelProvider(this).get(CadastroCidadaoViewModel.class);
        db = AppDatabase.getDatabase(this);

        setupToolbar();
        setupViewPager();
        setupButtons();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarCadastroCidadao);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewPagerCidadao);
        CadastroCidadaoPagerAdapter adapter = new CadastroCidadaoPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(false);
    }

    private void setupButtons() {
        buttonPrevious = findViewById(R.id.button_previous_cidadao);
        buttonNext = findViewById(R.id.button_next_cidadao);

        buttonPrevious.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
        buttonNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < NUM_PAGES - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                saveCidadao();
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                buttonPrevious.setVisibility(position > 0 ? View.VISIBLE : View.INVISIBLE);
                buttonNext.setText(position == NUM_PAGES - 1 ? "Salvar" : "Próximo");
            }
        });
    }

    private void saveCidadao() {
        // --- VALIDAÇÃO ADICIONADA AQUI ---
        String nome = viewModel.nomeCompleto.getValue();
        String cpf = viewModel.cpf.getValue();

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(cpf)) {
            Toast.makeText(this, "Nome completo e CPF/CNS são obrigatórios!", Toast.LENGTH_LONG).show();
            viewPager.setCurrentItem(0); // Volta para a primeira etapa onde estão os campos
            return; // Interrompe o salvamento
        }

        Cidadao novoCidadao = new Cidadao();

        // Etapa 1
        novoCidadao.setCpf(viewModel.cpf.getValue());
        novoCidadao.setCns(viewModel.cns.getValue());
        novoCidadao.setNomeCompleto(viewModel.nomeCompleto.getValue());
        novoCidadao.setNomeSocial(viewModel.nomeSocial.getValue());
        novoCidadao.setDataNascimento(viewModel.dataNascimento.getValue());
        novoCidadao.setSexo(viewModel.sexo.getValue());
        novoCidadao.setResponsavelFamiliar(Boolean.TRUE.equals(viewModel.responsavelFamiliar.getValue()));
        novoCidadao.setRacaOuCor(viewModel.racaOuCor.getValue());
        novoCidadao.setEtnia(viewModel.etnia.getValue());

        // Etapa 2
        novoCidadao.setTelefoneCelular(viewModel.telefoneCelular.getValue());
        novoCidadao.setEmail(viewModel.email.getValue());
        novoCidadao.setNomeMae(viewModel.nomeMae.getValue());
        novoCidadao.setNomePai(viewModel.nomePai.getValue());
        novoCidadao.setNis(viewModel.nis.getValue());

        // Etapa 3
        novoCidadao.setFrequentaEscola(Boolean.TRUE.equals(viewModel.frequentaEscola.getValue()));
        novoCidadao.setGrauInstrucao(viewModel.grauInstrucao.getValue());
        novoCidadao.setSituacaoMercadoTrabalho(viewModel.situacaoMercadoTrabalho.getValue());
        novoCidadao.setOcupacao(viewModel.ocupacao.getValue());
        novoCidadao.setParentescoResponsavel(viewModel.parentescoResponsavel.getValue());

        // Etapa 4
        novoCidadao.setPossuiDeficiencia(Boolean.TRUE.equals(viewModel.possuiDeficiencia.getValue()));
        novoCidadao.setFrequentaCuidador(Boolean.TRUE.equals(viewModel.frequentaCuidador.getValue()));
        novoCidadao.setParticipaGrupoComunitario(Boolean.TRUE.equals(viewModel.participaGrupoComunitario.getValue()));
        novoCidadao.setPossuiPlanoSaude(Boolean.TRUE.equals(viewModel.possuiPlanoSaude.getValue()));

        // Etapa 5
        novoCidadao.setPeso(viewModel.peso.getValue());
        novoCidadao.setDoencaRespiratoria(Boolean.TRUE.equals(viewModel.doencaRespiratoria.getValue()));
        novoCidadao.setProblemasRins(Boolean.TRUE.equals(viewModel.problemasRins.getValue()));
        novoCidadao.setDoencaCardiaca(Boolean.TRUE.equals(viewModel.doencaCardiaca.getValue()));
        novoCidadao.setTeveInternacao(Boolean.TRUE.equals(viewModel.teveInternacao.getValue()));
        novoCidadao.setCausaInternacao(viewModel.causaInternacao.getValue());

        // Etapa 6
        novoCidadao.setFumante(Boolean.TRUE.equals(viewModel.fumante.getValue()));
        novoCidadao.setDependenteAlcool(Boolean.TRUE.equals(viewModel.dependenteAlcool.getValue()));
        novoCidadao.setDependenteDrogas(Boolean.TRUE.equals(viewModel.dependenteDrogas.getValue()));
        novoCidadao.setTemHipertensao(Boolean.TRUE.equals(viewModel.temHipertensao.getValue()));
        novoCidadao.setTemDiabetes(Boolean.TRUE.equals(viewModel.temDiabetes.getValue()));
        novoCidadao.setTeveAvcDerrame(Boolean.TRUE.equals(viewModel.teveAvcDerrame.getValue()));
        novoCidadao.setTeveInfarto(Boolean.TRUE.equals(viewModel.teveInfarto.getValue()));
        novoCidadao.setTemHanseniase(Boolean.TRUE.equals(viewModel.temHanseniase.getValue()));
        novoCidadao.setTemTeveCancer(Boolean.TRUE.equals(viewModel.temTeveCancer.getValue()));

        AppDatabase.databaseWriteExecutor.execute(() -> {
            db.cidadaoDao().insert(novoCidadao);
        });

        Toast.makeText(this, "Cidadão salvo com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}