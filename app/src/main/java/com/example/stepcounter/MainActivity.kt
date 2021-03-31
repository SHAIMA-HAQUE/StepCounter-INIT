package com.example.stepcounter

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.hardware.SensorManager as SensorManager.SENSOR_DELAY_UI

class MainActivity : AppCompatActivity(),SensorEventListener {
    var running = false
    var sensorManager: SensorManager.SENSOR_DELAY_UI?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager.SENSOR_DELAY_UI


    }

    override fun onResume() {
        super.onResume()
        running=true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepsSensor == null) {
            Toast.makeText(this, "No Step Counter Sensor!", Toast.LENGTH_SHORT.show())
        } else {
             sensorManager?.registerListener(this,stepsSensor,SensorManager.SENSOR_DELAY_UI)
        }


    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            //update the UI
            val steps: TextView = findViewById(R.id.stepsValue)
            if(event!=null)
            {
                steps.text = "" +event.values[0]
            }
        }
    }




}