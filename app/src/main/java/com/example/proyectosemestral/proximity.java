package com.example.proyectosemestral;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class proximity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximityListener;
    private RelativeLayout mainLayout;
    private TextView estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        mainLayout = findViewById(R.id.main_layout);
        estado = findViewById(R.id.txtEstado);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null) {
            estado.setText("Sensor de proximidad no disponible");
        } else {
            proximityListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[0] < proximitySensor.getMaximumRange()) {
                        mainLayout.setBackgroundColor(Color.RED);
                        estado.setText("¡Algo está cerca!");
                    } else {
                        mainLayout.setBackgroundColor(Color.GREEN);
                        estado.setText("WOOOOOOOOOOOOOOOW Nada cerca");
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {}
            };
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (proximitySensor != null)
            sensorManager.registerListener(proximityListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (proximitySensor != null)
            sensorManager.unregisterListener(proximityListener);
    }
}