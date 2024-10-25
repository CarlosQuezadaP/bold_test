package com.bold.bolttest.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // Color primario
    onPrimary = Color.White, // Color para texto sobre el primario
    secondary = Color(0xFF03DAC5), // Color secundario
    onSecondary = Color.Black, // Color para texto sobre el secundario
    background = Color(0xFFFFFFFF), // Color de fondo
    onBackground = Color.Black, // Color para texto sobre el fondo
    surface = Color(0xFFFFFFFF), // Color de superficie (como tarjetas)
    onSurface = Color.Black // Color para texto sobre la superficie
)

private val Typography = Typography(
    bodyLarge = TextStyle(
        fontSize = 16.sp, color = Color.Black
    )
)


@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content // Contenido que usará el tema
    )
}