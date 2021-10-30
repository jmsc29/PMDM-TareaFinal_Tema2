package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button empezar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empezar = (Button) findViewById(R.id.bEmpezar);
        empezar.setOnClickListener(listenerStart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.IAcercaDE:
                Intent acerca = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(acerca);
                return true;
            case R.id.IAyuda:
                Intent ayuda = new Intent(MainActivity.this, Ayuda.class);
                startActivity(ayuda);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private View.OnClickListener listenerStart = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecundaryActivity.class);
                startActivity(intent);
        }
    };

}