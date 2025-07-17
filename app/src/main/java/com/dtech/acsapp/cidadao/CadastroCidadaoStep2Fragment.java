package com.dtech.acsapp.cidadao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.dtech.acsapp.R;

public class CadastroCidadaoStep2Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Simplesmente infla o layout para esta etapa
        return inflater.inflate(R.layout.fragment_cadastro_cidadao_step2, container, false);
    }
}