package com.example.doggeryfirebase;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.Context;

import java.util.List;

public class AdaptadorAgenda extends RecyclerView.Adapter<AdaptadorAgenda.ViewHolder> {

    private Context context;
    private List<Agenda> agendaList;
    @NonNull
    @Override
    public AdaptadorAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAgenda.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}