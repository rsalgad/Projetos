package com.diadesignearquitetura.projetos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.fragment.AcaoFragment;
import com.diadesignearquitetura.projetos.model.Acao;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Acao extends RecyclerView.Adapter<Adapter_Acao.MyViewHolder> {

    private List<Acao> listaAcoes = new ArrayList<Acao>();

    public Adapter_Acao(List<Acao> listaAcoes) {
        this.listaAcoes = listaAcoes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemAcao = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acao_item, parent, false);

        return new Adapter_Acao.MyViewHolder(itemAcao);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Acao acao =  listaAcoes.get(position);
        holder.titulo.setText(acao.getTitulo());
        holder.categoria.setText(acao.getCategoria().getTitulo());
        holder.valor.setText(Integer.toString(acao.getValor()));
        holder.botaoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listaAcoes.remove(position);
                AcaoFragment frag = (AcaoFragment) MainActivity.acaoFragment;
                notifyDataSetChanged();
                frag.UpdateRecyclerView();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAcoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private Button titulo;
        private TextView categoria;
        private ImageButton botaoDeletar;
        private TextView valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.botaoCategoria);
            categoria = itemView.findViewById(R.id.textViewAcaoCategoria);
            botaoDeletar = itemView.findViewById(R.id.botaoDeleteAcao);
            valor = itemView.findViewById(R.id.textViewValor);
        }
    }

}
