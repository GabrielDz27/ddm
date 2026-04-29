package com.example.imc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {

    Button button;
    TextView tvImc;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.voltar);
        tvImc = findViewById(R.id.tvImc);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(v ->{
            finish();
        });

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        Double peso = bundle.getDouble("pesoUsr");
        Double altura = bundle.getDouble("alturaUsr");

        double imc=peso/altura*altura;

        DecimalFormat dc = new DecimalFormat("##.##");

        if (imc<20) {
            imageView.setImageResource(R.drawable.abaixopeso);
        }


    }
}