package com.diadesignearquitetura.projetos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.fragment.CategoriaFragment;
import com.diadesignearquitetura.projetos.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Categoria extends RecyclerView.Adapter<Adapter_Categoria.MyViewHolder> {

    private List<Categoria> listaCategoria = new ArrayList<Categoria>();

    public Adapter_Categoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemCategoria = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoria_item, parent, false);

        return new MyViewHolder(itemCategoria);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Categoria cat = listaCategoria.get(position);
        holder.categoria.setText(cat.getTitulo());
        holder.botaoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listaCategorias.remove(position);
                CategoriaFragment frag = (CategoriaFragment)MainActivity.categoriaFragment;
                notifyDataSetChanged();
                frag.UpdateRecyclerView();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private Button categoria;
        private ImageButton botaoDeletar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.botaoCategoria);
            botaoDeletar = itemView.findViewById(R.id.botaoDeleteCategoria);
        }
    }

}
