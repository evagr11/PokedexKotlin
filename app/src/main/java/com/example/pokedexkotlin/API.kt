package com.example.pokedexkotlin

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class API {

    // Busca un Pokémon por su número de la Pokédex en la PokéAPI.
    // Cuando llega la respuesta llama a onResult con el Pokémon, o con null si no existe
    // o si no hay conexión.
    fun buscarPokemon(ct: Context, numero: Int, onResult: (Pokemon?) -> Unit) {
        val queue: RequestQueue = Volley.newRequestQueue(ct)
        val url: String = "https://pokeapi.co/api/v2/pokemon/$numero"

        val pokemonRequest: StringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                try {
                    val jObj: JSONObject = JSONObject(response)
                    val name: String = jObj.getString("name")
                    val id: Int = jObj.getInt("id")
                    val image: String = jObj.getJSONObject("sprites")
                        .getJSONObject("other")
                        .getJSONObject("official-artwork")
                        .getString("front_default")

                    val tipos: ArrayList<String> = ArrayList()
                    val types = jObj.getJSONArray("types")
                    for (i in 0 until types.length()) {
                        tipos.add(types.getJSONObject(i).getJSONObject("type").getString("name"))
                    }

                    onResult(Pokemon(name = name, number = id, imagen = image, tipos = tipos))
                } catch (e: JSONException) {
                    e.printStackTrace()
                    onResult(null)
                }
            },
            { onResult(null) }
        )
        queue.add(pokemonRequest)
    }
}
