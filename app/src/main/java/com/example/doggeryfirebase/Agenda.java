package com.example.doggeryfirebase;

public class Agenda {

    String Dia;
    String Mes;

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String ano) {
        Ano = ano;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public Agenda(String dia, String mes, String ano, String hora) {
        Dia = dia;
        Mes = mes;
        Ano = ano;
        Hora = hora;
    }

    String Ano;
    String Hora;
}
