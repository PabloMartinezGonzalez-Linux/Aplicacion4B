package com.example.aplicacion4b

import android.R
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion4b.databinding.ActivityDatosUsuarioBinding


class DatosUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatosUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDatosUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = arrayOf("Seleccione una opción", "-18", "+18", "+64")

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.button.setOnClickListener {
            val name = binding.inputNombre.text.toString()
            val email = binding.inputEmail.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                saveUserData(name, email)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Por favor, ingresa todos los datos.", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun saveUserData(name: String, email: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("nombre_usuario", name)
        editor.putString("email", email)
        editor.putBoolean("guardado", true)
        editor.apply()

        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
    }
}
