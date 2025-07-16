package com.dtech.acsapp.logradouro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtech.acsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LogradouroListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_logradouro_list);

        // Configura o RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewLogradouros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cria a lista de dados de exemplo
        List<Logradouro> logradouros = new ArrayList<>();
        logradouros.add(new Logradouro(1, "Rua Professor Simão José Hess", 14));
        logradouros.add(new Logradouro(2, "Avenida das Farmácias", 13));
        logradouros.add(new Logradouro(3, "Rua Jornalista Clarissa Flores", 13));
        logradouros.add(new Logradouro(4, "Rua Eduarda Ximenes Coutinho", 12));

        // Cria e configura o adaptador
        LogradouroAdapter adapter = new LogradouroAdapter(logradouros);
        recyclerView.setAdapter(adapter);

        // Configura a ação do botão "+"
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ação temporária, apenas para mostrar que o botão funciona
                Toast.makeText(LogradouroListActivity.this, "Adicionar novo logradouro...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
