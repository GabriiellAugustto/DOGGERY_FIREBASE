package com.example.doggeryfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    EditText edtnome,edtemail,edtsenha;
    Button btcad;
    String[] mensagens = {"Prencha Todos os Campos", "Cadastro Realizado Com Sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);

        edtnome = findViewById(R.id.edtnome);
        edtemail = findViewById(R.id.edtemail);
        edtsenha = findViewById(R.id.edtsenha);
        btcad = findViewById(R.id.btcad);


        btcad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edtnome.getText().toString();
                String email= edtemail.getText().toString();
                String senha = edtsenha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else {

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                     if (task.isSuccessful()){

                         SalvarDados();

                         Snackbar snackbar = Snackbar.make(v,mensagens[1 ],Snackbar.LENGTH_SHORT);
                         snackbar.setBackgroundTint(Color.WHITE);
                         snackbar.setTextColor(Color.BLACK);
                         snackbar.show();

                     }else {
                         String erro;
                         try {
                            throw task.getException();
                         }catch (FirebaseAuthWeakPasswordException e ){
                             erro ="Senha Invalida";
                         }catch (FirebaseAuthUserCollisionException e){
                             erro = "Conta ja Cadastrada ";
                         }catch (FirebaseAuthInvalidCredentialsException e){
                             erro = "Email invalido";
                         }catch (Exception e){
                             erro = "Erro ao cadastrar";
                         }
                         Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                         snackbar.setBackgroundTint(Color.WHITE);
                         snackbar.setTextColor(Color.BLACK);
                         snackbar.show();

                     }
                     }
                    });
                }
            }
        });
    }

    private void SalvarDados() {
        String nome = edtnome.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, String> usuarios = new HashMap<>();
        usuarios.put("nome",nome);

        String usuarioid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioid);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_erro","Erro");
                    }
                });
    }
}