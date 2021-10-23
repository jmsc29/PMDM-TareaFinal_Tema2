package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Cuestionario extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Fragment> preguntas;
    private FragmentManager miManejador;
    private int contador = 0;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        preguntas = new ArrayList<>();
        preguntas.add(new Fragmento1());
        preguntas.add(new Fragmento2());
        preguntas.add(new Fragmento3());
        preguntas.add(new Fragmento4());
        preguntas.add(new Fragmento5());
        preguntas.add(new Fragmento6());
        preguntas.add(new Fragmento7());
        preguntas.add(new Fragmento8());

        siguiente = findViewById(R.id.bSiguiente);

        miManejador = getSupportFragmentManager();
        cargaFragmento(contador);

        siguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        contador++;
        if(contador < preguntas.size()){
            cargaFragmento(contador);
        }else{
            siguiente.setEnabled(false);
        }
    }

    private void cargaFragmento (int cuenta){
        FragmentTransaction transaction;
        transaction = miManejador.beginTransaction();
        transaction.replace(R.id.contenedor, preguntas.get(cuenta));
        transaction.commit();
    }

}