package com.example.wanderson.trabalhocrud.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SearchView;

import com.example.wanderson.trabalhocrud.banco.UsuarioDAO;
import com.example.wanderson.trabalhocrud.R;
import com.example.wanderson.trabalhocrud.UsuarioAdapter;
import com.example.wanderson.trabalhocrud.modelo.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Usuario> usuarios;
    UsuarioDAO dao;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IserirActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
         recyclerView = findViewById(R.id.my_recycler_view);

        dao = new UsuarioDAO(this);

        usuarios = dao.buscarTodosUsuarios();

        UsuarioAdapter adapter = new UsuarioAdapter(usuarios, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

         MenuItem searchItem = menu.findItem(R.id.search);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setOnQueryTextListener(pesuisar());




        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }


    private  SearchView.OnQueryTextListener pesuisar() {

        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Log.i("D", "nome= "+s);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dao = new UsuarioDAO(getBaseContext());
                ArrayList<Usuario> usuarios = dao.buscarUsuario(s);
                UsuarioAdapter adapter = new UsuarioAdapter(usuarios, MainActivity.this);
                recyclerView.setAdapter(adapter);
                Log.i("D", "nome= "+s);

                return true;
            }
        };


    }



}
