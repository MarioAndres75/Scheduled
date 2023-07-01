package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
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
    lateinit var cancelar:Button
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContentView(R.layout.activity_pantalla3)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        cumple=findViewById(R.id.Cumple)
        reunion=findViewById(R.id.Reunion)
        turno=findViewById(R.id.Turno)
        examen=findViewById(R.id.Examen)
        definir=findViewById(R.id.Definir)
        cancelar=findViewById(R.id.cancelar)

        cumple.setOnClickListener{
            tipoEvento="Cumpleaños de"
            detalle="Festejamos el cumple de: "
            borradoAutomatico=false
            irAFormulario()
        }
        reunion.setOnClickListener{
            tipoEvento="Tipo de reunion"
            detalle="Hay reunion de: "
            borradoAutomatico=true
            irAFormulario()
        }
        turno.setOnClickListener {
            tipoEvento="Especialidad del turno "
            detalle ="Turno con: "
            borradoAutomatico=true
            irAFormulario()
        }
        examen.setOnClickListener {
            tipoEvento="Materia de Examen"
            detalle="Hay Examen de: "
            borradoAutomatico=true
            irAFormulario()
        }
        definir.setOnClickListener {
            tipoEvento="Describa el Evento"
            detalle="Se programó un/una: "
            borradoAutomatico=true
            irAFormulario()
        }

       cancelar.setOnClickListener {
           irAPantalla2()
       }
    }

    private fun irAPantalla2() {
        val lanzar = Intent(this,pantalla2::class.java) //home
        startActivity(lanzar)
    }

    private fun irAFormulario() {
        val lanzar = Intent(this,formulario::class.java) //home
        startActivity(lanzar)
    }
}
