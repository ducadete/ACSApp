package com.dtech.acsapp.imovel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dtech.acsapp.R;
import java.util.List;

public class ImovelAdapter extends RecyclerView.Adapter<ImovelAdapter.ImovelViewHolder> {

    private List<Imovel> imoveis;
    private final OnDeleteClickListener deleteListener;
    private final OnEditClickListener editListener;
    private final OnImovelClickListener clickListener;

    // --- INTERFACES PARA CADA TIPO DE CLIQUE ---

    public interface OnDeleteClickListener {
        void onDeleteClick(Imovel imovel);
    }

    public interface OnEditClickListener {
        void onEditClick(Imovel imovel);
    }

    public interface OnImovelClickListener {
        void onImovelClick(Imovel imovel);
    }

    // --- CONSTRUTOR ÚNICO E CORRETO ---
    public ImovelAdapter(List<Imovel> imoveis, OnDeleteClickListener deleteListener, OnEditClickListener editListener, OnImovelClickListener clickListener) {
        this.imoveis = imoveis;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ImovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_imovel, parent, false);
        return new ImovelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImovelViewHolder holder, int position) {
        Imovel imovel = imoveis.get(position);
        holder.bind(imovel, deleteListener, editListener, clickListener);
    }

    @Override
    public int getItemCount() {
        return imoveis != null ? imoveis.size() : 0;
    }

    public void updateList(List<Imovel> newList) {
        this.imoveis = newList;
        notifyDataSetChanged();
    }

    static class ImovelViewHolder extends RecyclerView.ViewHolder {
        TextView numeroImovel, complemento;
        ImageView deleteIcon, editIcon;
        View clickableArea;

        public ImovelViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroImovel = itemView.findViewById(R.id.textViewNumeroImovel);
            complemento = itemView.findViewById(R.id.textViewComplemento);
            deleteIcon = itemView.findViewById(R.id.icon_delete_imovel);
            editIcon = itemView.findViewById(R.id.icon_edit_imovel);
            clickableArea = itemView.findViewById(R.id.clickable_area);
        }

        public void bind(final Imovel imovel, final OnDeleteClickListener dListener, final OnEditClickListener eListener, final OnImovelClickListener cListener) {
            numeroImovel.setText("Nº " + imovel.getNumero());
            complemento.setText(imovel.getComplemento());

            deleteIcon.setOnClickListener(v -> dListener.onDeleteClick(imovel));
            editIcon.setOnClickListener(v -> eListener.onEditClick(imovel));
            clickableArea.setOnClickListener(v -> cListener.onImovelClick(imovel));
        }
    }
}