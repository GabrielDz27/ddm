package com.ifsc.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotaDAO {
    private SQLiteDatabase db;

    public NotaDAO(Context context) {
        db = context.openOrCreateDatabase("banco.db",Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT , titulo TEXT, conteudo TEXT );");
    }

    public Nota insereNota(Nota n) {
        if(n!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("titulo",n.getTitulo());
            contentValues.put("conteudo",n.getConteudo());
            int id= (int) db.insert("notas",null,contentValues);
            return new Nota(id,n.getTitulo(),n.getConteudo());
        }
        return null;
    }

    public Nota getNota(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE id=?", new String[]{Integer.toString(id)});
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            return new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return null;
    }

    public ArrayList listarNotas() {
        ArrayList result = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE id=?",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result.add(new Nota(cursor.getInt(0),cursor.getString(1), cursor.getString(2)));
            return result;
        }
        return null;
    }

    public Nota removeNota() {
        return null;
    }
}
