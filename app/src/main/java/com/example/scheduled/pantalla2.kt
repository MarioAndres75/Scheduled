package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import android.nfc.Tag
import com.google.firebase.analytics.FirebaseAnalytics
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*

import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.snapshots
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
var ordenEvento=0
var numeroDeEvento=1
var tipoEvento = "Tipo No Identificado "
var fecha = "01/01/2023"
var detalle="sin detalle"
var ArrayDeEventosNew: MutableList<EventoNew> = mutableListOf()
var hora ="00"
var contador =0
var cantidadDeEventos=0


var db = FirebaseFirestore.getInstance() //firestore
class pantalla2 : AppCompatActivity() {

    val c = Calendar.getInstance()
    val month = c.get(Calendar.MONTH).toInt()
    val day = c.get(Calendar.DAY_OF_MONTH).toInt()
    val ordenActual = (month*100)+day
    lateinit var nuevoEvento :Button
    lateinit var listaDeEventos:TextView
    lateinit var planificacion:Button
    var otroEvento =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        nuevoEvento=findViewById(R.id.NuevoEvento)
        listaDeEventos=findViewById(R.id.ListaDeEventos)
        planificacion=findViewById(R.id.planificacion)
       leerFirestore()
       ordenarImprimir()

        nuevoEvento.setOnClickListener{

            val lanzar = Intent(this,Pantalla3::class.java) //home
            startActivity(lanzar)
            numeroDeEvento++
       }
       planificacion.setOnClickListener {
           leerFirestore()
           ordenarImprimir()
       }
    }
fun ordenarImprimir(){
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
}
fun leerFirestore(){
    if (contador ==0) {
        contador=1
        for (i in 1..365) {

            val docRef = db.collection("Eventos").document(i.toString())
                .get()
                .addOnSuccessListener {
                    if (it.get("detalle") != null) {

                       var NuevoEvento = (it.get("detalle") as String?).toString()
                     var orden =  (it.get("orden") as String?).toString()
                       val eventoNew = EventoNew(NuevoEvento,orden.toInt())
                       cantidadDeEventos++
                       ArrayDeEventosNew.add(eventoNew)
                    }

                }
        }

    }
}
}
