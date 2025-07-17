package com.dtech.acsapp.logradouro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.R;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class LogradouroAdapter extends RecyclerView.Adapter<LogradouroAdapter.LogradouroViewHolder> {

    private List<Logradouro> logradouros;
    private int editModePosition = -1; // Renomeado para modo de edição/exclusão

    // Adicionamos a nova interface e o listener
    private final OnDeleteClickListener deleteListener;
    private final OnLogradouroClickListener clickListener;
    private final OnEditClickListener editListener;

    // --- INTERFACES ---
    public interface OnDeleteClickListener { void onDeleteClick(Logradouro logradouro); }
    public interface OnLogradouroClickListener { void onLogradouroClick(Logradouro logradouro); }
    public interface OnEditClickListener { void onEditClick(Logradouro logradouro); }

    // --- CONSTRUTOR ATUALIZADO ---
    public LogradouroAdapter(List<Logradouro> logradouros, OnDeleteClickListener d, OnLogradouroClickListener c, OnEditClickListener e) {
        this.logradouros = logradouros;
        this.deleteListener = d;
        this.clickListener = c;
        this.editListener = e;
    }

    @NonNull @Override
    public LogradouroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_logradouro, parent, false);
        return new LogradouroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogradouroViewHolder holder, int position) {
        holder.bind(logradouros.get(position), position);
    }

    @Override public int getItemCount() { return logradouros != null ? logradouros.size() : 0; }
    public void updateList(List<Logradouro> newList) {
        this.logradouros = newList;
        exitEditMode();
        notifyDataSetChanged();
    }
    public void exitEditMode() {
        if (editModePosition != -1) {
            int oldPosition = editModePosition;
            editModePosition = -1;
            notifyItemChanged(oldPosition);
        }
    }

    class LogradouroViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView nomeLogradouro, imoveisCount;
        ImageView deleteIcon, editIcon; // Adicionamos o ícone de edição
        Animation shakeAnimation;

        public LogradouroViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            nomeLogradouro = itemView.findViewById(R.id.textViewNomeLogradouro);
            imoveisCount = itemView.findViewById(R.id.textViewImoveisCount);
            deleteIcon = itemView.findViewById(R.id.icon_delete);
            editIcon = itemView.findViewById(R.id.icon_edit); // Encontra o ícone de edição
            shakeAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.shake);
        }

        void bind(final Logradouro logradouro, final int position) {
            nomeLogradouro.setText(logradouro.getNome());
            String imoveisCountText = logradouro.getImoveisCount() + " imóveis cadastrados";
            imoveisCount.setText(imoveisCountText);

            // Controla a visibilidade dos ícones e a animação
            if (editModePosition == position) {
                deleteIcon.setVisibility(View.VISIBLE);
                editIcon.setVisibility(View.VISIBLE); // Mostra o lápis
                cardView.startAnimation(shakeAnimation);
            } else {
                deleteIcon.setVisibility(View.GONE);
                editIcon.setVisibility(View.GONE); // Esconde o lápis
                cardView.clearAnimation();
            }

            itemView.setOnLongClickListener(v -> {
                exitEditMode();
                editModePosition = getAdapterPosition();
                notifyItemChanged(editModePosition);
                return true;
            });

            itemView.setOnClickListener(v -> {
                if (editModePosition != -1) {
                    exitEditMode();
                } else {
                    clickListener.onLogradouroClick(logradouro);
                }
            });

            deleteIcon.setOnClickListener(v -> deleteListener.onDeleteClick(logradouro));
            editIcon.setOnClickListener(v -> editListener.onEditClick(logradouro)); // Define o clique do lápis
        }
    }
}