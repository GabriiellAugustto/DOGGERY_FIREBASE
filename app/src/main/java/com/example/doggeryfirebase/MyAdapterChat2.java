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

public class MyAdapterChat2 extends RecyclerView.Adapter<MyAdapterChat2.ViewHolder> {
    private Context context;
    private List<User> userList;


    public MyAdapterChat2 (Context context,List<User> userList){
        this.context = context;
        this.userList= userList;
    }


    @NonNull
    @Override
    public MyAdapterChat2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.user_modelo,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterChat2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.nomecard.setText(userList.get(position).getNome());
        holder.fonecard.setText(userList.get(position).getTelefone());
        holder.cardperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

    }

    @Override
    public int getItemCount() {
         return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomecard;
        TextView fonecard;
        CardView cardperfil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomecard = itemView.findViewById(R.id.nomecard);
            fonecard = itemView.findViewById(R.id.fonecard);
            cardperfil = itemView.findViewById(R.id.cardperfil);
        }
    }
}
