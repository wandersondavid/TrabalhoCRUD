package com.example.wanderson.trabalhocrud.banco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BuscarDados {
    private UsuarioDBHelper mDbHelper;


    public BuscarDados(Context context){

        this.mDbHelper = new UsuarioDBHelper(context);
    }
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            UsuarioContract.COLUNA_NOME,
            UsuarioContract.COLUNA_TELEFONE,
            UsuarioContract.COLUNA_EMAIL,
            UsuarioContract.COLUNA_CPF
    };

    // Filter results WHERE "title" = 'My Title'
    String selection = UsuarioContract.COLUNA_NOME + " = ?";
    String[] selectionArgs = { "My Title" };

    // How you want the results sorted in the resulting Cursor
    String sortOrder =
            UsuarioContract.COLUNA_EMAIL + " DESC";

    Cursor cursor = db.query(
            UsuarioContract.NOME_TABELA,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
    );
}
