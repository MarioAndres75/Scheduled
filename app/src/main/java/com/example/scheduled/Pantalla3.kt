package com.example.scheduled

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Pantalla3 : AppCompatActivity() {
    lateinit var cumple : Button
    lateinit var reunion: Button
    lateinit var turno : Button
    lateinit var examen: Button
    lateinit var definir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla3)
        cumple=findViewById(R.id.Cumple)
        reunion=findViewById(R.id.Reunion)
        turno=findViewById(R.id.Turno)
        examen=findViewById(R.id.Examen)
        definir=findViewById(R.id.Definir)

        cumple.setOnClickListener{
            tipoEvento="Cumpleaños de"
            detalle="Festejamos el cumple de: "
            val lanzar = Intent(this,formulario::class.java) //home
            startActivity(lanzar)
        }
        reunion.setOnClickListener{
            tipoEvento="Tipo de reunion"
            detalle="Hay reunion de: "
            val lanzar = Intent(this,formulario::class.java) //home
            startActivity(lanzar)
        }

    }
}