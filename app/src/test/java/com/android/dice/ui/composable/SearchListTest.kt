package com.android.dice.ui.composable

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.dice.ui.model.Artist
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
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
class SearchListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockedName = "login"
    private val mockedName2 = "login2"

    private val mockedArtist = mockk<Artist>(relaxed = true).apply {
        every { name } returns mockedName
    }

    private val mockedArtist2 = mockk<Artist>(relaxed = true).apply {
        every { name } returns mockedName2
    }

    @Test
    fun `given SearchList Composable when PagingItems is available, then the correct data should be displayed`() {
        val searchItems = flowOf(PagingData.from(listOf(mockedArtist, mockedArtist2)))

        composeTestRule.setContent {
            searchItems.collectAsLazyPagingItems()

            SearchList(searchResult = searchItems.collectAsLazyPagingItems()) {}
        }
        composeTestRule.onNodeWithText(mockedName).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockedName2).assertIsDisplayed()
        composeTestRule.onNodeWithTag("idleText").assertDoesNotExist()
    }

    @Test
    fun `given SearchList Composable when PagingItems is not available, then the correct data should be displayed`() {
        val searchItems = flowOf(PagingData.from(listOf<Artist>()))

        composeTestRule.setContent {
            searchItems.collectAsLazyPagingItems()

            SearchList(searchResult = searchItems.collectAsLazyPagingItems()) {}
        }
        composeTestRule.onNodeWithTag("idleText").assertIsDisplayed()
    }
}
