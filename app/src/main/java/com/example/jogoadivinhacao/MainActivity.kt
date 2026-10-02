package com.example.jogoadivinhacao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var rgLimite: RadioGroup
    private lateinit var btnJogar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNome = findViewById(R.id.etNome)
        rgLimite = findViewById(R.id.rgLimite)
        btnJogar = findViewById(R.id.btnJogar)

        btnJogar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            if (nome.isEmpty()) {
                etNome.error = "Digite o nome do jogador"
                return@setOnClickListener
            }

            val maximo = when (rgLimite.checkedRadioButtonId) {
                R.id.rb10 -> 10
                R.id.rb100 -> 100
                else -> 50
            }

            val intent = Intent(this, JogoActivity::class.java).apply {
                putExtra(JogoActivity.EXTRA_NOME, nome)
                putExtra(JogoActivity.EXTRA_MAXIMO, maximo)
            }
            startActivity(intent)
        }
    }
}
