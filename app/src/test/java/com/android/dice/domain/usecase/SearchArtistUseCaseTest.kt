package com.android.dice.domain.usecase

import com.android.dice.data.repository.ArtistSearchRepository
import com.android.dice.domain.mapper.ArtistMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchArtistUseCaseTest {
    private lateinit var sut: SearchArtistUseCase
    private val artistSearchRepository = mockk<ArtistSearchRepository>()
    private val artistMapper = ArtistMapper()

    @Test
    fun `given query, when execute is called, then artistSearchRepository searchUser should be called`() = runTest {
        sut = SearchArtistUseCase(artistSearchRepository, artistMapper)
        val query = "searchQuery"
        coEvery {
            artistSearchRepository.searchUser(query)
        } returns flowOf()

        sut.execute(query)

        coVerify {
            artistSearchRepository.searchUser(query)
        }
    }
}
