package com.example.aplicacion4b

import android.provider.Settings
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.aplicacion4b.databinding.ActivityConfiguracionTelefonoBinding

class ConfiguracionTelefono : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracionTelefonoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracionTelefonoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bttLlamar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED){

                val telefono = binding.numeroTelefono.text.toString()

                if (telefono.isNotEmpty()){
                    hacerLlamada()
                } else {
                    Toast.makeText(this, "Por favor, ingresa un número de teléfono válido", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "No hay permisos para la lamada", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 100)

                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun hacerLlamada() {
        val telefono = binding.numeroTelefono.text.toString().replace(" ", "").trim()
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$telefono")
        startActivity(intent)
    }

}
