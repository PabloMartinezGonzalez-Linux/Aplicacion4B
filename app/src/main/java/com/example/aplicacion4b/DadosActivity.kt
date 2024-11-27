package com.example.aplicacion4b

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion4b.databinding.ActivityDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DadosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDadosBinding
    private var sumaTotal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializarEventos()
    }

    private fun inicializarEventos() {
        binding.txtResultado.visibility = View.INVISIBLE
        binding.tiradaDados.setOnClickListener {
            binding.txtResultado.visibility = View.VISIBLE
            iniciarJuego()
        }
    }

    private fun iniciarJuego() {
        programarTiradas()
    }

    private fun programarTiradas() {
        val ejecutor = Executors.newSingleThreadScheduledExecutor()
        val retrasoMilisegundos = 1000

        for (iteracion in 1..5) {
            ejecutor.schedule(
                { tirarDados() },
                retrasoMilisegundos * iteracion.toLong(),
                TimeUnit.MILLISECONDS
            )
        }

        ejecutor.schedule(
            { mostrarResultado() },
            retrasoMilisegundos * 7.toLong(),
            TimeUnit.MILLISECONDS
        )

        ejecutor.shutdown()
    }

    private fun tirarDados() {
        val valoresDados = Array(3) { Random.nextInt(1, 6) }
        val imagenesDados: Array<ImageView> = arrayOf(
            binding.dado1,
            binding.dado2,
            binding.dado3
        )

        sumaTotal = valoresDados.sum()
        for (indice in valoresDados.indices) {
            actualizarImagenDado(imagenesDados[indice], valoresDados[indice])
        }
    }

    private fun actualizarImagenDado(imagen: ImageView, valorDado: Int) {
        val recursoDrawable = when (valorDado) {
            1 -> R.drawable.dado_1
            2 -> R.drawable.dado_2
            3 -> R.drawable.dado_3
            4 -> R.drawable.dado_4
            5 -> R.drawable.dado_5
            6 -> R.drawable.dado_6
            else -> throw IllegalArgumentException("Valor del dado inválido: $valorDado")
        }
        imagen.setImageResource(recursoDrawable)
    }

    private fun mostrarResultado() {
        binding.txtResultado.text = sumaTotal.toString()
    }
}
