package com.example.doggeryfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class AgendaFragment extends Fragment {

    AgendaFragment(){}

    List<User> userList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_agenda, container, false);

      RecyclerView listaage = (RecyclerView) view.findViewById(R.id.listage);

        userList = new ArrayList<>();

        userList.add(
                new User("","","",""));

        userList.add(
                new User("","","",""));

        userList.add(
                new User("","","",""));

        userList.add(
                new User("","","",""));

        MyAdapterAgenda adapter = new MyAdapterAgenda(getContext(),userList);

        listaage.setLayoutManager(new GridLayoutManager(getContext(),1));

        listaage.setHasFixedSize(true);

        listaage.setAdapter(adapter);




       return view;
    }
}