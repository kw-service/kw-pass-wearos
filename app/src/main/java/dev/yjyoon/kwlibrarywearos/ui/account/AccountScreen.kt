package dev.yjyoon.kwlibrarywearos.ui.account

import android.app.RemoteInput
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.theme.Primary
import dev.yjyoon.kwlibrarywearos.ui.theme.White87

@OptIn(ExperimentalHorologistComposeLayoutApi::class)
@Composable
fun AccountScreen(
    viewModel: AccountViewModel,
    navigateToQrCode: (User) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    val listState = rememberScalingLazyListState()
    val vignetteState = remember { mutableStateOf(VignettePosition.TopAndBottom) }
    val showVignette = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            result.data?.let { data ->
                val results: Bundle = RemoteInput.getResultsFromIntent(data)

                results.getCharSequence(INPUT_KEY_ID)?.toString()?.let { viewModel.setId(it) }
                results.getCharSequence(INPUT_KEY_PW)?.toString()?.let { viewModel.setPassword(it) }
                results.getCharSequence(INPUT_KEY_PHONE)?.toString()?.let { viewModel.setPhone(it) }
            }
        }

    Scaffold(
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState,
                modifier = Modifier
            )
        },
        vignette = {
            if (showVignette.value) {
                Vignette(vignettePosition = vignetteState.value)
            }
        },
        timeText = {
            TimeText()
        }
    ) {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .rotaryWithScroll(focusRequester, listState),
            state = listState,
            autoCentering = AutoCenteringParams(0),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Text(
                    stringResource(id = R.string.input_account),
                    style = MaterialTheme.typography.title2,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            item {
                AccountInputChip(
                    type = AccountInputType.Id,
                    input = state.id,
                    onClick = { launcher.launch(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                AccountInputChip(
                    type = AccountInputType.Password,
                    input = "●".repeat(state.password.length),
                    onClick = { launcher.launch(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                AccountInputChip(
                    type = AccountInputType.Phone,
                    input = state.phone,
                    onClick = { launcher.launch(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Button(
                    onClick = { navigateToQrCode(viewModel.getUser()) },
                    enabled = state.filled,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = White87,
                        contentColor = Primary,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                }
            }
        }
    }
}
