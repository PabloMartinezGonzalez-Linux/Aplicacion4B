package com.example.aplicacion4b

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion4b.databinding.ActivityDatosUsuarioBinding

class DatosUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatosUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDatosUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = arrayOf("Seleccione un fondo", "verdeSuave", "azulOscuro", "verdeOscuro")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.button.setOnClickListener {
            val name = binding.inputNombre.text.toString()
            val email = binding.inputEmail.text.toString()
            val selectedColor = binding.spinner.selectedItem.toString()
            val isCheckBoxChecked = binding.checkbox.isChecked

            if (name.isNotEmpty() && email.isNotEmpty() && selectedColor != "Seleccione una opción") {
                saveUserData(name, email, selectedColor, isCheckBoxChecked)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Por favor, ingresa todos los datos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserData(name: String, email: String, selectedColor: String, isCheckBoxChecked: Boolean) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("nombre_usuario", name)
        editor.putString("email", email)
        editor.putString("color_fondo", selectedColor)
        editor.putBoolean("guardado", true)
        editor.putBoolean("opcion_check", isCheckBoxChecked)
        editor.apply()

        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
    }
}
