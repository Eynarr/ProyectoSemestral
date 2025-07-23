package com.example.proyectosemestral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    private Button btnJuego, btnSensor;
    private ImageView imagenCarita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        imagenCarita = findViewById(R.id.imagenCarita);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.girar);
        imagenCarita.startAnimation(animacion);

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
