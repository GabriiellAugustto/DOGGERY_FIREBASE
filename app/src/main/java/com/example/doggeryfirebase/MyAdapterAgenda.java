package com.example.doggeryfirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterAgenda  extends RecyclerView.Adapter<MyAdapterAgenda.ViewHolder> {
    private Context context;
    private List<User> userList;

    public MyAdapterAgenda(Context context,List<User> userList){
        this.context = context;
        this.userList= userList;
    }
    @NonNull
    @Override
    public MyAdapterAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.user_modelo_agenda,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterAgenda.ViewHolder holder, int position) {

        holder.nomecard.setText(userList.get(position).getNome());
        holder.checage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomecard;
        TextView checage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomecard = itemView.findViewById(R.id.nomecard);
            checage = itemView.findViewById(R.id.checage);

        }
    }
}
