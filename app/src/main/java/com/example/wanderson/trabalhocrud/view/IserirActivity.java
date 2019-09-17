package com.example.wanderson.trabalhocrud.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wanderson.trabalhocrud.banco.UsuarioDAO;
import com.example.wanderson.trabalhocrud.R;
import com.example.wanderson.trabalhocrud.UsuarioAdapter;
import com.example.wanderson.trabalhocrud.modelo.Usuario;

public class IserirActivity extends AppCompatActivity {
    EditText editTextNome,editTextTelefone, editTextEmail, editTextCpf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iserir);

        editTextNome = findViewById(R.id.editText);
        editTextTelefone = findViewById(R.id.editText2);
        editTextEmail = findViewById(R.id.editText3);
        editTextCpf = findViewById(R.id.editText4);


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(getBaseContext());

                Usuario usuario = new Usuario(editTextNome.getText().toString(), editTextTelefone.getText().toString(), editTextEmail.getText().toString(), editTextCpf.getText().toString());
                dao.iserirUsuario(usuario);
                finish();

            }
        });

    }
}

