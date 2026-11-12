package com.example.pokedexkotlin

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

/**
 * Hace las peticiones a la PokéAPI con Volley.
 */
class API {

    /**
     * Busca un Pokémon por su número de la Pokédex.
     *
     * Pide `pokeapi.co/api/v2/pokemon/{numero}` y convierte el JSON en un [Pokemon].
     * La respuesta llega de forma asíncrona por [onResult].
     *
     * @param ct contexto para crear la cola de Volley.
     * @param numero número de la Pokédex que se busca.
     * @param onResult recibe el [Pokemon], o `null` si no existe, no hay conexión
     * o el JSON no tiene el formato esperado.
     */
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
