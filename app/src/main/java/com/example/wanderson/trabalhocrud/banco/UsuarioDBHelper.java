package com.example.wanderson.trabalhocrud.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDBHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UsuarioContract.NOME_TABELA + " (" +
                    UsuarioContract.COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UsuarioContract.COLUNA_NOME + " TEXT," +
                    UsuarioContract.COLUNA_TELEFONE + " TEXT," +
                    UsuarioContract.COLUNA_EMAIL + " TEXT," +
                    UsuarioContract.COLUNA_CPF + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UsuarioContract.NOME_TABELA;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public UsuarioDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

