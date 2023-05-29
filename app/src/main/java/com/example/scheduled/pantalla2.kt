package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import com.google.firebase.analytics.FirebaseAnalytics
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*

var numeroDeEvento=0
var tipoEvento = "Tipo No Identificado "
var fecha = "01/01/2023"
var detalle="sin detalle"
var ArrayDeEventosNew: MutableList<EventoNew> = mutableListOf()
var hora ="00"

class pantalla2 : AppCompatActivity() {

    val c = Calendar.getInstance()
    val month = c.get(Calendar.MONTH).toInt()
    val day = c.get(Calendar.DAY_OF_MONTH).toInt()
    val ordenActual = (month*100)+day
    lateinit var nuevoEvento :Button
    lateinit var listaDeEventos:TextView
    var otroEvento =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        nuevoEvento=findViewById(R.id.NuevoEvento)
        listaDeEventos=findViewById(R.id.ListaDeEventos)
       FirebaseDatabase.getInstance().reference // realtime database

        ArrayDeEventosNew.sortBy { it.orden }//ordena la ajenda
        ArrayDeEventosNew.forEach{
            if (ordenActual<= it.orden){
                listaDeEventos.text="${listaDeEventos.text} \n " +
                        "${it.detalle.toString()}"
            }else otroEvento =1
        }
        if(otroEvento==1){
            otroEvento=0
            listaDeEventos.text= "${listaDeEventos.text} \n " +
                    "*************************** \n"+
                    "2024 -> "
        }
        ArrayDeEventosNew.forEach{
            if (ordenActual> it.orden){
                listaDeEventos.text= "${listaDeEventos.text} \n " +
                        "${it.detalle.toString()} \n"


            }
        }

        nuevoEvento.setOnClickListener{

            val lanzar = Intent(this,Pantalla3::class.java) //home
            startActivity(lanzar)
            numeroDeEvento++


        }
    }

}