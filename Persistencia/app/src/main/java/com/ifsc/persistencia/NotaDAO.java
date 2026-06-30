package com.ifsc.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotaDAO {
    private SQLiteDatabase db;

    public NotaDAO(Context context) {
        db = context.openOrCreateDatabase("banco.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT , titulo TEXT, conteudo TEXT );");
    }

    public Nota insereNota(Nota n) {
        if (n != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("titulo", n.getTitulo());
            contentValues.put("conteudo", n.getConteudo());
            int id = (int) db.insert("notas", null, contentValues);
            n.setId(id);
            return n;
        }
        return null;
    }

    public Nota getNota(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE id=?", new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()) {
            Nota nota = new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            return nota;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Nota> listarNotas() {
        ArrayList<Nota> result = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result.add(new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }

    public boolean removeNota(int id) {
        return db.delete("notas", "id=?", new String[]{Integer.toString(id)}) > 0;
    }

    public boolean atualizarNota(Nota n) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", n.getTitulo());
        contentValues.put("conteudo", n.getConteudo());
        return db.update("notas", contentValues, "id=?", new String[]{Integer.toString(n.getId())}) > 0;
    }
}
