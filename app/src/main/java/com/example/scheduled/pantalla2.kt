package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import java.util.*

var ordenEvento=0
var numeroDeEvento=1
var tipoEvento = "Tipo No Identificado "
var borradoAutomatico = false
var fecha = "01/01/2023"
var detalle="sin detalle"
var ArrayDeEventosNew: MutableList<EventoNew> = mutableListOf()
var hora ="00"
var contador =0
var usuario = email.text.toString()
var cantidadDeEventos=0
enum class ProviderType{
    BASIC
}


var db = FirebaseFirestore.getInstance() //firestore
class pantalla2 : AppCompatActivity() {

    val c = Calendar.getInstance()
    val month = c.get(Calendar.MONTH).toInt()
    val day = c.get(Calendar.DAY_OF_MONTH).toInt()
    val ordenActual = (month*100)+day
    lateinit var nuevoEvento :Button
    lateinit var listaDeEventos:TextView
    lateinit var planificacion:Button
    lateinit var enero :Button
    lateinit var febrero :Button
    lateinit var marzo :Button
    lateinit var abril :Button
    lateinit var mayo :Button
    lateinit var junio :Button
    lateinit var julio :Button
    lateinit var agosto :Button
    lateinit var septiembre :Button
    lateinit var octubre :Button
    lateinit var noviembre :Button
    lateinit var diciembre :Button
    lateinit var todaLaAgenda :Button

    var otroEvento =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        nuevoEvento=findViewById(R.id.NuevoEvento)
        listaDeEventos=findViewById(R.id.ListaDeEventos)
        planificacion=findViewById(R.id.planificacion)
        enero=findViewById(R.id.enero)
        febrero=findViewById(R.id.febrero)
        marzo=findViewById(R.id.marzo)
        abril=findViewById(R.id.abril)
        mayo=findViewById(R.id.mayo)
        junio=findViewById(R.id.junio)
        julio=findViewById(R.id.julio)
        agosto=findViewById(R.id.agosto)
        septiembre=findViewById(R.id.septiembre)
        octubre=findViewById(R.id.octubre)
        noviembre=findViewById(R.id.noviembre)
        diciembre=findViewById(R.id.diciembre)
        todaLaAgenda=findViewById(R.id.todaLaAgenda)



        val dateMes = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("MM"))
        var mes = dateMes.toString().toInt()
        val dateDia = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd"))
        var dia = dateDia.toString().toInt()
        diaActual = (mes*100)+dia
        leerFirestore(contador)
        contador = 1
       // ordenarImprimir()
        listaDeEventos.text="BIENVENIDO \n" +
                            " \n" +
                            "SELECCIONE \n" +
                             "\n" +
                            "(NUMERO DE MES) \n " +
                             "\n" +
                             "O (VER TODO)"


        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        //val provider :String? = bundle?.getString("provider")

        nuevoEvento.setOnClickListener{

            val lanzar = Intent(this,Pantalla3::class.java) //home
            startActivity(lanzar)
            numeroDeEvento++
       }
        todaLaAgenda.setOnClickListener{

          ordenarImprimir()
        }
        enero.setOnClickListener{
            val mesPedido=1
            val mesNombre="***ENERO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        febrero.setOnClickListener{
            val mesPedido=2
            val mesNombre="***FEBRERO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        marzo.setOnClickListener{
            val mesPedido=3
            val mesNombre="***MARZO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        abril.setOnClickListener{
            val mesPedido=4
            val mesNombre="***ABRIL***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        mayo.setOnClickListener{
            val mesPedido=5
            val mesNombre="***MAYO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        junio.setOnClickListener{
            val mesPedido=6
            val mesNombre="***JUNIO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        julio.setOnClickListener{
            val mesPedido=7
            val mesNombre="***JULIO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        agosto.setOnClickListener{
            val mesPedido=8
            val mesNombre="***AGOSTO***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        septiembre.setOnClickListener{
            val mesPedido=9
            val mesNombre="**SEPTIEMBRE**"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        octubre.setOnClickListener{
            val mesPedido=10
            val mesNombre="***OCTUBRE***"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        noviembre.setOnClickListener{
            val mesPedido=11
            val mesNombre="**NOVIEMBRE**"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
        diciembre.setOnClickListener{
            val mesPedido=12
            val mesNombre="**DICIEMBRE**"
            ordenarImprimirMes(mesPedido,mesNombre)
        }
    }

    private fun ordenarImprimirMes(mesPedido: Int, mesNombre: String) {
        listaDeEventos.text=mesNombre
        val ordenMin = mesPedido*100
        val ordenMax = (mesPedido*100) +31
        ArrayDeEventosNew.sortBy { it.orden }//ordena la ajenda
        ArrayDeEventosNew.forEach {

                if (ordenActual <= it.orden) {
                if (it.orden in (ordenMin + 1) until ordenMax)    listaDeEventos.text = "${listaDeEventos.text} \n " +
                            "${it.detalle.toString()}"
                } else otroEvento = 1
            }

        if(otroEvento==1){
            otroEvento=0
            listaDeEventos.text= "${listaDeEventos.text} \n "

        }
        ArrayDeEventosNew.forEach{
            if (ordenActual> it.orden){

                if (it.orden in (ordenMin + 1) until ordenMax)     listaDeEventos.text= "${listaDeEventos.text} \n " +
                        "${it.detalle.toString()} \n"


            }
        }

        Firebase.messaging.token.addOnCompleteListener {
            if (it.isSuccessful) {
                // En este momento conocemos el valor del token
                Log.d("Notificaciones", it.result!!)
            }
        }


    }

    fun ordenarImprimir(){
    listaDeEventos.text="*AGENDA COMPLETA*"
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

fun leerFirestore(contador: Int) {
    if (contador ==0) {

        for (i in 1..365) {

            val docRef = db.collection(email.text.toString()).document(i.toString())
                .get()
                .addOnSuccessListener {
                    if (it.get("detalle") != null) {

                       var NuevoEvento = (it.get("detalle") as String?).toString()
                     var orden =  (it.get("orden") as String?).toString().toInt()
                       borradoAutomatico= (it.get("Tipo") as Boolean)
                        if (borradoAutomatico && orden < diaActual) { //para borrar automaticamente
                        db.collection(email.text.toString()).document(i.toString()).delete()
                        }  else{
                       val eventoNew = EventoNew(NuevoEvento,orden)
                       cantidadDeEventos++
                       ArrayDeEventosNew.add(eventoNew)

                    }
                    }
                }

        }

    }
}
}
