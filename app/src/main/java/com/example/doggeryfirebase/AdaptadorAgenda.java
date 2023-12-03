package com.example.doggeryfirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorAgenda extends RecyclerView.Adapter<AdaptadorAgenda.ViewHolder> {
    private Context context;
    private List<Agenda> agendaList;


    public AdaptadorAgenda(Context context,List<Agenda> agendaList){
        this.context = context;
        this.agendaList = agendaList;
    }


    @NonNull
    @Override
    public AdaptadorAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.user_agenda,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAgenda.ViewHolder holder, int position) {
        holder.dia.setText(agendaList.get(position).getDia());
        holder.mes.setText(agendaList.get(position).getMes());
        holder.ano.setText(agendaList.get(position).getAno());
        holder.hora.setText(agendaList.get(position).getHora());

    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dia,mes,ano,hora;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dia = itemView.findViewById(R.id.dia);
            mes = itemView.findViewById(R.id.mes);
            ano = itemView.findViewById(R.id.ano);
            hora = itemView.findViewById(R.id.hora);


        }
    }
}