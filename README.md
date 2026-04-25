# FootballLeaguesApp ⚽
Aplicación Android desarrollada en Kotlin como práctica de 2º DAM.

## Descripción
La aplicación permite a los usuarios registrarse e iniciar sesión mediante Firebase Authentication. Una vez dentro, se muestra una lista de ligas de fútbol obtenidas desde una API externa. Al seleccionar una liga, se visualizan sus equipos en formato tarjeta con opción de marcarlos como favoritos.
También incluye una pantalla específica para consultar los equipos favoritos guardados durante la ejecución de la aplicación.

## Funcionalidades principales
- Registro de usuarios con Firebase
- Inicio de sesión con email y contraseña
- Navegación mediante Fragments
- Lista de ligas de fútbol
- Lista de equipos por liga
- Marcado de favoritos con estrellas
- Pantalla de favoritos
- Menú superior con navegación
- Uso de imágenes con Glide

## Tecnologías utilizadas
- Kotlin
- Android Studio
- Firebase Authentication
- Retrofit
- RecyclerView
- Glide
- Fragments
- Material Design básico

## Estructura del proyecto
app/
 ├── activities
 ├── fragments
 ├── adapters
 ├── data
 ├── models
 └── res/layout

## API utilizada
TheSportsDB:
- Obtener ligas
- Obtener equipos por liga

## Autor
Sara Vázquez

## Proyecto académico
Desarrollo realizado como práctica entregable del ciclo formativo de Desarrollo de Aplicaciones Multiplataforma (DAM).
