package com.diadesignearquitetura.projetos.model;

public class Acao {

    private String titulo;
    private int valor;
    private Categoria categoria;

    public Acao(String titulo, int valor, Categoria categoria) {
        this.titulo = titulo;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Acao(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoriaID(Categoria categoria) {
        this.categoria = categoria;
    }
}
