package com.android.dice.ui.composable

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.android.dice.ui.theme.ArtistSearchTheme
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.O_MR1],
    application = HiltTestApplication::class
)
class ErrorPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given when ErrorPage composable is used, it should display the correct text`() {
        val mockedError = "Error Message"
        composeTestRule.setContent {
            ArtistSearchTheme {
                ErrorPage(message = mockedError) {}
            }
        }

        composeTestRule.onNodeWithText(mockedError).assertIsDisplayed()

        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
}
