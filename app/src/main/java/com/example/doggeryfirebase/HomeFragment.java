package com.example.doggeryfirebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;


public class HomeFragment extends Fragment {

    HomeFragment(){}

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView ibperfil = (ImageView) view.findViewById(R.id.ibperfil);
        ImageButton ibchat = (ImageButton) view.findViewById(R.id.btnchat);
        CardView ibwalker = (CardView) view.findViewById(R.id.btndogwalker);
        TextView idnome = (TextView) view.findViewById(R.id.idnome1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView pefcard= (CardView) view.findViewById(R.id.pefcard);


        idnome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PerfilActivity.class) );
            }
        });
        pefcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), PerfilActivity.class));
            }
        });


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

        Uri fotousu = (Uri) FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        String nomeusu = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    Picasso.get().load(value.getString("foto")).into(ibperfil);
                    idnome.setText(value.getString("nome"));

                }
            }
        });
            return view;
    }
}
