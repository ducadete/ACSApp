package com.dtech.acsapp.logradouro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.R; // Garanta que esta importação está correta
import java.util.List;

public class LogradouroAdapter extends RecyclerView.Adapter<LogradouroAdapter.LogradouroViewHolder> {

    private List<Logradouro> logradouros;

    public LogradouroAdapter(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }

    @NonNull
    @Override
    public LogradouroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_logradouro, parent, false);
        return new LogradouroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogradouroViewHolder holder, int position) {
        Logradouro logradouro = logradouros.get(position);
        holder.nomeLogradouro.setText(logradouro.getNome());

        // MODO CORRETO DE ATRIBUIR O TEXTO, USANDO RECURSOS
        int count = logradouro.getImoveisCount();
        String imoveisText = holder.itemView.getContext().getString(R.string.imoveis_cadastrados_count, count);
        holder.imoveisCount.setText(imoveisText);
    }

    @Override
    public int getItemCount() {
        return logradouros.size();
    }

    public static class LogradouroViewHolder extends RecyclerView.ViewHolder {
        TextView nomeLogradouro;
        TextView imoveisCount;

        public LogradouroViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeLogradouro = itemView.findViewById(R.id.textViewNomeLogradouro);
            // CORREÇÃO DO PONTO DUPLO
            imoveisCount = itemView.findViewById(R.id.textViewImoveisCount);
        }
    }
}