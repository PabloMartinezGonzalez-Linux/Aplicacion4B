package com.example.aplicacion4b

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion4b.databinding.ActivityChistesBinding
import java.util.Locale
import kotlin.random.Random

class ChistesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech
    private var lastClickTime: Long = 0
    private val doubleClickInterval = 500 // Intervalo en milisegundos para considerar doble clic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureTextToSpeech()

        binding.btnEscuchar.setOnClickListener {
            handleButtonClick()
        }
    }

    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext) {
            if (it != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.getDefault()
            } else {
                error("Error en la configuración de Text-to-Speech")
            }
        }
    }

    private fun handleButtonClick() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < doubleClickInterval) {
            val randomChiste = Listachistes.chistes[Random.nextInt(Listachistes.chistes.size)]
            textToSpeech.speak(randomChiste, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            textToSpeech.speak(getString(R.string.double_click_message), TextToSpeech.QUEUE_FLUSH, null, null)
        }
        lastClickTime = currentTime
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
