package com.example.doggeryfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ContatosActivity extends AppCompatActivity {

    List<User> userList;

    RecyclerView contatoslist;

    MyAdapterChat2 adapterChat2;
    FirebaseFirestore db;

    ImageButton voltar;

    ImageView ivcont;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos_layout);

        ivcont = findViewById(R.id.ivcont);

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



        Uri fotousu = (Uri) FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        String nomeusu = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    Picasso.get().load(value.getString("foto")).into(ivcont);
                }
            }
        });

    }
}