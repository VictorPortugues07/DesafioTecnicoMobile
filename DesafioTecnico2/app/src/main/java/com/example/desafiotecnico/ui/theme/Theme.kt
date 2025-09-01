package com.example.desafiotecnico.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Color.Primary,
    secondary = Color.Secondary,
    tertiary = Color.PrimaryVariant
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Primary,
    onPrimary = Color.OnPrimary,
    secondary = Color.Secondary,
    onSecondary = Color.OnSecondary,
    tertiary = Color.PrimaryVariant,
    background = Color.Background,
    onBackground = Color.OnBackground,
    surface = Color.Surface,
    onSurface = Color.OnSurface,
    error = Color.Error,
    onError = Color.OnError
)

@Composable
fun DesafioTecnicoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}