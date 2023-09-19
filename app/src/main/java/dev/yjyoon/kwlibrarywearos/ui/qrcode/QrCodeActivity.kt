package dev.yjyoon.kwlibrarywearos.ui.qrcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.android.horologist.compose.ambient.AmbientAware
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwlibrarywearos.ui.account.AccountActivity
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.theme.KwLibraryTheme

@AndroidEntryPoint
class QrCodeActivity : ComponentActivity() {

    private val viewModel: QrCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AmbientAware(isAlwaysOnScreen = true) {
                KwLibraryTheme {
                    QrCodeScreen(
                        viewModel = viewModel,
                        navigateToAccount = ::startAccountActivity,
                        ambientStateUpdate = it
                    )
                }
            }
        }
    }

    private fun startAccountActivity() {
        AccountActivity.startActivity(this)
    }

    companion object {
        fun startActivity(context: Context, user: User) {
            val intent = Intent(context, QrCodeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .putExtra(QrCodeViewModel.EXTRA_KEY_USER, user)
            context.startActivity(intent)
        }
    }
}
