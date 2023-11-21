package com.example.doggeryfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos_layout);

        contatoslist = findViewById(R.id.chat2list);
        contatoslist.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        contatoslist.setHasFixedSize(true);

        db= FirebaseFirestore.getInstance();
        userList = new ArrayList<>();
        adapterChat2 = new MyAdapterChat2(getApplicationContext(),userList);


        contatoslist = findViewById(R.id.chat2list);
        userList = new ArrayList<>();

        userList.add(
                new User("","","",""));

        userList.add(
                new User("","","",""));

        userList.add(
                new User("","","",""));

        userList.add(
                new User("","","",""));

        MyAdapterChat2 adapterChat2 = new MyAdapterChat2(getApplicationContext(),userList);
        contatoslist.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        contatoslist.setHasFixedSize(true);
        contatoslist.setAdapter(adapterChat2);

    }
}