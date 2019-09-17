package com.example.wanderson.trabalhocrud.banco;

import android.provider.BaseColumns;

public final class UsuarioContract implements BaseColumns{
    public UsuarioContract() {
    }

    public static final String NOME_TABELA = "usuarios";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME ="nome";
    public static final String COLUNA_TELEFONE="telefone";
    public static final String COLUNA_EMAIL="email";
    public static final String COLUNA_CPF="cpf";

}
