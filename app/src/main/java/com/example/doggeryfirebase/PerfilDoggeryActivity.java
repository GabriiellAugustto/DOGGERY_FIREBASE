package com.example.doggeryfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class PerfilDoggeryActivity extends AppCompatActivity {

    TextView nomeper,foneper,bioper;
    ImageView imgper,btvolta;




    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_doggery_layout);

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
    }
}