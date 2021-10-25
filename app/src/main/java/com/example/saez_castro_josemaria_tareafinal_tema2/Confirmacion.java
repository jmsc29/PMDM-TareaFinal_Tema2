package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Confirmacion extends AppCompatActivity {

    private TextView infoU;
    String edad;
    String genero;
    String provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        infoU = findViewById(R.id.tvInfoUsuarioFinal);

        Bundle info = getIntent().getExtras();

        edad = info.getString("edad");
        genero = info.getString("genero");
        provincia = info.getString("provincia");

        infoU.setText("Edad: " + edad + "       Género: " + genero + "       Provincia: " + provincia);
    }
}