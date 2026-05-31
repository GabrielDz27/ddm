package com.example.slideshow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button voltar, proximo;

    int[] minhasImagens = {
            R.drawable.img,
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3
    };

    int posicao = 0;

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

        imageView = findViewById(R.id.imageView);
        voltar = findViewById(R.id.voltar);
        proximo = findViewById(R.id.proximo);

        proximo.setOnClickListener(view -> {
            if (posicao < minhasImagens.length - 1) {
                posicao++;
                imageView.setImageResource(minhasImagens[posicao]);
            }
        });

        voltar.setOnClickListener(view -> {
            if (posicao > 0) {
                posicao--;
                imageView.setImageResource(minhasImagens[posicao]);
            }
        });
    }
}