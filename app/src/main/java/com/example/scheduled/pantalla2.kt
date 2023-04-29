package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

var numeroDeEvento=0
var tipoEvento = "Tipo No Identificado "
var fecha = "01/01/2023"
var detalle="sin detalle"
var hora="00"
var ListadoDeEventos="EVENTOS:"
var ArrayDeEventos :MutableList<String> = mutableListOf("")

class pantalla2 : AppCompatActivity() {
    lateinit var nuevoEvento :Button
    lateinit var listaDeEventos:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        nuevoEvento=findViewById(R.id.NuevoEvento)
        listaDeEventos=findViewById(R.id.ListaDeEventos)
        ArrayDeEventos.forEach {
            listaDeEventos.text=" ${listaDeEventos.text} \n "+it.toString()
        }
        nuevoEvento.setOnClickListener{

            val lanzar = Intent(this,Pantalla3::class.java) //home
            startActivity(lanzar)
            numeroDeEvento++


        }
    }
}