package com.example.proyectosemestral;

import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView question;
    private Button btn1, btn2, btn3;
    private MediaPlayer correcto, incorrecto;

    // Datos (imagen, respuesta, letras distractoras)
    String[] palabras = {"manzana", "sol", "dado"};
    int[] imagenes = {R.drawable.manzana, R.drawable.sol, R.drawable.dado};
    char[] respuestas = {'M', 'S', 'D'};
    int index = 0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageObject);
        question = findViewById(R.id.textQuestion);
        btn1 = findViewById(R.id.btnOption1);
        btn2 = findViewById(R.id.btnOption2);
        btn3 = findViewById(R.id.btnOption3);

        correcto = MediaPlayer.create(this, R.raw.correct);
        incorrecto = MediaPlayer.create(this, R.raw.wrong);

        View.OnClickListener listener = view -> {
            Button b = (Button) view;
            char letra = b.getText().toString().charAt(0);
            if (letra == respuestas[index]) {
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_up));
                correcto.start();
                siguientePalabra();
            } else {
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                incorrecto.start();
                Toast.makeText(this, "No, intenta otra vez", Toast.LENGTH_SHORT).show();
            }
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);

        mostrarPalabra();
    }

    private void mostrarPalabra() {
        image.setImageResource(imagenes[index]);

        // Poner la letra correcta en un botón al azar
        char correcta = respuestas[index];
        char falsa1 = (char)(correcta + 1);
        char falsa2 = (char)(correcta + 2);

        Button[] botones = {btn1, btn2, btn3};
        int posCorrecta = random.nextInt(3);
        botones[posCorrecta].setText(String.valueOf(correcta));
        botones[(posCorrecta + 1) % 3].setText(String.valueOf(falsa1));
        botones[(posCorrecta + 2) % 3].setText(String.valueOf(falsa2));
    }

    private void siguientePalabra() {
        index++;

        if (index >= palabras.length) {
            // Juego terminado → ir a sensor
            Intent intent = new Intent(MainActivity.this, proximity.class);
            startActivity(intent);
            finish(); // Cierra el juego si ya no se va a usar
        } else {
            mostrarPalabra();
        }
    }
}