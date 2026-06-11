package com.ifsc.persistencia;

import android.content.Context;

import java.util.ArrayList;

public class NotaController {
    private NotaDAO notaDAO;

    public NotaController(Context context) {
        this.notaDAO = new NotaDAO(context);
    }

    public void inserirNovaNota(Nota nota) {
        notaDAO.insereNota(nota);
    }

    public boolean deleteNota(int id) {
        return false;
    }

    public Nota getNota(int id) {
        return notaDAO.getNota(id);
    }

    public ArrayList<Nota> listarNotas() {
        return new ArrayList<>();
    }

    public ArrayList<String> listaTitulosNotas() {
        ArrayList<Nota> notas = this.listarNotas();
        ArrayList<String> titulosNotas = new ArrayList<>();

        for (Nota n: notas) {
            titulosNotas.add(n.getTitulo());
        }
        return titulosNotas;
    }
}
