package com.ifsc.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAdicionar;
    private NotaController notaController;
    private ArrayList<Nota> listaNotas;
    private ArrayList<String> listaTitulos;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        notaController = new NotaController(this);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotaActivity.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Nota nota = listaNotas.get(position);
            Intent intent = new Intent(this, NotaActivity.class);
            intent.putExtra("ID", nota.getId());
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Nota nota = listaNotas.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Excluir nota")
                    .setMessage("Deseja realmente excluir esta nota?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        notaController.deleteNota(nota.getId());
                        atualizarLista();
                    })
                    .setNegativeButton("Não", null)
                    .show();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista() {
        listaNotas = notaController.listarNotas();
        listaTitulos = new ArrayList<>();
        for (Nota n : listaNotas) {
            listaTitulos.add(n.getTitulo());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTitulos);
        listView.setAdapter(adapter);
    }
}
