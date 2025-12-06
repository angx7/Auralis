# 🎹 Auralis Frontend

Frontend de la aplicación **Auralis** para la práctica pianística. Permite la interacción con la API de Auralis, brindando al usuario una interfaz intuitiva para registrar sesiones, visualizar canciones y recibir recomendaciones personalizadas para mejorar su técnica pianística.

## ✨ Características

- 🚀 **Interfaz interactiva** para selección de canciones, iniciar y finalizar sesiones de práctica.
- 📝 **Métricas de desempeño**: ver puntajes y estadísticas de cada sesión.
- 🤖 **Recomendaciones de IA**: obtener consejos breves y prácticos generados por OpenAI para mejorar en la práctica.
- 🎵 **Catálogo de canciones**: consultar canciones por nivel de dificultad (fácil, intermedio, difícil).
- 🧑‍🎤 **Perfil personal**: actualización de perfil y visualización de sesiones pasadas.

## 🧩 Requisitos

- **Android Studio**: IDE recomendado para desarrollo con Jetpack Compose.
- **SDK de Android 12+**: Requiere una versión mínima del SDK de Android.
- **Dependencias**:
  - Jetpack Compose
  - ViewModel y LiveData
  - Retrofit para consumir la API

## 🛠️ Instalación

1. Clona este repositorio:

```bash
git clone https://github.com/angx7/Auralis
```

2. Abre el proyecto en Android Studio.

3. Sincroniza y construye el proyecto.

## 🧑‍💻 Arquitectura

### MVVM (Modelo-Vista-ViewModel)

- **Model**: _Representa los datos que obtienes de la API._
- **View**: _La UI de la app, usando composables._
- **ViewModel**: _Maneja la lógica y estado de la UI, proporcionando datos a la vista y recibiendo interacciones del usuario._

## 🌐 Rutas del Frontend

_El frontend interactúa con las siguientes rutas de la API:_

### 🔐 Autenticación (/auth)

- `POST /auth/login`: Autentica al usuario con su correo y contraseña.
- `POST /auth/register`: Registra un nuevo usuario.
- `GET /auth/me`: Obtiene el perfil del usuario.

### 🎵 Canciones (/songs)

- `GET /songs`: Obtiene todas las canciones disponibles.
- `GET /songs/{id}`: Obtiene una canción específica por ID.

### 🕒 Sesiones (/sessions)

- `POST /sessions`: Inicia una nueva sesión de práctica.
- `PUT /sessions/{id}`: Termina la sesión de práctica.
- `GET /sessions`: Obtiene todas las sesiones del usuario.
- `GET /sessions/{id}`: Obtiene una sesión específica.
- `GET /sessions/month`: Obtiene el resumen de sesiones del mes.

### 🤖 IA (/ai)

- `POST /ai/protip`: Obtiene un tip generado por IA para mejorar en la práctica.
- `GET /ai/report`: Obtiene un reporte del progreso de las sesiones de práctica.

## 🎨 Diseño

_La aplicación usa un diseño limpio y moderno, siguiendo las guías de Material Design con Jetpack Compose para una experiencia fluida y visualmente agradable._

## 🧭 Notas rápidas

- Token de Autenticación: Se guarda en SharedPreferences y se pasa en los headers de cada solicitud a la API.
- Manejo de Errores: Los errores de API se manejan mediante try-catch en los ViewModels y se muestran de forma amigable al usuario.
- Sincronización en tiempo real: Las sesiones y canciones se actualizan en tiempo real desde la API.

## 👤 Autores

Desarrollado por

- Angel Alejandro Becerra Rojas [@angx7](https://github.com/angx7)
- Christian Axel Moreno Flores [@Kuripipeer](https://github.com/kuripipeer)
- Abraham Rodríguez Contreras [@bardodepacotilla2912](https://github.com/bardodepacotilla2912)
- Sergio Ernesto Rosas Ducoing [@SergioErnestoRosasDucoing](https://github.com/SergioErnestoRosasDucoing)
- Héctor Javier Adrián Zaragoza [@TachyonSlash](https://github.com/TachyonSlash)
