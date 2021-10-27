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
import android.widget.RadioButton;
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
    private RadioButton respuesta;
    private CheckBox cb1, cb2, cb3, cb4;
    private CheckBox[] checkBoxes;
    private TextView infoUsuario;
    private ProgressBar proB;

    private String edad;
    private String genero;
    private String provincia;
    private String[] respuestas = new String[8];
    private boolean servidor;

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
        proB.setProgress(1);

    }

    private void recogerInfo() {
        Bundle info = getIntent().getExtras();
        edad = info.getString("edad");
        genero = info.getString("genero");
        provincia = info.getString("provincia");
        servidor = info.getBoolean("server");
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
                    comprobarRadioButton(res, preguntas.get(contador));
                    proB.setProgress(contador+1);
                    break;
                case 2:
                    res = findViewById(R.id.rg2);
                    comprobarRadioButton(res, preguntas.get(contador));
                    proB.setProgress(contador+1);
                    break;
                case 3:
                    res = findViewById(R.id.rg3);
                    comprobarRadioButton(res, preguntas.get(contador));
                    proB.setProgress(contador+1);
                    break;
                case 4:
                    cb1 = findViewById(R.id.cb41);
                    cb2 = findViewById(R.id.cb42);
                    cb3 = findViewById(R.id.cb43);
                    cb4 = findViewById(R.id.cb44);
                    checkBoxes = new CheckBox[]{cb1, cb2, cb3, cb4};
                    comprobarCheckBox(cb1, cb2, cb3, cb4, preguntas.get(contador));
                    proB.setProgress(contador+1);
                    break;
                case 5:
                    res = findViewById(R.id.rg5);
                    comprobarRadioButton(res, preguntas.get(contador));
                    proB.setProgress(contador+1);
                    break;
                case 6:
                    res = findViewById(R.id.rg6);
                    comprobarRadioButton(res, preguntas.get(contador));
                    proB.setProgress(contador+1);
                    break;
                case 7:
                    res = findViewById(R.id.rg7);
                    comprobarRadioButton(res, preguntas.get(contador));
                    siguiente.setText("Finalizar");
                    proB.setProgress(contador+1);
                    break;
                default:
                    break;
            }
        }else{
            res = findViewById(R.id.rg8);
            if (res.getCheckedRadioButtonId() != -1){
                terminarCuestionario();
            }else{
                contador--;
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

    private void comprobarRadioButton(RadioGroup res, Fragment pregunta){
        if(res.getCheckedRadioButtonId() == -1){
            contador--;
            Toast toast = Toast.makeText(getApplicationContext(), "Elige una opción", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            respuestas[contador-1] = RadioButtonElegido();
            cargaFragmento(pregunta);
        }
    }

    private void comprobarCheckBox(CheckBox res1, CheckBox res2, CheckBox res3, CheckBox res4, Fragment pregunta){
        if(!res1.isChecked() && !res2.isChecked() && !res3.isChecked() && !res4.isChecked()){
            contador--;
            Toast toast = Toast.makeText(getApplicationContext(), "Elige al menos una opción", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            respuestas[contador-1] = CheckBoxElegido();
            cargaFragmento(pregunta);
        }
    }

    private String RadioButtonElegido(){
        int elegido = res.getCheckedRadioButtonId();
        View rb = res.findViewById(elegido);
        return String.valueOf(res.indexOfChild(rb));
    }

    private String CheckBoxElegido(){
        String checkBoxElegidos = "";
        for(int i = 0; i < checkBoxes.length; i++){
            if(checkBoxes[i].isChecked()){
                checkBoxElegidos += (i+1);
            }
        }
        return checkBoxElegidos;
    }

    private void terminarCuestionario(){
        respuestas[contador-1] = RadioButtonElegido();

        Bundle b = new Bundle();
        b.putStringArray("respuestas", respuestas);

        Intent intent = new Intent(Cuestionario.this, Confirmacion.class);
        intent.putExtra("edad", edad);
        intent.putExtra("genero", genero);
        intent.putExtra("provincia", provincia);
        intent.putExtra("server", servidor);
        intent.putExtras(b); // Para poder pasar la array
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