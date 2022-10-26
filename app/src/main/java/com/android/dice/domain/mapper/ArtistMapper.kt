package com.android.dice.domain.mapper

import com.android.dice.data.model.ArtistSchema
import com.android.dice.domain.model.ArtistDomain
import com.android.dice.ui.model.Artist
import javax.inject.Inject

class ArtistMapper @Inject constructor() {
    fun mapToDomain(schema: ArtistSchema): ArtistDomain = ArtistDomain(
        id = schema.id,
        country = schema.area?.name ?: schema.country ?: "Not Available",
        gender = schema.gender ?: "Not Available",
        name = schema.name,
        disambiguation = schema.disambiguation ?: "Not Available",
        type = schema.type ?: "Not Available",
        tags = schema.tags?.map {
            it.name
        } ?: listOf(),
        score = schema.score
    )

    fun mapToPresentation(domain: ArtistDomain): Artist = Artist(
        id = domain.id,
        country = domain.country,
        gender = domain.gender,
        name = domain.name,
        disambiguation = domain.disambiguation,
        type = domain.type,
        tags = domain.tags,
        score = domain.score
    )
}
