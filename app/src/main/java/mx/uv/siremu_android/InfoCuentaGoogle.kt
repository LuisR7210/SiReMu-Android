package mx.uv.siremu_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_info_cuenta_google.*
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule



class InfoCuentaGoogle : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_cuenta_google)

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            nombre.text = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            correo.text  = acct.email
            val idCuenta = acct.id
            GlideApp.with(this).load(acct.photoUrl).into(fotoPerfil);
        }
        btMisAlbumes.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        botonCerrarSesion.setOnClickListener{
            mGoogleSignInClient.signOut().addOnCompleteListener(this, OnCompleteListener<Void?> {
                    startActivity(Intent(this, Login::class.java))
                })

        }
    }
}

 @GlideModule
 class AppGlideModule : AppGlideModule()
