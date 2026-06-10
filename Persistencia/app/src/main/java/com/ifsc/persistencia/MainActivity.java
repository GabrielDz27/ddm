package com.ifsc.persistencia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    EditText editText;
    Button button;
    ArrayList<String> list;
    ArrayAdapter<String> asdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        button = findViewById(R.id.salvar);
        editText = findViewById(R.id.editTextText);
        button.setOnClickListener(v -> {
            String string = editText.getText().toString();
            ContentValues contentValues = new ContentValues();
            contentValues.put("titulo","x");
            contentValues.put("conteudo","COnteudo da nota 1");
            sqLiteDatabase.insert("notas",null,contentValues);
            consultarDados();
        });

        sqLiteDatabase = openOrCreateDatabase("banco.db",MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("DROP TABLE notas");
        sqLiteDatabase.execSQL("CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT , titulo TEXT, conteudo TEXT );");
        consultarDados();
    }

    public void consultarDados() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM notas",null);
        cursor.moveToFirst();

        list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range")
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            @SuppressLint("Range")
            String conteudo = cursor.getString(cursor.getColumnIndex("conteudo"));
            list.add("id"+Integer.toString(id)+"titulo: "+titulo);
            cursor.moveToNext();

            Log.d("Sele notas", "id: "+id+", titulo:"+titulo+" conteudo:"+conteudo);
        }
        asdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(asdapter);
    }
}