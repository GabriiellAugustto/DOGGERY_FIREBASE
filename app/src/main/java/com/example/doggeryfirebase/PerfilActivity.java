package com.example.doggeryfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.internal.StorageReferenceUri;

public class PerfilActivity extends AppCompatActivity {

    TextView txnome,txemail,txfone,edit,txbio;
    Button btsair;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage fs= FirebaseStorage.getInstance();
    String nomeusu;
    String emailusu;
    String foneusu;
    String biousu;
    String fotousu;


    ImageView fotoperfil,btvol;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_layout);

        txnome = findViewById(R.id.txnome);
        txemail = findViewById(R.id.txemail);
        btsair = findViewById(R.id.btsair);
        txfone = findViewById(R.id.txfone);
        edit = findViewById(R.id.edit);
        txbio = findViewById(R.id.txbio);
        fotoperfil = findViewById(R.id.fotoperfil);
        btvol = findViewById(R.id.btvol);

        btvol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, EditActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        biousu = FirebaseAuth.getInstance().getCurrentUser().toString();
        foneusu = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        emailusu = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        nomeusu = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (documentSnapshot != null){
                    txnome.setText(documentSnapshot.getString("nome"));
                    txfone.setText(documentSnapshot.getString("telefone"));
                    txbio.setText(documentSnapshot.getString("bio"));
                    txemail.setText(emailusu);

                }

            }
        });
    }

}