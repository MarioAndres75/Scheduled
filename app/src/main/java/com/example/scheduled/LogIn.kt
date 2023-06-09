package com.example.scheduled

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var email :EditText
lateinit var passw :EditText
lateinit var log : Button
lateinit var nuevoUsuario : Button
class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
         Firebase.auth
        email=findViewById(R.id.email)
        passw=findViewById(R.id.clave)
        log=findViewById(R.id.acceder)
        nuevoUsuario=findViewById(R.id.registrar)

        log.setOnClickListener {
                if (email.text.isNotEmpty() && passw.text.isNotEmpty()) {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email.text.toString(),passw.text.toString())
                        .addOnCompleteListener{
                            if (it.isSuccessful) {
                                acceso(it.result?.user?.email?:"",ProviderType.BASIC)
                            } else{
                             alerta()

                            }
                        }


                }

            }


        nuevoUsuario.setOnClickListener {
            if (email.text.isNotEmpty() && passw.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email.text.toString(),passw.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            acceso(it.result?.user?.email?:"",ProviderType.BASIC)
                        } else{
                            alerta()

                        }
                    }


            }
        }

    }



    fun acceso(email:String,provider:ProviderType){
        val lanzar = Intent(this, pantalla2::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        } //home
        startActivity(lanzar)
    }
    fun alerta(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog =builder.create()
        dialog.show()
    }
}