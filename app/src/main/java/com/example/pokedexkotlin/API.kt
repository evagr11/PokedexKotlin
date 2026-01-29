package com.example.pokedexkotlin

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class API {
    val myPokedex: kotlin.collections.ArrayList<Pokemon> = ArrayList()
    val fullURLList: Array<String?> = arrayOfNulls(50)


    fun obtainAllPokemon(ct: Context, onComplete: () -> Unit){
        val queue : RequestQueue = Volley.newRequestQueue(ct)
        val url : String = "https://pokeapi.co/api/v2/pokemon?limit=50&offset=0"

        var stringRequest : StringRequest = StringRequest(
            url,
            { response ->
                try {
                    val jObj: JSONObject = JSONObject(response)
                    val results : JSONArray = jObj.getJSONArray("results")
                    for(i in 0 until results.length()){
                        fullURLList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("url");
                    }

                    gatherAllPokemonInfo(ct){
                        onComplete()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            },
            { }



        )
        queue.add(stringRequest)
    }

    fun gatherAllPokemonInfo (ct : Context, onComplete : Runnable){
        myPokedex.clear()
        val queue : RequestQueue = Volley.newRequestQueue(ct)

        var completed : Int = 0
        val total: Int = fullURLList.size

        for(url in fullURLList){
            val pokemonRequest : StringRequest = StringRequest(
                url, { response ->
                    val jObj: JSONObject = JSONObject(response)

                    val name: String = jObj.getString("name")
                    val id: Int = jObj.getInt("id")
                    val image: String = jObj.getJSONObject("sprites")
                        .getJSONObject("other")
                        .getJSONObject("official-artwork")
                        .getString("front_default")

                    val pokemon: Pokemon = Pokemon(
                        name = name,
                        number = id,
                        imagen = image
                    )

                    myPokedex.add(pokemon)
                },
                {}

            )

            queue.add(pokemonRequest)
        }
    }


    fun getArrayMyPokedex(): ArrayList<Pokemon> {
        return myPokedex
    }


}

