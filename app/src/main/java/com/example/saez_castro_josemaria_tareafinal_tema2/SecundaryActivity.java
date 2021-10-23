package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SecundaryActivity extends AppCompatActivity{

    private Spinner listaP;
    private Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        listaP = findViewById(R.id.spListaP);
        String[] provincias = {"Jaén", "Córdoba", "Sevilla", "Huelva", "Cádiz", "Málaga", "Granada", "Almería"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provincias);
        listaP.setAdapter(adaptador);

        continuar = findViewById(R.id.bContinuar);
        continuar.setOnClickListener(listenerStartQuiz);

    }

    private View.OnClickListener listenerStartQuiz = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecundaryActivity.this, Cuestionario.class);
                startActivity(intent);
            }
    };

}