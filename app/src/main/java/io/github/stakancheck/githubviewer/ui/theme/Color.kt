package io.github.stakancheck.githubviewer.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
val md_theme_light_primary = Color(0xFF0B68DB) // Акцентный синий
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFADC9FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001C3B)
val md_theme_light_secondary = Color(0xFF1F232B) // Тёмный серый (на фоне)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFF6F8FA) // Surface back
val md_theme_light_onSecondaryContainer = Color(0xFF000000)
val md_theme_light_tertiary = Color(0xFF824FDF) // Акцентный tertiary
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFAF6F6)
val md_theme_light_onTertiaryContainer = Color(0xFF000000)
val md_theme_light_background = Color(0xFFFFFFFF) // Белый фон
val md_theme_light_onBackground = Color(0xFF1F232B) // На фоне
val md_theme_light_surface = Color(0xFFF6F8FA)
val md_theme_light_onSurface = Color(0xFF1F232B)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_outline = Color(0xFF74777F)
val md_theme_light_surfaceVariant = Color(0xFFECEFF1)
val md_theme_light_onSurfaceVariant = Color(0xFF1F232B)
val md_theme_light_surfaceContainer = Color(0xFFECEFF1)
val md_theme_light_inverseSurface = Color(0xFF2F3033)
val md_theme_light_inverseOnSurface = Color(0xFFF1F0F4)
val md_theme_light_inversePrimary = Color(0xFFA6C8FF)

// Dark Theme Colors
val md_theme_dark_primary = Color(0xFF6FA6FF) // Светлый акцентный синий
val md_theme_dark_onPrimary = Color(0xFF003060)
val md_theme_dark_primaryContainer = Color(0xFF004787)
val md_theme_dark_onPrimaryContainer = Color(0xFFD5E3FF)
val md_theme_dark_secondary = Color(0xFFF6F8FA) // Светлая поверхность
val md_theme_dark_onSecondary = Color(0xFF1F232B)
val md_theme_dark_secondaryContainer = Color(0xFF3E5A6E)
val md_theme_dark_onSecondaryContainer = Color(0xFFD5E3FF)
val md_theme_dark_tertiary = Color(0xFF824FDF) // Акцентный tertiary
val md_theme_dark_onTertiary = Color(0xFFFFFFFF)
val md_theme_dark_tertiaryContainer = Color(0xFF706363)
val md_theme_dark_onTertiaryContainer = Color(0xFFFFFFFF)
val md_theme_dark_background = Color(0xFF1F232B) // Тёмный фон
val md_theme_dark_onBackground = Color(0xFFF6F8FA)
val md_theme_dark_surface = Color(0xFF2F3033)
val md_theme_dark_onSurface = Color(0xFFF6F8FA)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_outline = Color(0xFF8D9199)
val md_theme_dark_surfaceVariant = Color(0xFF3E5A6E)
val md_theme_dark_onSurfaceVariant = Color(0xFFFFFFFF)
val md_theme_dark_surfaceContainer = Color(0xFF3E5A6E)
val md_theme_dark_inverseSurface = Color(0xFFF6F8FA)
val md_theme_dark_inverseOnSurface = Color(0xFF1F232B)
val md_theme_dark_inversePrimary = Color(0xFF0B68DB)


val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceContainer = md_theme_light_surfaceContainer,
)


val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceContainer = md_theme_dark_surfaceContainer,
)
