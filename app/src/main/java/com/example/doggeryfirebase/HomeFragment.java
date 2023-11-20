package com.example.doggeryfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class HomeFragment extends Fragment {

    HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);


        ImageButton ibperfil = (ImageButton) view.findViewById(R.id.ibperfil);
        ImageButton ibchat = (ImageButton) view.findViewById(R.id.btnchat);
        CardView ibwalker = (CardView) view.findViewById(R.id.btndogwalker);

        ibperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), PerfilActivity.class));
            }
        });

        ibchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), ContatosActivity.class));
            }
        });

        ibwalker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), DogWalkerActivity.class));
            }
        });


return view;
    }
}