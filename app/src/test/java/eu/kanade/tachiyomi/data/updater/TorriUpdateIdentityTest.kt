package eu.kanade.tachiyomi.data.updater

import eu.kanade.tachiyomi.BuildConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Fork safety: Torri must never offer the official Mihon APK as its own update.
 */
class TorriUpdateIdentityTest {

    @Test
    fun `stable update checks use Torri releases`() {
        assertEquals("Tomex777/Torri", GITHUB_REPO)
        assertTrue(RELEASE_URL.startsWith("https://github.com/Tomex777/Torri/releases/tag/"))
    }

    @Test
    fun `development builds retain the separate Torri package identity`() {
        assertEquals("app.torri.dev", BuildConfig.APPLICATION_ID)
    }
}
