package com.dtech.acsapp.imovel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CadastroImovelPagerAdapter extends FragmentStateAdapter {

    public CadastroImovelPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CadastroImovelStep1Fragment();
            case 1:
                return new CadastroImovelStep2Fragment();
            case 2:
                return new CadastroImovelStep3Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Temos 3 etapas
    }
}