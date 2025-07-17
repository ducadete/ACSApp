package com.dtech.acsapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.dtech.acsapp.cidadao.CadastroCidadaoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            // 1. Configura a navegação padrão para os itens que são Fragments
            NavigationUI.setupWithNavController(navView, navController);

            // 2. Adiciona um listener customizado para tratar do nosso caso especial
            navView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.navigation_add_cidadao) {
                    // Se o item clicado for "Cadastrar Cidadão", abre a Activity manualmente
                    Intent intent = new Intent(MainActivity.this, CadastroCidadaoActivity.class);
                    startActivity(intent);
                    return false; // Retorna 'false' para não marcar o item como selecionado
                } else {
                    // Para todos os outros itens, usa a navegação padrão do NavController
                    // Isso garante que o clique no botão "Logradouro" continue funcionando
                    return NavigationUI.onNavDestinationSelected(item, navController);
                }
            });
        }
    }
}