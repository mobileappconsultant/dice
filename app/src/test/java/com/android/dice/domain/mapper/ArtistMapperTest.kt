package com.android.dice.domain.mapper

import com.android.dice.data.model.ArtistSchema
import com.android.dice.data.model.Tag
import com.android.dice.domain.model.ArtistDomain
import com.android.dice.ui.model.Artist
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class ArtistMapperTest {

    private val sut = ArtistMapper()

    private val mockedId = "98"
    private val mockedName = "Davido"
    private val mockedCountry = "mockedCountry"
    private val mockedGender = "mockedGender"
    private val mockedDisambiguation = "mockedDisambiguation"
    private val mockedTags = listOf(
        Tag(count = 0, name = "")
    )
    private val mockedType = "type"
    private val mockedScore = 150

    private val mockedSearchSchema = mockk<ArtistSchema>().apply {
        every { id } returns mockedId
        every { name } returns mockedName
        every { country } returns mockedCountry
        every { gender } returns mockedGender
        every { disambiguation } returns mockedDisambiguation
        every { tags } returns mockedTags
        every { score } returns mockedScore
        every { type } returns mockedType
    }

    private val mockedSearchDomain = ArtistDomain(
        id = mockedId,
        name = mockedName,
        type = mockedType,
        country = mockedCountry,
        gender = mockedGender,
        disambiguation = mockedDisambiguation,
        tags = mockedTags.map { it.name },
        score = mockedScore,
    )

    private val mockedArtist = Artist(
        id = mockedId,
        name = mockedName,
        type = mockedType,
        country = mockedCountry,
        gender = mockedGender,
        disambiguation = mockedDisambiguation,
        tags = mockedTags.map { it.name },
        score = mockedScore,
    )

    @Test
    fun `test schema mapping`() {
        val actualResult = sut.mapToDomain(mockedSearchSchema)
        assertEquals(mockedSearchDomain, actualResult)
    }

    @Test
    fun `test domain mapping`() {
        val actualResult = sut.mapToPresentation(mockedSearchDomain)
        assertEquals(mockedArtist.tags, actualResult.tags)
        assertEquals(mockedArtist, actualResult)
    }
}
