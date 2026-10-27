package com.example.pokedexkotlin

import android.content.Context
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
        var pokemonTipos by remember{ mutableStateOf("")}
        var pokemonNumber by remember{ mutableStateOf("")}
        var pokemonImage by remember{ mutableStateOf("")}
        var buscando by remember{ mutableStateOf(false)}
        val ct : Context = LocalContext.current

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .safeDrawingPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = pokemonImage,
                contentDescription = "Imagen del Pokémon",
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_launcher_foreground),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
                    .clip(CircleShape)
            )
            Text(
                text = pokemonName,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = pokemonTipos,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            NumberTextFild(
                label = "Número de Pokédex (1-1025)",
                value = pokemonNumber,
                onValueChange = {pokemonNumber = it},
                modifier = Modifier.padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            Button(
                enabled = !buscando,
                onClick = {
                    val idBuscado : Int? = pokemonNumber.trim().toIntOrNull()
                    if (idBuscado == null || idBuscado < 1) {
                        pokemonName = "Escribe un número válido"
                        pokemonTipos = ""
                        pokemonImage = ""
                    } else {
                        buscando = true
                        pokemonName = "Buscando..."
                        API().buscarPokemon(ct, idBuscado) { pokemon ->
                            buscando = false
                            if (pokemon != null) {
                                pokemonName = "#${pokemon.number} " + pokemon.name.replaceFirstChar { it.uppercase() }
                                pokemonTipos = pokemon.tipos.joinToString(" · ") { it.replaceFirstChar { c -> c.uppercase() } }
                                pokemonImage = pokemon.imagen
                            } else {
                                pokemonName = "No encontrado"
                                pokemonTipos = ""
                                pokemonImage = ""
                            }
                        }
                    }
                }
            ) {
                Text(
                    text = if (buscando) "Buscando..." else "Buscar Pokémon"
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
