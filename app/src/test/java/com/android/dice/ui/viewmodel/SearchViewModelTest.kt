package com.android.dice.ui.viewmodel

import com.android.dice.CoroutineTestRule
import com.android.dice.dispatchers.TestDispatcherProvider
import com.android.dice.domain.mapper.ArtistMapper
import com.android.dice.domain.usecase.SearchArtistUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var sut: SearchViewModel
    private val artistMapper = ArtistMapper()
    private val searchArtistUseCase = mockk<SearchArtistUseCase>()
    private val coroutineProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        sut = SearchViewModel(artistMapper, searchArtistUseCase, coroutineProvider)
    }

    @Test
    fun `given query when searchArtist is executed, verify that searchArtistUseCase is executed`() = runTest {
        coEvery {
            searchArtistUseCase.execute(any())
        } returns flowOf()

        sut.searchArtist("query")

        advanceTimeBy(300L)

        coVerify {
            searchArtistUseCase.execute("query")
        }
    }
}
