package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Torri's signature Night palette.
 *
 * Deep indigo surfaces keep the reader calm at night, with moonlit periwinkle
 * and warm gold accents for clear focus and a distinct Torri identity.
 */
internal object NightColorScheme : BaseColorScheme() {

    override val darkScheme = darkColorScheme(
        primary = Color(0xFFB9C4FF),
        onPrimary = Color(0xFF1D2A5C),
        primaryContainer = Color(0xFF344279),
        onPrimaryContainer = Color(0xFFDCE1FF),
        secondary = Color(0xFFC9BFFF),
        onSecondary = Color(0xFF30265F),
        secondaryContainer = Color(0xFF473D78),
        onSecondaryContainer = Color(0xFFE6DEFF),
        tertiary = Color(0xFFFFD479),
        onTertiary = Color(0xFF412D00),
        tertiaryContainer = Color(0xFF5E4300),
        onTertiaryContainer = Color(0xFFFFDEA2),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF0A0D18),
        onBackground = Color(0xFFE3E6F1),
        surface = Color(0xFF0D1020),
        onSurface = Color(0xFFE3E6F1),
        surfaceVariant = Color(0xFF252A3D),
        onSurfaceVariant = Color(0xFFC5C6D0),
        outline = Color(0xFF8E909A),
        outlineVariant = Color(0xFF444650),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFFE3E6F1),
        inverseOnSurface = Color(0xFF2B2F3A),
        inversePrimary = Color(0xFF4B5E9B),
        surfaceContainerLowest = Color(0xFF070A14),
        surfaceDim = Color(0xFF0A0D18),
        surfaceContainerLow = Color(0xFF121625),
        surfaceContainer = Color(0xFF171B2B),
        surfaceContainerHigh = Color(0xFF212535),
        surfaceContainerHighest = Color(0xFF2C3040),
        surfaceBright = Color(0xFF343849),
    )

    override val lightScheme = lightColorScheme(
        primary = Color(0xFF4B5E9B),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFDCE1FF),
        onPrimaryContainer = Color(0xFF06174B),
        inversePrimary = Color(0xFFB9C4FF),
        secondary = Color(0xFF5E5489),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFE6DEFF),
        onSecondaryContainer = Color(0xFF1A1042),
        tertiary = Color(0xFF765A00),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFFFDEA2),
        onTertiaryContainer = Color(0xFF241A00),
        background = Color(0xFFFAF8FF),
        onBackground = Color(0xFF1A1B24),
        surface = Color(0xFFFAF8FF),
        onSurface = Color(0xFF1A1B24),
        surfaceVariant = Color(0xFFE2E1EC),
        onSurfaceVariant = Color(0xFF45464F),
        surfaceTint = Color(0xFF4B5E9B),
        inverseSurface = Color(0xFF2F3039),
        inverseOnSurface = Color(0xFFF1EFFA),
        outline = Color(0xFF767780),
        outlineVariant = Color(0xFFC6C5D0),
        error = Color(0xFFBA1A1A),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        surfaceDim = Color(0xFFDBD9E4),
        surfaceBright = Color(0xFFFAF8FF),
        surfaceContainerLowest = Color(0xFFFFFFFF),
        surfaceContainerLow = Color(0xFFF4F2FD),
        surfaceContainer = Color(0xFFEFEDF7),
        surfaceContainerHigh = Color(0xFFE9E7F2),
        surfaceContainerHighest = Color(0xFFE3E1EC),
    )
}
