package com.android.dice.ui.screens.artists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.android.dice.R
import com.android.dice.ui.composable.SearchComponent
import com.android.dice.ui.composable.SearchList
import com.android.dice.ui.model.Artist

@Composable
fun ArtistScreen(artists: LazyPagingItems<Artist>, onArtistSearch: (String) -> Unit, onArtistClicked: (Artist) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(R.string.artist_search),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        }
        SearchComponent(onArtistSearch)
        SearchList(searchResult = artists, onArtistClicked = onArtistClicked)
    }
}
