package com.example.aplicacion4b

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat


class CargaActivity : ComponentActivity() {

    private val TIEMPO_CARGA = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga)
        window.statusBarColor = ContextCompat.getColor(this, R.color.verde)

        val sharedPreferences = getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE)
        val isUserDataSaved = sharedPreferences.getBoolean("guardado", false)

        val nextActivity = if (isUserDataSaved) {
            MainActivity::class.java
        } else {
            DatosUsuarioActivity::class.java
        }

        android.os.Handler().postDelayed({
            val intent = Intent(this,nextActivity)
            startActivity(intent)
            finish()
        }, TIEMPO_CARGA.toLong())
    }
}
