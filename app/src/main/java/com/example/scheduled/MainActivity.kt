package com.example.scheduled

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {  lateinit var botonInicio: Button
//autenticacion
/*    private var signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    } */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        var actionCodeSettings = ActionCodeSettings.newBuilder()
        .setAndroidPackageName( // yourPackageName=
            "...", // installIfNotAvailable=
            true, // minimumVersion=
            null,
        )
        .setHandleCodeInApp(true) // This must be set to true
        .setUrl("https://google.com") // This URL needs to be whitelisted
        .build()

       // autenticacion
    /*   var providers = listOf(
        AuthUI.IdpConfig.EmailBuilder()
            .enableEmailLinkSignIn()
            .setActionCodeSettings(actionCodeSettings)
            .build(),
    )
    var signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()
    signInLauncher.launch(signInIntent)





        if (AuthUI.canHandleIntent(intent)) {
            var extras = intent.extras ?: return
            val link = extras.getString("email_link_sign_in")
            if (link != null) {
                var signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setEmailLink(link)
                    .setAvailableProviders(providers)
                    .build()
                signInLauncher.launch(signInIntent)
            }
        }
*/

        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        botonInicio=findViewById(R.id.BotonInicio)

        botonInicio.setOnClickListener{
            val lanzar = Intent(this,pantalla2::class.java) //home
            startActivity(lanzar)
        }


    }
    //autenticacion funcion
    /* private fun onSignInResult(result:FirebaseAuthUIAuthenticationResult) {
        var response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            var user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
*/


}