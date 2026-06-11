package com.ifsc.persistencia;

import java.util.Objects;

public class Nota {
    private int id;
    private String titulo;
    private String conteudo;

    public Nota(int id, String titulo, String conteudo) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Nota(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return id == nota.id && Objects.equals(titulo, nota.titulo) && Objects.equals(conteudo, nota.conteudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, conteudo);
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
