package com.android.dice.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.android.dice.R
import com.android.dice.ui.model.Artist
import com.android.dice.ui.theme.primaryTextColor
import com.android.dice.ui.theme.secondaryTextColor

@ExperimentalCoilApi
@Composable
fun ArtistDetailsScreen(artist: Artist, onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
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
            }
        }
    }
}

@Composable
fun DetailsTile(type: String, value: String) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = type,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.secondaryTextColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primaryTextColor
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
