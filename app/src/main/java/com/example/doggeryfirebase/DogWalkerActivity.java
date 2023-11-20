package com.example.doggeryfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class DogWalkerActivity extends AppCompatActivity {
    List<User> userList;

    RecyclerView idListaUser;

    ImageView btnvoltar;

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

        userList = new ArrayList<>();

        userList.add(
                new User("Augusto","35988214598"));

        userList.add(
                new User("Jesus","359876523"));

        userList.add(
                new User("Pedro","11326598741"));

        userList.add(
                new User("Tiago","11362543265"));

        MyAdapter adapter = new MyAdapter(getApplicationContext(),userList);

        idListaUser.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        idListaUser.setHasFixedSize(true);

        idListaUser.setAdapter(adapter);

    }
}