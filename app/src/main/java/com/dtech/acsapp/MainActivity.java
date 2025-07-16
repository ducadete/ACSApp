package com.dtech.acsapp; // Certifique-se de que este é o seu pacote correto

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity; // Para AppCompatActivity
import androidx.navigation.NavController; // Para NavController
import androidx.navigation.Navigation; // Para Navigation.findNavController
import androidx.navigation.ui.NavigationUI; // Para NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView; // Para BottomNavigationView

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // 1. Encontra o BottomNavigationView no layout
        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupWithNavController(navView, navController);

    }
}
