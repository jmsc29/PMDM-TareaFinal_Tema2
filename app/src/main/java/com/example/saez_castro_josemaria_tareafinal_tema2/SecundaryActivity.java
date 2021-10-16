package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SecundaryActivity extends AppCompatActivity {

    private Spinner listaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        listaP = findViewById(R.id.spListaP);
        String[] provincias = {"Jaén", "Córdoba", "Sevilla", "Huelva", "Cádiz", "Málaga", "Granada", "Almería"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provincias);
        listaP.setAdapter(adaptador);
    }
}