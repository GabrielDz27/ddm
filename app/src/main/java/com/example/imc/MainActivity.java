package com.example.imc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edPeso;
    EditText edAltura;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edPeso = findViewById(R.id.ed1);
        edAltura = findViewById(R.id.ed2);
//        tvImc = findViewById(R.id.tvImc);

        button = findViewById(R.id.button);

        button.setOnClickListener(v ->{
            String strPeso = edPeso.getText().toString();
            String strAltura = edAltura.getText().toString();

            if (strPeso.isEmpty()) {
                edPeso.setError("informe o peso");
                edPeso.requestFocus();
                return;
            }

            Double peso = Double.parseDouble(strPeso);
            Double altura = Double.parseDouble(strAltura);

            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            i.putExtra("pesoUsr",peso);
            i.putExtra("alturaUsr",altura);
            startActivity(i);
        });
    }


}