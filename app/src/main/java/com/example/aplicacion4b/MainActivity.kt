package com.example.aplicacion4b

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion4b.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE)
        binding.nombreSaludo.text = sharedPreferences.getString("nombre_usuario", "Usuario")

        binding.logoMain.setOnClickListener{
            startActivity(Intent(this, DatosUsuarioActivity::class.java))
        }

        binding.cardTelefono.setOnClickListener {
            startActivity(Intent(this, ConfiguracionTelefono::class.java))
        }

        binding.cardAlarma.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }

        binding.cardCloud.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aemet.es/es/eltiempo/prediccion/municipios/jaen-id23050"))
            startActivity(intent)
        }

        binding.cardChistes.setOnClickListener {
            startActivity(Intent(this, ChistesActivity::class.java))
        }

        binding.cardDados.setOnClickListener{
            startActivity(Intent(this, DadosActivity::class.java))
        }

        binding.cardMensaje.setOnClickListener(){
            val intent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_APP_MESSAGING)
            }
            startActivity(intent)
        }

    }
}
