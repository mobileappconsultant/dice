package com.android.dice.domain.mapper

import com.android.dice.data.model.album.ReleaseGroup
import com.android.dice.domain.model.AlbumDomain
import com.android.dice.ui.model.Album
import javax.inject.Inject

class AlbumMapper @Inject constructor() {

    fun mapToDomain(schema: ReleaseGroup): AlbumDomain = AlbumDomain(
        name = schema.title,
        dateReleased = schema.firstReleaseDate
    )

    fun mapToPresentation(domain: AlbumDomain): Album = Album(
        name = domain.name,
        dateReleased = domain.dateReleased
    )
}
