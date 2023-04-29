package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {  lateinit var botonInicio: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        botonInicio=findViewById(R.id.BotonInicio)

        botonInicio.setOnClickListener{
            val lanzar = Intent(this,pantalla2::class.java) //home
            startActivity(lanzar)
        }


    }
}