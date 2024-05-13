package com.example.rickandmortyv20

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class InfoPersonajeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_personaje)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = intent.getStringExtra("name")
        val species = intent.getStringExtra("species")
        val image = intent.getStringExtra("image")
        val status = intent.getStringExtra("status")
        val gender = intent.getStringExtra("gender")
        val location = intent.getStringExtra("location")
        // Encontrar vistas en el diseño inflado
        val ivPersonaje = findViewById<ImageView>(R.id.ivPersonaje)
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvEspecie = findViewById<TextView>(R.id.tvEspecie)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val tvGenero = findViewById<TextView>(R.id.tvGenero)
        val tvUbicacion = findViewById<TextView>(R.id.tvUbicacion)

        // Establecer los datos del personaje en las vistas
        name?.let { tvNombre.text = it }
        species?.let { tvEspecie.text = it }
        image?.let { Picasso.get().load(it).into(ivPersonaje) }
        status?.let { tvStatus.text = it }
        gender?.let { tvGenero.text = it }
        location?.let { tvUbicacion.text = it }

    }
}