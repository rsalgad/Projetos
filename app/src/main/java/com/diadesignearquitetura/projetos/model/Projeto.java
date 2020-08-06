package com.diadesignearquitetura.projetos.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Projeto implements Serializable {

    private String titulo;
    private Date data;
    private String imagem;
    private String cliente;
    private String endereco;
    private float metragem;
    private String empreendimento;
    private String comentario;

    public Projeto() {
    }

    public Projeto(String titulo, Date data, String imagem, String cliente, String endereco, float metragem, String empreendimento, String comentario) {
        this.titulo = titulo;
        this.data = data;
        this.imagem = imagem;
        this.cliente = cliente;
        this.endereco = endereco;
        this.metragem = metragem;
        this.empreendimento = empreendimento;
        this.comentario = comentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public float getMetragem() {
        return metragem;
    }

    public void setMetragem(float metragem) {
        this.metragem = metragem;
    }

    public String getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(String empreendimento) {
        this.empreendimento = empreendimento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
