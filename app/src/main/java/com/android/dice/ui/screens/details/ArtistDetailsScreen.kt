package com.android.dice.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.android.dice.R
import com.android.dice.ui.composable.AlbumList
import com.android.dice.ui.composable.DetailsTile
import com.android.dice.ui.composable.FullScreenProgress
import com.android.dice.ui.model.Artist
import com.android.dice.ui.viewmodel.AlbumSearchViewModel

@ExperimentalCoilApi
@Composable
fun ArtistDetailsScreen(artist: Artist, onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            TopAppBar {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.artist_details),
                    fontSize = 24.sp
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = artist.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(16.dp))
                DetailsTile(type = stringResource(id = R.string.type), value = artist.type)
                DetailsTile(type = stringResource(id = R.string.country), value = artist.country)
                DetailsTile(
                    type = stringResource(id = R.string.gender),
                    value = artist.gender
                )
                DetailsTile(
                    type = stringResource(id = R.string.score),
                    value = artist.score.toString()
                )

                DetailsTile(
                    type = stringResource(id = R.string.disambiguation),
                    value = artist.disambiguation
                )

                DetailsTile(
                    type = stringResource(id = R.string.tags),
                    value = artist.tags.joinToString(", ")
                )

                ArtistAlbum(artist.id)
            }
        }
    }
}

@Composable
fun ArtistAlbum(id: String) {
    val albumViewModel = hiltViewModel<AlbumSearchViewModel>()

    val loading by albumViewModel.loading.collectAsState()

    val albums by albumViewModel.result.collectAsState()

    LaunchedEffect(Unit) {
        albumViewModel.getAlbum(id)
    }

    if (loading) {
        FullScreenProgress(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

    AlbumList(albums)
}
