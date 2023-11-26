package com.example.doggeryfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContatosActivity extends AppCompatActivity {

    List<User> userList;

    RecyclerView contatoslist;

    MyAdapterChat2 adapterChat2;
    FirebaseFirestore db;

    ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos_layout);



        voltar = findViewById(R.id.btnvoltar);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });


        contatoslist = findViewById(R.id.chat2list);
        contatoslist.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        contatoslist.setHasFixedSize(true);
        db= FirebaseFirestore.getInstance();
        userList = new ArrayList<>();
        adapterChat2 = new MyAdapterChat2(getApplicationContext(),userList);
        contatoslist.setAdapter(adapterChat2);
        puxandodobd();

    }

    private void puxandodobd() {
        db.collection("Usuarios")
                .orderBy("nome", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.e("teste",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()==DocumentChange.Type.ADDED){
                                userList.add(dc.getDocument().toObject(User.class));
                            }
                            adapterChat2.notifyDataSetChanged();
                        }
                    }
                });


    }
}