package com.android.dice.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.dice.ui.theme.primaryTextColor
import com.android.dice.ui.theme.secondaryTextColor

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
