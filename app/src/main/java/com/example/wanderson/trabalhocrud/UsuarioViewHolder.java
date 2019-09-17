package com.example.wanderson.trabalhocrud;

import android.provider.BaseColumns;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class UsuarioViewHolder extends RecyclerView.ViewHolder {
    TextView campoNome;
    TextView campoEmail;
    TextView campoTelefone;
    TextView campoCpf;
    View inteView;



    public UsuarioViewHolder( View itemView) {
        super(itemView);

        campoNome = itemView.findViewById(R.id.textViewNome);
        campoTelefone = itemView.findViewById(R.id.textViewFone);
        campoEmail = itemView.findViewById(R.id.textViewEmail);
        campoCpf = itemView.findViewById(R.id.textViewCpf);
        this.inteView=itemView;
    }
}
