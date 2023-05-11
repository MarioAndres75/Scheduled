package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import com.google.firebase.analytics.FirebaseAnalytics
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

var tipoEvento = "Tipo No Identificado "
var fecha = "01/01/2023"
var detalle="sin detalle"
var ArrayDeEventosNew: MutableList<EventoNew> = mutableListOf()
var hora ="00"
class pantalla2 : AppCompatActivity() {
    lateinit var nuevoEvento :Button
    lateinit var listaDeEventos:TextView
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContentView(R.layout.activity_pantalla2)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        nuevoEvento=findViewById(R.id.NuevoEvento)
        listaDeEventos=findViewById(R.id.ListaDeEventos)

        ArrayDeEventosNew.sortBy { it.orden }
        ArrayDeEventosNew.forEach{
            listaDeEventos.text="${listaDeEventos.text} \n " +
                    "${it.detalle.toString()}"
        }

        nuevoEvento.setOnClickListener{

            val lanzar = Intent(this,Pantalla3::class.java) //home
            startActivity(lanzar)
            //numeroDeEvento++


        }
    }

}