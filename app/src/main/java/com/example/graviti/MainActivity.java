package com.example.graviti;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Initialisations variables
    SensorManager sensorManager;
    Sensor Accelerometre;
    TextView tv_X;
    TextView tv_Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation sensorManager pour gérer les capteurs
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Affectation du capteur "ACCELEROMER" à la variable Accelerometre
        Accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Récupération des textView
        tv_X = (TextView) findViewById(R.id.tv_X);
        tv_Y = (TextView) findViewById(R.id.tv_Y);
    }

    /**
     * Méthode appelée lorsque les données capteurs sont MAJ
     * Affecte le résultat dans deux textView
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Récupère les valeurs de gavité x et y
        int x = (int) sensorEvent.values[0];
        int y = (int) sensorEvent.values[1];

        // Affecte les valeurs au textView
        tv_X.setText("X : " + x + "m/(s*s)");
        tv_Y.setText("Y : " + y + "m/(s*s)");
    }

    /**
     * Méthode non utilisé (implémentée de base)
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * Enregistre l'événement du capteur
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Enregistre un sensorEventListener pour mettre a jour les valeurs x et y
        sensorManager.registerListener(this, Accelerometre, SensorManager.SENSOR_DELAY_UI);
    }
}