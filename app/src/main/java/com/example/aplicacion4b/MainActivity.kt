package com.example.aplicacion4b

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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

        binding.bttConfiguracion.setOnClickListener(){
            startActivity(Intent(this, DatosUsuarioActivity::class.java))
        }

        binding.telefono.setOnClickListener(){
            startActivity(Intent(this, ConfiguracionTelefono::class.java))
        }
    }
}