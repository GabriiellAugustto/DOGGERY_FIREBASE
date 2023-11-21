package com.example.doggeryfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.carousel.CarouselLayoutManager;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DogWalkerActivity extends AppCompatActivity {
    List<User> userList;

    RecyclerView idListaUser;

    ImageView btnvoltar;
    MyAdapter adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_walker_layout);

        btnvoltar = findViewById(R.id.btnvoltar);

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

        idListaUser = findViewById(R.id.idListaUser);
        idListaUser.setHasFixedSize(true);
        idListaUser.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        db = FirebaseFirestore.getInstance();
        userList = new ArrayList<>();
        adapter = new MyAdapter(getApplicationContext(),userList);
        idListaUser.setAdapter(adapter);


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

                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()==DocumentChange.Type.ADDED){

                                userList.add(dc.getDocument().toObject(User.class));

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}




