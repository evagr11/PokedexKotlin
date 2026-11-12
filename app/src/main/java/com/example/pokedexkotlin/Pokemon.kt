package com.example.pokedexkotlin

/**
 * Datos de un Pokémon que llegan de la PokéAPI.
 *
 * @property name nombre del Pokémon, en minúsculas como lo da la API.
 * @property number número de la Pokédex nacional.
 * @property imagen URL de la ilustración oficial.
 * @property tipos tipos del Pokémon en inglés, por ejemplo `grass` y `poison`.
 */
class Pokemon (
    var name : String,
    var number : Int,
    var imagen : String,
    var tipos : List<String> = emptyList()
)
