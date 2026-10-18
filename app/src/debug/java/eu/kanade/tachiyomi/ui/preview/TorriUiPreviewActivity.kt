package eu.kanade.tachiyomi.ui.preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import eu.kanade.domain.ui.model.AppTheme
import eu.kanade.presentation.library.components.LibraryResumePrompt
import eu.kanade.presentation.more.settings.screen.appearance.AppThemeStudioScreen
import eu.kanade.presentation.theme.TachiyomiTheme

/** Debug-only capture host; never compiled into production/RC APKs. */
class TorriUiPreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mode = intent.getStringExtra("screen")
        window.statusBarColor = android.graphics.Color.rgb(14, 11, 18)
        window.navigationBarColor = android.graphics.Color.rgb(14, 11, 18)
        window.decorView.systemUiVisibility = 0
        setContent {
            TachiyomiTheme(appTheme = AppTheme.NIGHT, previewDark = true) {
                when (mode) {
                    "studio" -> Navigator(AppThemeStudioScreen())
                    else -> ContinuePreview()
                }
            }
        }
    }
}

@Composable
private fun ContinuePreview() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Library") },
            )
        },
        bottomBar = {
            NavigationBar {
                listOf("Library", "Updates", "History", "Browse", "More").forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = index == 0,
                        onClick = {},
                        icon = { Text(if (index == 0) "▣" else "○") },
                        label = { Text(title, maxLines = 1) },
                    )
                }
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                Text(
                    "My library",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "Your manga and chapters",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text("Recently read", style = MaterialTheme.typography.titleMedium)
                        HorizontalDivider()
                        Text("Sample manga", style = MaterialTheme.typography.bodyLarge)
                        Text("Continue from your last chapter", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            LibraryResumePrompt(
                mangaTitle = "Sample manga",
                chapterNumber = 12.0,
                onResume = {},
                onDismiss = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            )
        }
    }
}
