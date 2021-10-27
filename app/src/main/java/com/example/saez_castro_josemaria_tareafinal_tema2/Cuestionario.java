package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cuestionario extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Fragment> preguntas;
    private FragmentManager miManejador;
    private int contador = 0;
    private Button siguiente;
    private RadioGroup res;
    private CheckBox cb1, cb2, cb3, cb4;
    private TextView infoUsuario;
    private ProgressBar proB;

    private String edad;
    private String genero;
    private String provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        crearFragmentos();
        recogerInfo();

        siguiente = findViewById(R.id.bSiguiente);
        siguiente.setOnClickListener(this);

        miManejador = getSupportFragmentManager();
        cargaFragmento(preguntas.get(0));

        infoUsuario = findViewById(R.id.tvInfoUsuario);
        infoUsuario.setText("Edad: " + edad + "       Género: " + genero + "       Provincia: " + provincia);

        proB = findViewById(R.id.progressBar);
        proB.setProgress(0);

    }

    private void recogerInfo() {
        Bundle info = getIntent().getExtras();
        edad = info.getString("edad");
        genero = info.getString("genero");
        provincia = info.getString("provincia");
    }

    @Override
    public void onClick(View view) {
        contador++;
        Fragment f = null;
        if (contador < preguntas.size()) {
            f = preguntas.get(contador-1);
            cargaFragmento(f);
            switch(contador){
                case 1:
                    res = findViewById(R.id.rg1);
                    comprobarRespuesta(res, preguntas.get(contador));
                    proB.setProgress(contador);
                    break;
                case 2:
                    res = findViewById(R.id.rg2);
                    comprobarRespuesta(res, preguntas.get(contador));
                    proB.setProgress(contador);
                    break;
                case 3:
                    res = findViewById(R.id.rg3);
                    comprobarRespuesta(res, preguntas.get(contador));
                    proB.setProgress(contador);
                    break;
                case 4:
                    cb1 = findViewById(R.id.cb41);
                    cb2 = findViewById(R.id.cb42);
                    cb3 = findViewById(R.id.cb43);
                    cb4 = findViewById(R.id.cb44);
                    comprobarCheckBox(cb1, cb2, cb3, cb4, preguntas.get(contador));
                    proB.setProgress(contador);
                    break;
                case 5:
                    res = findViewById(R.id.rg5);
                    comprobarRespuesta(res, preguntas.get(contador));
                    proB.setProgress(contador);
                    break;
                case 6:
                    res = findViewById(R.id.rg6);
                    comprobarRespuesta(res, preguntas.get(contador));
                    proB.setProgress(contador);
                    break;
                case 7:
                    res = findViewById(R.id.rg7);
                    comprobarRespuesta(res, preguntas.get(contador));
                    siguiente.setText("Finalizar");
                    proB.setProgress(contador);
                    break;
                default:
                    break;
            }
        }else{
            res = findViewById(R.id.rg8);
            if (res.getCheckedRadioButtonId() != -1){
                proB.setProgress(contador);
                terminarCuestionario();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Elige una opción", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private void cargaFragmento(Fragment f) {
        FragmentTransaction transaction;
        transaction = miManejador.beginTransaction();
        transaction.replace(R.id.contenedor, f);
        transaction.commit();
    }

    private void comprobarRespuesta(RadioGroup res, Fragment pregunta){
        if(res.getCheckedRadioButtonId() == -1){
            contador--;
            Toast toast = Toast.makeText(getApplicationContext(), "Elige una opción", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            cargaFragmento(pregunta);
        }
    }

    private void comprobarCheckBox(CheckBox res1, CheckBox res2, CheckBox res3, CheckBox res4, Fragment pregunta){
        if(!res1.isChecked() && !res2.isChecked() && !res3.isChecked() && !res4.isChecked()){
            contador--;
            Toast toast = Toast.makeText(getApplicationContext(), "Elige al menos una opción", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            cargaFragmento(pregunta);
        }
    }

    private void terminarCuestionario(){
        Intent intent = new Intent(Cuestionario.this, Confirmacion.class);
        intent.putExtra("edad", edad);
        intent.putExtra("genero", genero);
        intent.putExtra("provincia", provincia);
        startActivity(intent);
    }

    private void crearFragmentos(){
        preguntas = new ArrayList<>();
        preguntas.add(new Fragmento1());
        preguntas.add(new Fragmento2());
        preguntas.add(new Fragmento3());
        preguntas.add(new Fragmento4());
        preguntas.add(new Fragmento5());
        preguntas.add(new Fragmento6());
        preguntas.add(new Fragmento7());
        preguntas.add(new Fragmento8());
    }

}