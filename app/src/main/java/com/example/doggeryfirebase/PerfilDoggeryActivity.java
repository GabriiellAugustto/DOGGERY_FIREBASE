package com.example.doggeryfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

public class PerfilDoggeryActivity extends AppCompatActivity {

    TextView nomeper,foneper,bioper;
    ImageView imgper,btvolta ,ivper;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button btnstart;



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_doggery_layout);
        btnstart = findViewById(R.id.btnstart);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SolicitaçaoActivity.class));
            }
        });



        ivper = findViewById(R.id.ivper);

        nomeper = findViewById(R.id.nomeper);
        foneper = findViewById(R.id.foneper);
        imgper = findViewById(R.id.imgper);
        bioper = findViewById(R.id.bioper);
        btvolta = findViewById(R.id.btvolta);


        btvolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DogWalkerActivity.class));
            }
        });
        Intent intent= getIntent();

        String nome,telefone,bio;
        String imagem;

        nome = intent.getExtras().getString("Nome");
        telefone = intent.getExtras().getString("Telefone");
        imagem = intent.getExtras().getString("Imagem");
        bio = intent.getExtras().getString("Bio");

        nomeper.setText(nome);
        foneper.setText(telefone);
        bioper.setText(bio);
        Picasso.get().load(imagem).into(imgper);



        Uri fotousu = (Uri) FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        String nomeusu = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    Picasso.get().load(value.getString("foto")).into(ivper);
                }
            }
        });
    }
}