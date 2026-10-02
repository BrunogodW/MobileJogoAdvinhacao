package com.example.jogoadivinhacao

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.random.Random

class JogoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOME = "nome"
        const val EXTRA_MAXIMO = "maximo"
    }

    private lateinit var tvBoasVindas: TextView
    private lateinit var etPalpite: EditText
    private lateinit var btnChutar: Button
    private lateinit var tvDica: TextView
    private lateinit var tvTentativas: TextView
    private lateinit var btnJogarNovo: Button

    private var nome = ""
    private var maximo = 50
    private var secreto = 0
    private var tentativas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        tvBoasVindas = findViewById(R.id.tvBoasVindas)
        etPalpite = findViewById(R.id.etPalpite)
        btnChutar = findViewById(R.id.btnChutar)
        tvDica = findViewById(R.id.tvDica)
        tvTentativas = findViewById(R.id.tvTentativas)
        btnJogarNovo = findViewById(R.id.btnJogarNovo)

        // Recebe os dados enviados pela MainActivity
        nome = intent.getStringExtra(EXTRA_NOME) ?: "Jogador"
        maximo = intent.getIntExtra(EXTRA_MAXIMO, 50)

        // Sorteia o número secreto entre 1 e maximo (inclusive)
        secreto = Random.nextInt(1, maximo + 1)

        tvBoasVindas.text = "$nome, pensei num número de 1 a $maximo!"
        tvTentativas.text = "Tentativas: 0"
        tvDica.visibility = View.INVISIBLE

        btnChutar.setOnClickListener { chutar() }
        btnJogarNovo.setOnClickListener { finish() } // volta para a primeira tela
    }

    private fun chutar() {
        val palpite = etPalpite.text.toString().toIntOrNull()

        if (palpite == null || palpite !in 1..maximo) {
            etPalpite.error = "Digite um número de 1 a $maximo"
            return
        }

        tentativas++
        tvTentativas.text = "Tentativas: $tentativas"
        etPalpite.text.clear()
        tvDica.visibility = View.VISIBLE

        if (palpite == secreto) {
            val palavra = if (tentativas == 1) "tentativa" else "tentativas"
            tvDica.text = "Acertou!\n$nome acertou em $tentativas $palavra!"
            etPalpite.isEnabled = false
            btnChutar.isEnabled = false
            btnJogarNovo.visibility = View.VISIBLE
            return
        }

        val diferenca = abs(palpite - secreto)
        val quente = diferenca <= maximo * 0.10
        val direcao = if (secreto > palpite) "MAIOR" else "MENOR"
        val temperatura = if (quente) "Quente!" else "Frio."

        tvDica.text = "$temperatura O número é $direcao."
    }
}
