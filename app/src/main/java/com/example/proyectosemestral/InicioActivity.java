package com.example.proyectosemestral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    private Button btnJuego, btnSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnJuego = findViewById(R.id.btnIrJuego);
        btnSensor = findViewById(R.id.btnIrSensor);

        btnJuego.setOnClickListener(v -> {
            Intent juegoIntent = new Intent(InicioActivity.this, MainActivity.class);
            startActivity(juegoIntent);
        });

        btnSensor.setOnClickListener(v -> {
            Intent sensorIntent = new Intent(InicioActivity.this, proximity.class);
            startActivity(sensorIntent);
        });
    }
}
