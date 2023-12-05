package com.example.doggeryfirebase;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class EditActivity extends AppCompatActivity {

    EditText bioed, nomeed, foneed;
    Button btsalvar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imgvoltar;
    ImageView imgperf;
    Uri imgusu;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        bioed = findViewById(R.id.bioed);
        nomeed = findViewById(R.id.nomeed);
        foneed = findViewById(R.id.foneed);
        btsalvar = findViewById(R.id.btsalvar);
        imgvoltar = findViewById(R.id.imgvoltar);
        imgperf = findViewById(R.id.imgperf);


        imgvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, PerfilActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //editando perfil //

        btsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeed.getText().toString();
                String telefone = foneed.getText().toString();
                String bio = bioed.getText().toString();


                if (nome.isEmpty() || telefone.isEmpty() || bio.isEmpty()) {

                    Snackbar snackbar = Snackbar.make(v, "Campos em braco", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();


                } else {

                    SalvarFoto();

                    Snackbar snackbar = Snackbar.make(v, "Salvo com Sucessso", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                    startActivity(new Intent(getApplicationContext(), PerfilActivity.class));


                }
            }
        });

        //chamndo foto da galeria
        imgperf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Escolha Foto de Perfil"), 1);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dados) {
        super.onActivityResult(requestCode, resultCode, dados);
        if (resultCode == Activity.RESULT_OK) {
            if (resultCode == 1) {
            }
            imgusu = dados.getData();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgusu);
                imgperf.setImageDrawable(new BitmapDrawable(bitmap));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    //salvando//
    private void SalvarFoto() {
        String perfil = UUID.randomUUID().toString();
        StorageReference reference = FirebaseStorage.getInstance().getReference("/ImgPerf/" + perfil);
        reference.putFile(imgusu).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("teste", uri.toString());

                                String foto = uri.toString();
                                String nome = nomeed.getText().toString();
                                String telefone = foneed.getText().toString();
                                String bio = bioed.getText().toString();

                                Map<String, String> usuarios = new HashMap<>();
                                usuarios.put("foto", foto);
                                usuarios.put("nome", nome);
                                usuarios.put("telefone", telefone);
                                usuarios.put("bio", bio);

                                String usuarioid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                DocumentReference documentReference = db.collection("Usuarios").document(usuarioid);
                                documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Log.d("db", "Sucesso");


                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("db_erro", "Erro");
                                            }
                                        });

                            }

                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Teste", e.getMessage(), e);
                    }
                });
    }
    }


    //Salavndo no banco de dados//
    /*private void Salvandodados() {
        String nome = nomeed.getText().toString();
        String telefone = foneed.getText().toString();
        String bio = bioed.getText().toString();


        Map<String, String> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        usuarios.put("telefone", telefone);
        usuarios.put("bio", bio);

        String usuarioid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioid);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("db", "Sucesso");


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_erro", "Erro");
                    }
                });
    }
}*/




