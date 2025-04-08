package com.example.frontendmobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    error = ErrorColor
)

@Composable
fun FrontendmobileTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography,
        content = content
    )
}
