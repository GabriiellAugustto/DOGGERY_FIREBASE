package com.example.doggeryfirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;

    public MyAdapter(Context context,List<User> userList){
        this.context = context;
        this.userList= userList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.user_modelo,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.nomecard.setText(userList.get(position).getNome());
        holder.fonecard.setText(userList.get(position).getTelefone());
        

        holder.cardperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PerfilDoggeryActivity.class);

                intent.putExtra("Nome",userList.get(position).getNome());
                intent.putExtra("Telefone",userList.get(position).getTelefone());
                intent.putExtra("Imagem",userList.get(position).getFoto());

                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nomecard;
        ImageView imgcard;
        TextView fonecard;
        CardView cardperfil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomecard = itemView.findViewById(R.id.nomecard);
            fonecard = itemView.findViewById(R.id.fonecard);
            imgcard = itemView.findViewById(R.id.imgcard);
            cardperfil = itemView.findViewById(R.id.cardperfil);
        }
    }
}
