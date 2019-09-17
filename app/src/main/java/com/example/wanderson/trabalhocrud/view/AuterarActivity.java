package com.example.wanderson.trabalhocrud.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wanderson.trabalhocrud.banco.UsuarioDAO;
import com.example.wanderson.trabalhocrud.R;
import com.example.wanderson.trabalhocrud.UsuarioAdapter;
import com.example.wanderson.trabalhocrud.modelo.Usuario;

public class AuterarActivity extends AppCompatActivity {
    EditText editText, editTextTelefone, editTextEmail, editTextCpf;
    Usuario usuarioEdit = null;
    UsuarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auterar);

        Intent intent = getIntent();
        if (intent.hasExtra("usuario")) {
            usuarioEdit = (Usuario) intent.getSerializableExtra("usuario");

            editText = findViewById(R.id.editTextNome);
            editTextTelefone = findViewById(R.id.editTextTelene);
            editTextEmail = findViewById(R.id.editTextEmail);
            editTextCpf = findViewById(R.id.editTextCpf);

            editText.setText(usuarioEdit.getNome());
            editTextTelefone.setText(usuarioEdit.getTelefone());
            editTextEmail.setText(usuarioEdit.getEmail());
            editTextCpf.setText(usuarioEdit.getCpf());
        }


        Button button = findViewById(R.id.buttonUpdete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(getBaseContext());

                    Usuario usuario = new Usuario(usuarioEdit.getId(), editText.getText().toString(), editTextTelefone.getText().toString(), editTextEmail.getText().toString(), editTextCpf.getText().toString());


                    try {
                        dao.edidUsuario(usuario);

                        adapter.atualizarUsuario(usuario);

                    } catch (Exception e) {
                        Log.i("erro", " " + e);
                    }
                    finish();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
