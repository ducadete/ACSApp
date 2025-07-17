package com.dtech.acsapp.cidadao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.R;
import java.util.List;

public class CidadaoAdapter extends RecyclerView.Adapter<CidadaoAdapter.CidadaoViewHolder> {

    private List<Cidadao> cidadaos;

    public CidadaoAdapter(List<Cidadao> cidadaos) {
        this.cidadaos = cidadaos;
    }

    @NonNull
    @Override
    public CidadaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cidadao, parent, false);
        return new CidadaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CidadaoViewHolder holder, int position) {
        Cidadao cidadao = cidadaos.get(position);
        holder.bind(cidadao);
    }

    @Override
    public int getItemCount() {
        return cidadaos != null ? cidadaos.size() : 0;
    }

    public void updateList(List<Cidadao> newList) {
        this.cidadaos = newList;
        notifyDataSetChanged();
    }

    static class CidadaoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeCidadao, cpfCns;

        public CidadaoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCidadao = itemView.findViewById(R.id.textViewNomeCidadao);
            cpfCns = itemView.findViewById(R.id.textViewCpfCns);
        }

        void bind(Cidadao cidadao) {
            nomeCidadao.setText(cidadao.getNomeCompleto());
            String cpf = cidadao.getCpf();
            if (cpf != null && !cpf.isEmpty()) {
                cpfCns.setText("CPF: " + cpf);
            } else {
                cpfCns.setText("CNS: " + cidadao.getCns());
            }
        }
    }
}
