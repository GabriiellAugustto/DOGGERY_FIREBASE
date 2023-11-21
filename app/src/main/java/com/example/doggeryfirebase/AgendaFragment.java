package com.example.doggeryfirebase;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AgendaFragment extends Fragment {

    AgendaFragment(){}

    List<User> userList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_agenda, container, false);

      @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView ivage = (ImageView) view.findViewById(R.id.ivage);
      @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tvage = (TextView) view.findViewById(R.id.tvage);

      RecyclerView listaage = (RecyclerView) view.findViewById(R.id.listage);

        userList = new ArrayList<>();



        MyAdapterAgenda adapter = new MyAdapterAgenda(getContext(),userList);

        listaage.setLayoutManager(new GridLayoutManager(getContext(),1));

        listaage.setHasFixedSize(true);

        listaage.setAdapter(adapter);






        Uri fotousu = (Uri) FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        String nomeusu = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    Picasso.get().load(value.getString("foto")).into(ivage);
                    tvage.setText(value.getString("nome"));

                }
            }
        });


       return view;
    }
}