package com.example.scheduled

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class Pantalla3 : AppCompatActivity() {
    lateinit var cumple : Button
    lateinit var reunion: Button
    lateinit var turno : Button
    lateinit var examen: Button
    lateinit var definir: Button
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContentView(R.layout.activity_pantalla3)
        cumple=findViewById(R.id.Cumple)
        reunion=findViewById(R.id.Reunion)
        turno=findViewById(R.id.Turno)
        examen=findViewById(R.id.Examen)
        definir=findViewById(R.id.Definir)

        cumple.setOnClickListener{
            tipoEvento="Cumpleaños de"
            detalle="Festejamos el cumple de: "
            irAFormulario()
        }
        reunion.setOnClickListener{
            tipoEvento="Tipo de reunion"
            detalle="Hay reunion de: "
            irAFormulario()
        }
        turno.setOnClickListener {
            tipoEvento="Especialidad del turno "
            detalle ="Turno con: "
            irAFormulario()
        }
        examen.setOnClickListener {
            tipoEvento="Materia de Examen"
            detalle="Hay Examen de: "
            irAFormulario()
        }
        definir.setOnClickListener {
            tipoEvento="Describa el Evento"
            detalle="Se programó un/una: "
            irAFormulario()
        }

    }

    private fun irAFormulario() {
        val lanzar = Intent(this,formulario::class.java) //home
        startActivity(lanzar)
    }
}