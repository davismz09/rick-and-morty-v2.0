package com.example.rickandmortyapi.rickmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyv20.R

class PersonajeAdapter(
    private val personajes: List<Map<String, String>>,
    private val listener: (Map<String, String>) -> Unit
) :
    RecyclerView.Adapter<PersonajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_personaje, parent, false)
        return PersonajeViewHolder(view, listener)
    }

    override fun getItemCount(): Int = personajes.size

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item: Map<String, String> = personajes[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }
}