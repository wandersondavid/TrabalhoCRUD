package com.example.wanderson.trabalhocrud.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.wanderson.trabalhocrud.modelo.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {
    private UsuarioDBHelper usuarioDBHelper;


    public UsuarioDAO(Context context){
        this.usuarioDBHelper = new UsuarioDBHelper(context);
    }


    public boolean iserirUsuario(Usuario usuario){
        SQLiteDatabase database = usuarioDBHelper.getWritableDatabase();
        ContentValues valalores = new ContentValues();
//        valalores.put(UsuarioContract.COLUNA_ID, usuario.getId());
        valalores.put(UsuarioContract.COLUNA_NOME, usuario.getNome());
        valalores.put(UsuarioContract.COLUNA_TELEFONE, usuario.getTelefone());
        valalores.put(UsuarioContract.COLUNA_EMAIL, usuario.getEmail());
        valalores.put(UsuarioContract.COLUNA_CPF, usuario.getCpf());



        long nroLinhos = database.insert(UsuarioContract.NOME_TABELA, null,  valalores);
        if (nroLinhos== -1)return false;
        return true;
    }

    public boolean edidUsuario(Usuario usuario) {

        SQLiteDatabase database = usuarioDBHelper.getWritableDatabase();
        ContentValues valalores = new ContentValues();
        valalores.put(UsuarioContract.COLUNA_NOME, usuario.getNome());
        valalores.put(UsuarioContract.COLUNA_TELEFONE, usuario.getTelefone());
        valalores.put(UsuarioContract.COLUNA_EMAIL, usuario.getEmail());
        valalores.put(UsuarioContract.COLUNA_CPF, usuario.getCpf());
        String selection = UsuarioContract.COLUNA_ID + " LIKE ?";
        String[] argumentoWhere = {String.valueOf(usuario.getId())};
        int count = database.update(
              UsuarioContract.NOME_TABELA,
                valalores,
                selection,
                argumentoWhere);

        if (count== -1)return false;
        return true;


    }


    public boolean deleteUsuario (int id){
        try {

        }catch (Exception e){

        }
        SQLiteDatabase sqLiteDatabase = usuarioDBHelper.getWritableDatabase();
        String where = UsuarioContract.COLUNA_ID+ "=?";
        String[] valuesWhere = {String.valueOf(id)};
        sqLiteDatabase.delete(UsuarioContract.NOME_TABELA, where, valuesWhere);
try {
    long nroLinhos = sqLiteDatabase.delete(UsuarioContract.NOME_TABELA, null,  valuesWhere);
    if (nroLinhos== -1)return false;

}catch (Exception e){
    Log.i("ERRO", ""+ e);

}
        return true;
    }


    public  ArrayList<Usuario> buscarUsuario(String s){
        SQLiteDatabase database = this.usuarioDBHelper.getReadableDatabase();

        String[] colunas = {UsuarioContract.COLUNA_ID, UsuarioContract.COLUNA_NOME, UsuarioContract.COLUNA_TELEFONE, UsuarioContract.COLUNA_EMAIL,UsuarioContract.COLUNA_CPF };
        String selection = UsuarioContract.COLUNA_NOME + " LIKE ?";
        String[] agumentoWhere = {"%"+s+"%"};

        String odernar = UsuarioContract.COLUNA_NOME + "ASC";

        Cursor resultado = database.query(UsuarioContract.NOME_TABELA, colunas,selection, agumentoWhere, null,null,null);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        while (resultado.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(resultado.getInt(resultado.getColumnIndex(UsuarioContract.COLUNA_ID)));
            usuario.setNome(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_NOME)));
            usuario.setTelefone(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_TELEFONE)));
            usuario.setEmail(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_EMAIL)));
            usuario.setCpf(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_CPF)));
            usuarios.add(usuario);
        }
        return usuarios;

    }


    public ArrayList<Usuario> buscarTodosUsuarios(){
        SQLiteDatabase database = this.usuarioDBHelper.getReadableDatabase();

        String[] colunas = {UsuarioContract.COLUNA_ID, UsuarioContract.COLUNA_NOME, UsuarioContract.COLUNA_TELEFONE, UsuarioContract.COLUNA_EMAIL,UsuarioContract.COLUNA_CPF };
        String odernar = UsuarioContract.COLUNA_NOME + "ASC";

        Cursor resultado = database.query(UsuarioContract.NOME_TABELA, colunas,null, null, null,null,null);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        while (resultado.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(resultado.getInt(resultado.getColumnIndex(UsuarioContract.COLUNA_ID)));
            usuario.setNome(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_NOME)));
            usuario.setTelefone(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_TELEFONE)));
            usuario.setEmail(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_EMAIL)));
            usuario.setCpf(resultado.getString(resultado.getColumnIndex(UsuarioContract.COLUNA_CPF)));
            usuarios.add(usuario);
        }
        return usuarios;
    }



}

