package com.example.rickandmortyv20.api

data class PersonajeResponse(
    val name: String,
    val image: String,
    val status: String,
    val gender: String,
    val location: Location,
    val species: String
)

data class Location(
    val name: String
)