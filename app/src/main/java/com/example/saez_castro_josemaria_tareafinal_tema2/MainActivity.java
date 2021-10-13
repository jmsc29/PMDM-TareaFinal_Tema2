package com.example.saez_castro_josemaria_tareafinal_tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empezar = (Button) findViewById(R.id.bEmpezar);
        empezar.setOnClickListener(listenerStart);

    }

    private View.OnClickListener listenerStart = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SecundaryActivity.class);
            startActivity(intent);
        }
    };

}