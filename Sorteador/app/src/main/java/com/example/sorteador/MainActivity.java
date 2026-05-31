package com.example.sorteador;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    EditText editText;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.definicao);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.numero);

        button.setOnClickListener(view -> {
            String textoDigitado = editText.getText().toString();

            if (textoDigitado.isEmpty()) {
                editText.setError("Digite um numero primeiro");
            }

            Random random = new Random();
            i = random.nextInt(Integer.parseInt(textoDigitado));
            textView.setText(String.valueOf(i));
        });
    }
}