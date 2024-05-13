package com.example.rickandmortyapi.rickmorty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyv20.databinding.ItemPersonajeBinding
import com.squareup.picasso.Picasso

class PersonajeViewHolder(view: View, private val listener: (Map<String, String>) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonajeBinding.bind(view)

    fun bind(personaje: Map<String, String>) {
        Picasso.get().load(personaje["image"]).into(binding.ivPersonaje)
        itemView.setOnClickListener {
            listener(personaje)
        }
    }
}
