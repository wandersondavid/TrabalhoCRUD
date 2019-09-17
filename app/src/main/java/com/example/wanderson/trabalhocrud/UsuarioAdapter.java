package com.example.wanderson.trabalhocrud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanderson.trabalhocrud.banco.UsuarioDAO;
import com.example.wanderson.trabalhocrud.modelo.Usuario;
import com.example.wanderson.trabalhocrud.view.AuterarActivity;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioViewHolder> {

    private List<Usuario> usuarios;
    private Context context;

    public UsuarioAdapter(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View linha = LayoutInflater.from(context)
                .inflate(R.layout.lista_layaut, viewGroup, false);
        UsuarioViewHolder holder = new UsuarioViewHolder(linha);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder, final int i) {
        final Usuario usuario = usuarios.get(i);
        usuarioViewHolder.campoNome.setText(usuario.getNome());
        usuarioViewHolder.campoTelefone.setText(usuario.getTelefone());
        usuarioViewHolder.campoEmail.setText(usuario.getEmail());
        usuarioViewHolder.campoCpf.setText(usuario.getCpf());

        usuarioViewHolder.inteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Editar este Usuario?")
                        .setMessage(usuario.getNome())
                        .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Activity activity = getActivity(view);
                                Intent intent = new Intent(activity, AuterarActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.putExtra("usuario", usuario);
                                activity.startActivity(intent);

                            }

                        }).setNegativeButton("CANCELAR", null)
                        .create()
                        .show();

            }


        });


        final Usuario usuario1 = usuarios.get(i);
        usuarioViewHolder.inteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Excluir este Usuario?")
                        .setMessage(usuario.getNome())
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Usuario usuario2 = usuario1;
                                UsuarioDAO dao = new UsuarioDAO(view.getContext());
                                boolean sucesso = dao.deleteUsuario(usuario.getId());
                                if (sucesso) {
                                    removerUsuario(usuario);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                } else {
                                    Snackbar.make(view, "Erro ao excluir o cliente!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }

                        }).setNegativeButton("CANCELAR", null)
                        .create()
                        .show();

                return true;
            }

        });
    }

    @Override
    public int getItemCount() {
        return this.usuarios.size();
    }


    public void removerUsuario(Usuario usuario) {
        int position = usuarios.indexOf(usuario);
        usuarios.remove(position);
        notifyItemRemoved(position);
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public void atualizarUsuario(Usuario usuario) {


        usuarios.set(usuarios.indexOf(usuario), usuario);
        notifyItemChanged(usuarios.indexOf(usuario));
    }

}

