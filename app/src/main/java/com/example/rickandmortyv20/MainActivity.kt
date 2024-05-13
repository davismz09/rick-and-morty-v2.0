package com.example.rickandmortyv20

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapi.rickmorty.PersonajeAdapter
import com.example.rickandmortyv20.api.ApiServices
import com.example.rickandmortyv20.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonajeAdapter
    private val listaPersonajes = mutableListOf<Map<String, String>>()
    private var pagina = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)


        initRecyclerView()
        obtenerPersonajesPorPagina()

        binding.svBarra.setOnQueryTextListener(this)

        binding.btnNext.setOnClickListener {
            pagina++
            obtenerPersonajesPorPagina()

        }
        binding.btnPrev.setOnClickListener {
            if (pagina > 1) {
                pagina--
                obtenerPersonajesPorPagina()
            }
        }
    }

    private fun fetchPersonajes(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().getPersonajes(url)
                val personajes = call.body()?.results ?: emptyList()
                runOnUiThread {
                    if (call.isSuccessful) {
                        listaPersonajes.clear()
                        personajes.forEach { personaje ->
                            val mapa = mapOf(
                                "name" to personaje.name,
                                "species" to personaje.species,
                                "image" to personaje.image,
                                "status" to personaje.status,
                                "gender" to personaje.gender,
                                "location" to personaje.location.name
                            )
                            listaPersonajes.add(mapa)
                        }
                        adapter.notifyDataSetChanged()
                    } else {
                        listaPersonajes.clear()
                        showError("No existen personajes con ese filtro")
                    }
                    ocultarTeclado()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError("Error al obtener personajes")
                }
            }
        }
    }

    private fun obtenerPersonajesPorPagina() = fetchPersonajes("character/?page=$pagina")

    private fun obtenerPersonajesPorFiltro(filtro: String, valor: String) =
        fetchPersonajes("character/?${filtro.lowercase()}=${valor.lowercase()}")


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            val filtroSeleccionado = binding.spinner.selectedItem.toString()
            obtenerPersonajesPorFiltro(filtroSeleccionado, query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            obtenerPersonajesPorPagina()
        }
        return true
    }

    private fun ocultarTeclado() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.main.windowToken, 0)
    }

    private fun initRecyclerView() {
        adapter = PersonajeAdapter(listaPersonajes) { personaje ->
            val intent = Intent(this, InfoPersonajeActivity::class.java)
            intent.putExtra("name", personaje["name"])
            intent.putExtra("species", personaje["species"])
            intent.putExtra("image", personaje["image"])
            intent.putExtra("status", personaje["status"])
            intent.putExtra("gender", personaje["gender"])
            intent.putExtra("location", personaje["location"])
            startActivity(intent)
        }
        binding.rvPersonajes.layoutManager = GridLayoutManager(this, 3)
        binding.rvPersonajes.adapter = adapter
    }

    private fun getRetrofit(): ApiServices {
        val rf = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        return rf.create(ApiServices::class.java)
    }

    private fun showError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT).show()
    }

}