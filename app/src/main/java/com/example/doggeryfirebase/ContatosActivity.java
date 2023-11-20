package com.example.doggeryfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContatosActivity extends AppCompatActivity {

    List<User> userList;

    RecyclerView contatoslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos_layout);


        contatoslist = findViewById(R.id.chat2list);
        userList = new ArrayList<>();

        userList.add(
                new User(""
                        ,"","",""));

        userList.add(
                new User("",
                        "","",""));

        userList.add(
                new User("",
                        "","",""));

        userList.add(
                new User("",
                        "","",""));

        MyAdapterChat2 adapterChat2 = new MyAdapterChat2(getApplicationContext(),userList);
        contatoslist.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        contatoslist.setHasFixedSize(true);
        contatoslist.setAdapter(adapterChat2);

    }
}