package dev.yjyoon.kwlibrarywearos.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import dev.yjyoon.kwlibrarywearos.ui.theme.White87

@Composable
fun LoadingComponent(
    @StringRes textRes: Int? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(indicatorColor = White87)
        textRes?.let {
            Text(
                text = stringResource(id = it),
                style = MaterialTheme.typography.caption2,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
