package com.example.doggeryfirebase;

public class User {
    String nome;
    String Telefone;
    String foto;

    String bio;

    public User (){}

  public User(String nome, String telefone, String foto, String bio) {
        this.nome = nome;
        Telefone = telefone;
        this.foto = foto;
        this.bio = bio;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    }

