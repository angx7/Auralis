package com.example.cameratest.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryColorLight   = Color(0xFFE71F7D) // Fucsia principal (botones)
val SecondaryColorLight = Color(0xFF8E24AA) // Morado de apoyo
val BackgroundColorLight = Color(0xFFF5F1F7) // Fondo general gris-lila muy claro
val SurfaceColorLight    = Color(0xFFFFFFFF) // Tarjetas / inputs
val TextPrimaryLight     = Color(0xFF1B1021) // Texto principal casi negro violáceo
val TextSecondaryLight   = Color(0xFF6B5A72) // Texto secundario (labels, hints)
val OutlineLight         = Color(0xFFE0D3EA) // Bordes inputs, divisores
val AccentCyanLight      = Color(0xFF26C6DA) // Detalles tipo iconitos/links

// Para el degradado del botón "Sign in / Sign up"
val PrimaryGradientLight = listOf(
    Color(0xFFE71F7D), // fucsia
    Color(0xFF9C27B0)  // morado
)

val LightColors = lightColorScheme(
    primary = PrimaryColorLight,
    secondary = SecondaryColorLight,
    background = BackgroundColorLight,
    surface = SurfaceColorLight,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = Color(0xFFF0E4FA),   // tarjetas un poco separadas del fondo
    onSurfaceVariant = TextSecondaryLight,
    outline = OutlineLight
)


// =======================
// DARK THEME (pantalla café/morado oscuro)
// =======================
val PrimaryColorDark   = Color(0xFFFF4FA3) // Fucsia más brillante para dark
val SecondaryColorDark = Color(0xFFC77DFF) // Morado claro en dark
val BackgroundColorDark = Color(0xFF160B11) // Fondo tipo chocolate-morado
val SurfaceColorDark    = Color(0xFF201119) // Tarjetas algo más claras
val TextPrimaryDark     = Color(0xFFFDF5FF) // Texto casi blanco con tinte lila
val TextSecondaryDark   = Color(0xFFB99CC8) // Texto secundario apagado
val OutlineDark         = Color(0xFF3A2534) // Bordes en dark
val AccentCyanDark      = Color(0xFF26C6DA) // Se mantiene el cyan para consistencia

val PrimaryGradientDark = listOf(
    Color(0xFFFF4FA3), // fucsia brillante
    Color(0xFFC77DFF)  // morado claro
)

val DarkColors = darkColorScheme(
    primary = PrimaryColorDark,
    secondary = SecondaryColorDark,
    background = BackgroundColorDark,
    surface = SurfaceColorDark,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = Color(0xFF2A1721),
    onSurfaceVariant = TextSecondaryDark,
    outline = OutlineDark
)