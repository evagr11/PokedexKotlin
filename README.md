<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A7F52FF%2C50%3AC711E1%2C100%3AE44857&height=200&section=header&text=Pok%C3%A9dex+Kotlin&fontSize=60&fontColor=fff&fontAlignY=38&desc=Buscador+de+Pok%C3%A9mon+con+Jetpack+Compose&descAlignY=62&descSize=18" width="100%"/>
</div>

<div align="center">
  <a href="https://github.com/evagr11/PokedexKotlin" target="_blank"><img src="https://img.shields.io/badge/Ver_Código-7F52FF?style=for-the-badge&logo=github&logoColor=white" alt="Ver Código"></a>
  <a href="https://github.com/evagr11/Pokedex" target="_blank"><img src="https://img.shields.io/badge/Versión_en_Java-E44857?style=for-the-badge&logo=openjdk&logoColor=white" alt="Versión en Java"></a>
</div>

<div align="center">
  <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com/?font=JetBrains+Mono&size=18&duration=3500&pause=800&color=7F52FF&center=true&vCenter=true&width=600&lines=Kotlin+%2B+Jetpack+Compose%3BConsumo+de+la+Pok%C3%A9API+con+Volley%3BIm%C3%A1genes+desde+internet+con+Coil%3BEstado+con+remember+y+mutableStateOf" alt="Typing SVG" /></a>
</div>

# 🚀 Sobre el Proyecto

**Pokédex Kotlin** es un buscador de Pokémon para Android hecho con **Kotlin y Jetpack Compose**. Escribes el número de la Pokédex, pulsas *Buscar* y la app consulta la [PokéAPI](https://pokeapi.co/) para mostrar su ilustración oficial, su nombre y sus tipos.

Es la versión en Kotlin y Compose de mi [Pokédex en Java](https://github.com/evagr11/Pokedex), hecha con vistas XML y `RecyclerView`. Tenerlas juntas permite comparar cómo se resuelve lo mismo con interfaz declarativa y con el sistema clásico de vistas.

✨ **Características principales:**

*   🔎 **Búsqueda por número:** Cualquier Pokémon de la 1 a la 1025, con una sola petición a `pokeapi.co/api/v2/pokemon/{número}`.
*   🖼️ **Ilustración oficial:** La imagen se descarga y se guarda en caché con **Coil** (`AsyncImage`), con imagen de reserva mientras carga o si falla.
*   🏷️ **Tipos del Pokémon:** Se muestran debajo del nombre (por ejemplo, *Grass · Poison*).
*   ⏳ **Estados de la búsqueda:** El botón se desactiva y muestra "Buscando..." mientras llega la respuesta, y avisa si el número no es válido o el Pokémon no existe.
*   🔢 **Teclado numérico:** El campo de texto abre directamente el teclado de números.

# 🧠 Contexto del Proyecto

Proyecto del ciclo de **Desarrollo de Aplicaciones Multiplataforma (DAM)** para practicar **Jetpack Compose**: funciones `@Composable`, estado con `remember` y `mutableStateOf`, componentes de Material 3 y llamadas a una API REST desde una interfaz declarativa.

# 🛠️ Stack Tecnológico

<div align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/PokéAPI-EF5350?style=for-the-badge" alt="PokéAPI">
  <img src="https://img.shields.io/badge/Status-Estable-C711E1?style=for-the-badge" alt="Status Estable">
</div>

<br>

<div align="center">

| Componente | Tecnología |
| :--------- | :--------- |
| **Lenguaje** | `Kotlin` |
| **Interfaz** | `Jetpack Compose` con `Material 3` |
| **Red** | `Volley` |
| **Imágenes** | `Coil` (`AsyncImage`) |
| **API externa** | `PokéAPI v2` |
| **Plataforma** | `Android` (minSdk 24 · targetSdk 36) |

</div>

# 📂 Estructura del Proyecto

```bash
PokedexKotlin/
└── app/src/main/
    ├── java/com/example/pokedexkotlin/
    │   ├── Buscador.kt      # Activity y pantalla en Compose: imagen, nombre, tipos, campo y botón
    │   ├── API.kt           # Petición a la PokéAPI con Volley
    │   ├── Pokemon.kt       # Nombre, número, imagen y tipos
    │   └── ui/theme/        # Colores, tipografía y tema de Compose
    ├── res/                 # Iconos, textos y temas
    └── AndroidManifest.xml  # Permiso de internet
```

# 🚀 Cómo Utilizar el Proyecto

Para ejecutarlo necesitas **Android Studio** y conexión a internet.

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/evagr11/PokedexKotlin.git
    ```
2.  **Abre el proyecto en Android Studio:** `File` > `Open` y selecciona la carpeta `PokedexKotlin`.
3.  **Sincroniza Gradle** y ejecuta la app en un emulador o en un móvil con `Run 'app'`.
4.  Escribe un número (por ejemplo `25`) y pulsa **Buscar Pokémon**.

# 🎯 Mejoras futuras

- 🔤 Buscar también por nombre.
- 🎨 Cambiar el color de fondo según el tipo principal, como en la versión en Java.
- 🧱 Pasar la lógica a un `ViewModel` con `StateFlow` y las peticiones a **Retrofit** con corrutinas.
- 📜 Añadir una lista con todos los Pokémon usando `LazyVerticalGrid`.

<br>

<div align="center">
  <p>Creado por <strong>Eva Gallardo</strong></p>
  <a href="https://github.com/evagr11" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  </a>
  <a href="https://www.linkedin.com/in/eva-gallardo-romero-830362349/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</div>

<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A7F52FF%2C50%3AC711E1%2C100%3AE44857&height=120&section=footer" width="100%"/>
</div>
