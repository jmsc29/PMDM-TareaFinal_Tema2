package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SecundaryActivity extends AppCompatActivity {

    private Spinner listaP;
    private Button continuar;
    private RadioGroup genero;
    private EditText edad;
    private RadioButton generoElegido;
    private Switch swServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        //ESTO LO USO PARA QUE NO ME APAREZCA EL TECLADO AUTOMÁTICAMENTE AL INICIAR LA ACTIVIDAD
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //EN LA VISTA DE ESTA CLASE, ES DECIR, EN EL CONSTRAINT LAYOUT
        //AÑADO LAS LÍNEAS
        //android:focusable="true"
        //android:focusableInTouchMode="true"
        //PARA QUE NO TENGA COMO PREDETERMINADO NINGÚN EDITTEXT AL INICIO Y QUE SOLO SE MARQUE EN EL MOMENTO
        //EN EL QUE YO LO SELECCIONE

        listaP = findViewById(R.id.spListaP);
        String[] provincias = {"","Jaén", "Córdoba", "Sevilla", "Huelva", "Cádiz", "Málaga", "Granada", "Almería"};
        //ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provincias);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, provincias) {
            //Esto lo uso para poner el la primera elección que es una cadena vacía, en invisible
            //para así poder controlar que sea obligatorio que lo elija el usuario
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = null;
                // Para el valor inicial que está en la posición cero
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0); //Establezco altura a 0
                    tv.setVisibility(View.GONE); //Cambio su visibilidad
                    v = tv;
                }
                else {
                    v = super.getDropDownView(position, null, parent);
                }

                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaP.setAdapter(adaptador);

        continuar = findViewById(R.id.bContinuar);
        continuar.setOnClickListener(listenerStartQuiz);

        genero = findViewById(R.id.rgGenero);
        edad = findViewById(R.id.etEdad);
        edad.clearFocus();

        swServer = findViewById(R.id.swDatos);

    }

    private View.OnClickListener listenerStartQuiz = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (genero.getCheckedRadioButtonId() == -1 || edad.getText().toString().isEmpty() || listaP.getSelectedItem().toString() == "") {
                Toast toast = Toast.makeText(getApplicationContext(), "Introduce todos los datos para continuar", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                int idGenero = genero.getCheckedRadioButtonId();
                generoElegido = findViewById(idGenero);

                Intent intent = new Intent(SecundaryActivity.this, Cuestionario.class);
                intent.putExtra("edad", edad.getText().toString());
                intent.putExtra("genero", generoElegido.getText().toString());
                intent.putExtra("provincia", listaP.getSelectedItem().toString());
                intent.putExtra("server", swServer.isChecked());
                startActivity(intent);
            }
        }
    };
}