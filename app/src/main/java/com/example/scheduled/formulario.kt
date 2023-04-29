package com.example.scheduled

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class formulario : AppCompatActivity() {
    lateinit var nombreNuevo : EditText
    lateinit var diaNuevo : EditText
    lateinit var mesNuevo : EditText
    lateinit var agendarNuevo : Button
    lateinit var titulo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        nombreNuevo=findViewById(R.id.nombreInt)
        diaNuevo=findViewById(R.id.diaInt)
        mesNuevo=findViewById(R.id.mesInt)
        agendarNuevo=findViewById(R.id.agendar)
        titulo=findViewById(R.id.titulo)
        var valDia =0
        var valMes =0
        titulo.text= tipoEvento.toString()


        agendarNuevo.setOnClickListener {
            valDia=diaNuevo.text.toString().toInt()
            valMes=mesNuevo.text.toString().toInt()
            //validar fecha

            val NuevoEvento=   "Numero: $numeroDeEvento \n" +
                    // " Tipo: $tipoEvento \n" +
                    "El dia: ${diaNuevo.text} / ${mesNuevo.text}  \n" +
                    "$detalle" + "${nombreNuevo.text} \n"
            tipoEvento = "Tipo No Identificado "
            fecha = "01/01"
            detalle = "sin detalle"
            ArrayDeEventos.add(NuevoEvento)
            val lanzar = Intent(this,pantalla2::class.java) //home
            startActivity(lanzar)
        }
    }

}