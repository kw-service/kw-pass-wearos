package minmul.kwpass.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import minmul.kwpass.R
import minmul.kwpass.ui.components.TipBox
import minmul.kwpass.ui.main.MainUiState
import minmul.kwpass.ui.theme.KWPassTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrSizeScreenTopBar(
    navigateUp: () -> Unit, modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.qrcode_size)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(
                        (R.string.goBack)
                    )
                )
            }
        })
}

@Composable
fun QrSizeScreen(
    navController: NavController,
    mainUiState: MainUiState,
    onQrSizeModified: (Float) -> Unit,
    saveQrSizeOnDisk: () -> Unit
) {
    DisposableEffect(Unit) {
        onDispose {
            saveQrSizeOnDisk()
        }
    }

    Scaffold(
        topBar = {
            QrSizeScreenTopBar(
                navigateUp = { navController.navigateUp() })
        }, modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            TipBox(
                title = stringResource(R.string.about_size),
                icon = Icons.Default.Info,
                text = stringResource(R.string.about_size_description),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp)
                    .align(Alignment.TopCenter)

            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mainUiState.process.sampleQrBitmap != null) {
                    Text(
                        text = stringResource(R.string.not_working_qr),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        bitmap = mainUiState.process.sampleQrBitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(mainUiState.process.qrSize.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .zIndex(1f),
                        filterQuality = FilterQuality.None
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Slider(
                value = mainUiState.process.qrSize.toFloat(),
                onValueChange = { onQrSizeModified(it) },
                valueRange = 140f..320f,
                steps = 29,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(start = 32.dp, end = 32.dp, top = 400.dp)

            )
        }
    }
}


@Preview
@Composable
fun QrSizeScreenPreview() {
    KWPassTheme {
        QrSizeScreen(
            navController = rememberNavController(),
            mainUiState = MainUiState(),
            onQrSizeModified = {},
            saveQrSizeOnDisk = {})
    }
}