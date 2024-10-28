package com.example.aplicacion4b

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import java.util.logging.Handler


class CargaActivity : ComponentActivity() {

    private val TIEMPO_CARGA = 3000 // Tiempo de carga en milisegundos (3 segundos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga)

        // Usamos un Handler para retrasar la transición a MainActivity
        android.os.Handler().postDelayed({
            // Iniciar la MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Cerrar la pantalla de carga
            finish() // Para que no se pueda regresar a la pantalla de carga
        }, TIEMPO_CARGA.toLong())
    }

}
