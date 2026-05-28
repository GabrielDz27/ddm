package com.ifsc.fragmentos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    FragmentoA fragmentoA;
    FragmentoB fragmentoB;
    FragmentoC fragmentoC;
    Button buttonA, buttonB, buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicialização gerenciador de fragmentos
        setContentView(R.layout.activity_main);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);

        FragmentManager fragmentManager = getSupportFragmentManager();
        //Inicia uma transação com fragmentManfater
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //Adcionamos o container e o bjeto do nosso fragmentoA para exibirt
        transaction.add(R.id.frameLayout, new FragmentoA());
        //Finalizamos
        transaction.commit();

        buttonA.setOnClickListener(v -> {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout, new FragmentoA());
            ft.commit();
        });
        buttonB.setOnClickListener(v -> {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout, new FragmentoB());
            ft.commit();
        });
        buttonC.setOnClickListener(v -> {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout, new FragmentoC());
            ft.commit();
        });
    }
}