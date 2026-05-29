package com.ifsc.listagem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String nomes[] = new String[] {"João","Hudson","Maria","Daniel","Natasha","Gabriel","Alexandre","Andrei","Murilo","Kaique","Douglas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, nomes[position], Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ActivityDados.class);
            i.putExtra("user",nomes[position]);
            startActivity(i);
        });

    }
}