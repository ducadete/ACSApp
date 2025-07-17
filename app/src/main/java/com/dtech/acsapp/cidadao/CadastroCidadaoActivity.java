package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import com.dtech.acsapp.R;

public class CadastroCidadaoActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button buttonPrevious, buttonNext;
    private static final int NUM_PAGES = 6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidadao);

        Toolbar toolbar = findViewById(R.id.toolbarCadastroCidadao);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        viewPager = findViewById(R.id.viewPagerCidadao);
        buttonPrevious = findViewById(R.id.button_previous_cidadao);
        buttonNext = findViewById(R.id.button_next_cidadao);

        CadastroCidadaoPagerAdapter adapter = new CadastroCidadaoPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(false); // Impede o deslize com o dedo

        buttonPrevious.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
        buttonNext.setOnClickListener(v -> {
            int currentPage = viewPager.getCurrentItem();
            if (currentPage < NUM_PAGES - 1) {
                viewPager.setCurrentItem(currentPage + 1);
            } else {
                // TODO: Lógica para salvar o cidadão no banco de dados
                Toast.makeText(this, "Cadastro de cidadão finalizado!", Toast.LENGTH_SHORT).show();
                finish();
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
}