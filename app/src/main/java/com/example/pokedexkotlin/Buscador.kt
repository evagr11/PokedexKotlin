package com.example.pokedexkotlin

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexkotlin.ui.theme.PokedexKotlinTheme

class Buscador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexKotlinTheme {
                Surface(

                ){
                    PokedexApp()
                }
            }
        }
    }

    @Composable
    fun NumberTextFild(label : String, value: String, onValueChange : (String)-> Unit, modifier: Modifier = Modifier){
        TextField(
            label = {
                Text(
                    text = label
                )
            },
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
        )

    }

    @Composable
    fun PokedexApp(modifier: Modifier = Modifier) {

        var pokemonName by remember{ mutableStateOf("Nombre del Pokémon")}
        var pokemonNumber by remember{ mutableStateOf("XXXX")}
        var pokemonImage by remember{ mutableStateOf("")}

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .safeDrawingPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                //painter = ,
                contentDescription = "Imagen del Pokémon"
            )
            Text(
                text = pokemonName,
                style = MaterialTheme.typography.displaySmall
            )

            NumberTextFild(
                label = "Numero de Pokédex",
                value = pokemonNumber,
                onValueChange = {pokemonNumber = it},
                modifier = Modifier.padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    val id = pokemonNumber

                    val api = API()

                    api.obtainAllPokemon(this, Runnable){
                        val lista = api.getMyPokedex()

                        if (existe en lista){
                            pokemonName = pokemon.name
                            pokemonImage = pokemon.imagen
                        } else {
                            pokemonName = "No encontrado"
                            pokemonImage = ""
                        }
                    }
                }
            ) {
                Text(
                    text = "Buscar Pokémon"
                )
            }

        }
    }



    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PokedexKotlinTheme {
            PokedexApp()
        }
    }
}
