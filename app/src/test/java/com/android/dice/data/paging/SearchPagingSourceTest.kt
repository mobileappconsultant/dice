package com.android.dice.data.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.android.dice.CoroutineTestRule
import com.android.dice.data.api.MusicApi
import com.android.dice.data.model.ArtistSchema
import com.android.dice.data.model.SearchResponseSchema
import com.android.dice.domain.model.ArtistDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchPagingSourceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val mockedSearchSchema = mockk<ArtistSchema>(relaxed = true)

    private val mockedResponseSchema = SearchResponseSchema(
        artists = listOf(mockedSearchSchema, mockedSearchSchema, mockedSearchSchema),
        count = 60,
        created = "",
        offset = 0
    )

    private val mockSearchApi = mockk<MusicApi>()
    lateinit var sut: SearchPagingSource

    @Before
    fun setup() {
        sut = SearchPagingSource(mockSearchApi, "")
    }

    @Test
    fun `given when paging source load is called and an error occurs, it should return error`() =
        runTest {
            val error = RuntimeException("404", Throwable())
            coEvery { mockSearchApi.searchArtist(any(), any(), any()) } throws error
            val expectedResult = PagingSource.LoadResult.Error<Int, ArtistDomain>(error)
            assertEquals(
                expectedResult,
                sut.load(
                    PagingSource.LoadParams.Refresh(
                        key = 0,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `given when paging source load is called and request is successful, it should return correct data`() =
        runTest {
            coEvery { mockSearchApi.searchArtist(any(), any(), any()) } returns mockedResponseSchema
            val expectedResult = PagingSource.LoadResult.Page(
                data = listOf(mockedSearchSchema, mockedSearchSchema, mockedSearchSchema),
                prevKey = null,
                nextKey = 30
            )
            assertEquals(
                expectedResult,
                sut.load(
                    PagingSource.LoadParams.Append(
                        key = 0,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `given when paging source load is called and the list is empty, prev key and next key should be null`() =
        runTest {
            coEvery {
                mockSearchApi.searchArtist(
                    any(),
                    any(),
                    any()
                )
            } returns mockedResponseSchema.copy(artists = listOf())
            val expectedResult = PagingSource.LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
            assertEquals(
                expectedResult,
                sut.load(
                    PagingSource.LoadParams.Append(
                        key = 2,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                )
            )
        }
}
