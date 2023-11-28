package com.example.doggeryfirebase;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AgendaFragment extends Fragment {

    AgendaFragment(){}

    List<User> userList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MyAdapterAgenda adapterAgenda;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_agenda, container, false);


        Button agendar;
        agendar = (Button) view.findViewById(R.id.btAgendar);
        EditText dia , mes ,ano ,hora;
        dia = (EditText) view.findViewById(R.id.dia);
        mes = (EditText) view.findViewById(R.id.mes);
        ano = (EditText) view.findViewById(R.id.Ano);
        hora = (EditText) view.findViewById(R.id.hora);

        agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String Dia = dia.getText().toString();
                String Mes = mes.getText().toString();
                String Ano = ano.getText().toString();
                String Hora = hora.getText().toString();


                Map<String, String> agenda = new HashMap<>();
                agenda.put("Dia",Dia);
                agenda.put("Mes",Mes);
                agenda.put("Ano",Ano);
                agenda.put("Hora",Hora);

                String agendaid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference documentReference1 = db.collection("Agenda").document(agendaid);
                documentReference1.set(agenda).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });


      @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView ivage = (ImageView) view.findViewById(R.id.ivage);
      @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tvage = (TextView) view.findViewById(R.id.tvage);

      RecyclerView listaage = (RecyclerView) view.findViewById(R.id.listage);

        listaage.setLayoutManager(new GridLayoutManager(getContext(), 1));
        listaage.setHasFixedSize(true);
        db= FirebaseFirestore.getInstance();
        userList = new ArrayList<>();
        adapterAgenda = new MyAdapterAgenda(getContext(),userList);
        listaage.setAdapter(adapterAgenda);

        db.collection("Usuarios")
                .orderBy("nome", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.e("teste",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()==DocumentChange.Type.ADDED){
                                userList.add(dc.getDocument().toObject(User.class));
                            }
                            adapterAgenda.notifyDataSetChanged();
                        }
                    }
                });

        Uri fotousu = (Uri) FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        String nomeusu = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(nomeusu);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    Picasso.get().load(value.getString("foto")).into(ivage);
                    tvage.setText(value.getString("nome"));

                }
            }
        });


       return view;
    }
}