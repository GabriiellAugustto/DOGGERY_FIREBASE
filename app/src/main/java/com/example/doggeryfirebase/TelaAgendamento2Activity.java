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
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

public class TelaAgendamento2Activity extends AppCompatActivity {

    List<Agenda> agendaList;
    RecyclerView agerecy;
    AdaptadorAgenda adaptadorAgenda;
    FirebaseFirestore bd;

    ImageButton btvolt;
    ImageView ivage;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_agendamento2_layout);

        ivage = findViewById(R.id.ivage);
        btvolt = findViewById(R.id.btvolt);

        btvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaAgendamento2Activity.this, MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });



        agerecy = findViewById(R.id.agerecy);
        agerecy.setHasFixedSize(true);
        agerecy.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        bd = FirebaseFirestore.getInstance();
        agendaList = new ArrayList<>();
        adaptadorAgenda= new AdaptadorAgenda(getApplicationContext(),agendaList);
        agerecy.setAdapter(adaptadorAgenda);


        puxandodadosdobanco();



        Uri fotousu = (Uri) FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        String nomeusu = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = bd.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    Picasso.get().load(value.getString("foto")).into(ivage);
                }
            }
        });


    }

    private void puxandodadosdobanco(){

        bd.collection("Agenda")
                .orderBy("Dia", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("teste", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                agendaList.add(dc.getDocument().toObject(Agenda.class));

                            }
                            adaptadorAgenda.notifyDataSetChanged();
                        }
                    }
                });
    }
}