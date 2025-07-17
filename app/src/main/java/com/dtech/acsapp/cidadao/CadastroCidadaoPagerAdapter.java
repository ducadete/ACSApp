package com.dtech.acsapp.cidadao;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CadastroCidadaoPagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 6;

    public CadastroCidadaoPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CadastroCidadaoStep1Fragment();
            case 1:
                return new CadastroCidadaoStep2Fragment();
            case 2:
                return new CadastroCidadaoStep3Fragment();
            case 3:
                return new CadastroCidadaoStep4Fragment();
            case 4:
                return new CadastroCidadaoStep5Fragment();
            case 5:
                return new CadastroCidadaoStep6Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}