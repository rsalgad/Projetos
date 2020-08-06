package com.diadesignearquitetura.projetos.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.activity.ProjetoActivity;
import com.diadesignearquitetura.projetos.fragment.ProjetoDetalheFragment;
import com.diadesignearquitetura.projetos.model.Projeto;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Adapter_Projeto extends RecyclerView.Adapter<Adapter_Projeto.MyViewHolder> {

    List<Projeto> listaProjetos;
    int position;
    public Adapter_Projeto(List<Projeto> listaProjetos) {
        this.listaProjetos = listaProjetos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemProjeto = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projetos_detalhe, parent, false);

        return new MyViewHolder(itemProjeto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Projeto p = listaProjetos.get(position);
        this.position = position;
        holder.titulo.setText(p.getTitulo());
        DateFormat date = DateFormat.getDateInstance();
        holder.data.setText(date.format(p.getData()));
        holder.detalhe.setText(p.getEmpreendimento());
        if (p.getImagem() == null) {
            holder.imagem.setImageResource(R.drawable.ic_photo_camera_36dp);
        } else {
            holder.imagem.setImageBitmap(ProjetoDetalheFragment.decodeToBase64(p.getImagem()));
        }
        holder.botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProjetoActivity.class);
                intent.putExtra("projeto", MainActivity.listaProjetos.get(position));
                ContextCompat.startActivity(view.getContext(), intent, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProjetos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView data;
        private TextView detalhe;
        private ImageView imagem;
        private Button botaoVer;
        private Button botaoEditar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            data = itemView.findViewById(R.id.textData);
            detalhe = itemView.findViewById(R.id.textDetalhe);
            imagem = itemView.findViewById(R.id.imageView);
            botaoVer = itemView.findViewById(R.id.botaoVer);
            botaoEditar = itemView.findViewById(R.id.botaoEditar);
        }
    }

}
