# Rick and Morty App v2.0

Esta es una aplicación para dispositivos Android que muestra información sobre los personajes de la serie de televisión "Rick and Morty". La aplicación utiliza la API pública de Rick and Morty para obtener datos sobre los personajes.

## Características principales

- Visualización de una lista de personajes con imágenes y detalles.
- Búsqueda de personajes por nombre, especie, estado, género y ubicación.
- Navegación entre páginas de resultados.
- Visualización detallada de información sobre un personaje seleccionado.

## Capturas de pantalla
Pantalla inicial mostrando los personajes:
![5](https://github.com/davismz09/rick-and-morty-v2.0/assets/108368356/0133adfe-1c58-4244-9a9c-2a263f0582d3)
Al dar click en cualquier personaje se despliega una ventana con más información acerca del personaje:
![4](https://github.com/davismz09/rick-and-morty-v2.0/assets/108368356/4ee8b0bb-656f-45fb-843d-1c7a95fff8dd)
Se puede realizar una búsqueda mediante el nombre de cualquier personaje:
![2](https://github.com/davismz09/rick-and-morty-v2.0/assets/108368356/68f79b68-941f-4ced-8726-4933bb6c908c)
También se puede realizar la búsqueda mediante la especie de los personajes:
![3](https://github.com/davismz09/rick-and-morty-v2.0/assets/108368356/48c58d8e-eeb2-405f-aa8d-06bfdfabfc55)
En caso de no haber coincidencias en la búsqueda, mostrará una pantalla en negro y a su vez un mensaje de error:
![1](https://github.com/davismz09/rick-and-morty-v2.0/assets/108368356/174da611-025e-4040-a210-261e3c0589cc)


## Instalación

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en Android Studio.
3. Ejecuta la aplicación en un emulador o dispositivo Android.

## Uso

- Al abrir la aplicación, verás una lista de personajes. Puedes desplazarte hacia abajo para ver más personajes.
- Utiliza la barra de búsqueda en la parte superior para buscar personajes por nombre, especie, estado, género o ubicación.
- Puedes hacer clic en un personaje para ver más detalles sobre él.

## Tecnologías utilizadas

- Kotlin
- Retrofit para hacer llamadas a la API
- Gson para el manejo de datos JSON
- Picasso para la carga de imágenes
- RecyclerView para la visualización de la lista de personajes

## Contribuir

Si quieres contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit de ellos (`git commit -am 'Agrega una nueva característica'`).
4. Haz push de tu rama (`git push origin feature/nueva-caracteristica`).
5. Abre un pull request.

## Créditos

Esta aplicación fue desarrollada por David Mite Zambrano como parte del proyecto final de la materia de Aplicaciones Móviles de la carrea de Desarrollo de Software.


---

## Ampliación de la información

### Actividades Utilizadas

- `MainActivity`: Esta actividad es la pantalla principal de la aplicación. Se encarga de mostrar una lista de personajes y permite al usuario realizar búsquedas y navegar entre páginas de resultados.
  
- `InfoPersonajeActivity`: Esta actividad muestra información detallada sobre un personaje seleccionado. Se inicia cuando el usuario hace clic en un personaje en la lista de personajes.

### Dependencia para consultas URL

La aplicación utiliza Retrofit para hacer consultas a la API de Rick and Morty. Retrofit es una biblioteca de cliente HTTP para Android y Java que simplifica el consumo de servicios web RESTful. Aquí tienes un ejemplo de cómo se utiliza en la aplicación:

```kotlin
val rf = Retrofit.Builder()
    .baseUrl("https://rickandmortyapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val apiService = rf.create(ApiServices::class.java)
```

### Función de la Interfaz ApiServices

La interfaz `ApiServices` define métodos para realizar solicitudes a la API de Rick and Morty. En la aplicación, se utiliza para obtener la lista de personajes. Aquí tienes un ejemplo de cómo se utiliza:

```kotlin
interface ApiServices {
    @GET
    suspend fun getPersonajes(@Url url: String): Response<PersonajesResponse>
}
```

### Data Class

Un data class en Kotlin es una clase que se utiliza principalmente para contener datos. En la aplicación, se utilizan data classes para representar objetos como PersonajeResponse y PersonajesResponse, que contienen información sobre los personajes obtenidos de la API. Por ejemplo:

```kotlin
data class PersonajeResponse(
    val name: String,
    val image: String,
    val status: String,
    val gender: String,
    val location: Location,
    val species: String
)
```

### Estructura de la Activity Principal (MainActivity)

La actividad principal de la aplicación, `MainActivity`, está estructurada de la siguiente manera:

- `onCreate`: Este método se llama cuando se crea la actividad. En este método, se inicializan las vistas y se configuran los listeners para los botones y la barra de búsqueda.
  
- `fetchPersonajes`: Esta función se utiliza para obtener la lista de personajes de la API utilizando Retrofit. Se ejecuta en un hilo de fondo para no bloquear la interfaz de usuario.
  
- `obtenerPersonajesPorPagina`: Esta función se llama para obtener la lista de personajes de la página actual.
  
- `obtenerPersonajesPorFiltro`: Esta función se llama cuando el usuario realiza una búsqueda utilizando la barra de búsqueda. Se utiliza para obtener la lista de personajes filtrada por el término de búsqueda especificado.
  
- `onQueryTextSubmit` y `onQueryTextChange`: Estos métodos se llaman cuando el usuario envía o cambia el texto en la barra de búsqueda. Se utilizan para realizar búsquedas de personajes.
  
- `ocultarTeclado`: Esta función se utiliza para ocultar el teclado virtual después de realizar una búsqueda.
  
- `initRecyclerView`: Este método se utiliza para configurar el RecyclerView que muestra la lista de personajes.
  
- `getRetrofit`: Esta función se utiliza para configurar Retrofit y obtener una instancia de la interfaz ApiServices para realizar llamadas a la API.
  
- `showError`: Esta función se utiliza para mostrar un mensaje de error en caso de que ocurra un problema al obtener los datos de la API.
