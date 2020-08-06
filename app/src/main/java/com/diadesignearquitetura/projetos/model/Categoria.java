package com.diadesignearquitetura.projetos.model;

public class Categoria {

    private String titulo;
    private int id;

    public Categoria(String titulo, int id) {
        this.titulo = titulo;
        this.id = id;
    }

    public Categoria(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
