package com.ifsc.persistencia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NotaActivity extends AppCompatActivity {

    private EditText editTitulo, editConteudo;
    private Button btnSalvar, btnCancelar;
    private NotaController notaController;
    private int notaId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        editTitulo = findViewById(R.id.editTitulo);
        editConteudo = findViewById(R.id.editConteudo);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);

        notaController = new NotaController(this);

        if (getIntent().hasExtra("ID")) {
            notaId = getIntent().getIntExtra("ID", -1);
            Nota nota = notaController.getNota(notaId);
            if (nota != null) {
                editTitulo.setText(nota.getTitulo());
                editConteudo.setText(nota.getConteudo());
            }
        }

        btnSalvar.setOnClickListener(v -> {
            String titulo = editTitulo.getText().toString();
            String conteudo = editConteudo.getText().toString();

            if (notaId == -1) {
                notaController.inserirNovaNota(new Nota(titulo, conteudo));
            } else {
                notaController.atualizarNota(new Nota(notaId, titulo, conteudo));
            }
            finish();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }
}
