package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class formulario : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var nombreNuevo : EditText
    lateinit var diaNuevo :EditText
    lateinit var mesNuevo :EditText
    lateinit var agendarNuevo :Button
    lateinit var titulo:TextView
    lateinit var horaNuevo :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContentView(R.layout.activity_formulario)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        nombreNuevo=findViewById(R.id.nombreInt)
        diaNuevo=findViewById(R.id.diaInt)
        mesNuevo=findViewById(R.id.mesInt)
        agendarNuevo=findViewById(R.id.agendar)
        titulo=findViewById(R.id.titulo)
        horaNuevo=findViewById(R.id.horaInt)
        var valDia =0
        var valMes =0

        titulo.text= tipoEvento.toString()

        agendarNuevo.setOnClickListener {
            if (diaNuevo.text.toString().isNotEmpty() && mesNuevo.text.toString().isNotEmpty()) {
                valDia = diaNuevo.text.toString().toInt()
                valMes = mesNuevo.text.toString().toInt()
                ordenEvento=(valMes*100)+valDia
            }
            if (valDia in 1..31 && valMes in 1..12){
                var NuevoEvento=   "------------------------------------ \n" +
                        // " Tipo: $tipoEvento \n" +
                        "El dia: ${diaNuevo.text} / ${mesNuevo.text}  \n" +
                        "$detalle" + "${nombreNuevo.text} "
                if (horaNuevo.text.toString().isNotEmpty()) NuevoEvento="$NuevoEvento \n A las ${horaNuevo.text.toString()} HS "
               cantidadDeEventos++
             //firestore, escritura
              db.collection(usuario).document(cantidadDeEventos.toString()).set(
                  hashMapOf("detalle" to NuevoEvento,"orden" to ordenEvento.toString())
              )

                tipoEvento = "Tipo No Identificado "
                fecha = "01/01"
                detalle = "sin detalle"
                hora ="00"
                val eventoNew=EventoNew(NuevoEvento,ordenEvento)


                ArrayDeEventosNew.add(eventoNew)
                val lanzar = Intent(this,pantalla2::class.java) //home
                startActivity(lanzar)
            }else alerta()


        }
    }
    fun alerta(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No ha ingresado valores validos")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}
