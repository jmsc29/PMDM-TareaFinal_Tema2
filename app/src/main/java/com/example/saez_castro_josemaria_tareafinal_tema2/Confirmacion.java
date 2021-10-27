package com.example.saez_castro_josemaria_tareafinal_tema2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Confirmacion extends AppCompatActivity {

    private TextView infoU, respuestas;
    private String edad, genero, provincia;
    String[] respuestass;
    private Button bVolver, bFinalizar;

    private boolean servidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        infoU = findViewById(R.id.tvInfoUsuarioFinal);
        respuestas = findViewById(R.id.tvMuestraRespuestas);

        Bundle info = getIntent().getExtras();

        edad = info.getString("edad");
        genero = info.getString("genero");
        provincia = info.getString("provincia");
        respuestass = info.getStringArray("respuestas");
        servidor = info.getBoolean("server");

        infoU.setText("Edad: " + edad + "       Género: " + genero + "       Provincia: " + provincia);

        String resp = "";
        for (int i = 0; i < respuestass.length; i++) {
            resp += "cuest" + (i + 1) + " = " + respuestass[i] + "     ";
            if(i == 3){
                resp += "\n";
            }
        }
        respuestas.setText(resp);

        TextView campo = (TextView) findViewById(R.id.tvInfoServer);

        if (servidor) {
            respuestaServidor(campo);
        }else{
            campo.setText("No has activado la opción de enviar datos al servidor");
        }

        bVolver = findViewById(R.id.bVolver);
        bFinalizar = findViewById(R.id.bFinalizar);

        bVolver.setOnClickListener(volverInicio);
        bFinalizar.setOnClickListener(finalizar);

    }

    public void respuestaServidor(TextView campo){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url  = "http://www.iestrassierra.net/alumnado/curso2021/DAMS/dams2021a6/Pruebas/" +
                    "tarea2.php?" +
                    "edad="+edad+
                    "&genero="+genero+
                    "&provincia="+provincia+
                    "&cuest1="+respuestass[0]+
                    "&cuest2="+respuestass[1]+
                    "&cuest3="+respuestass[2]+
                    "&cuest4="+respuestass[3]+
                    "&cuest5="+respuestass[4]+
                    "&cuest6="+respuestass[5]+
                    "&cuest7="+respuestass[6]+
                    "&cuest8="+respuestass[7];
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        campo.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //EN EL ARCHIVO MANIFIESTO, DENTRO DE LA ETIQUETA APLICATION
                //TENGO QUE AÑADIR LA LÍNEA --> android:usesCleartextTraffic="true"
                //PARA PODER TENER ACCESO AL SERVIDOR, YA QUE SIN ESA LÍNEA
                //SÓLO FUNCIONA EN EL EMULADOR Y NO EN UN DISPOSITIVO FÍSICO REAL
                campo.setText("No se ha podido obtener información del servidor");
                System.out.println(error.toString());
            }
        });
        queue.add(stringRequest);
    }

    private View.OnClickListener volverInicio = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Confirmacion.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener finalizar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finishAffinity();
        }
    };

}