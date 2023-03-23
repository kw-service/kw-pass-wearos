package dev.yjyoon.kwlibrarywearos.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.qrcode.QrCodeActivity
import dev.yjyoon.kwlibrarywearos.ui.theme.KwLibraryTheme

@AndroidEntryPoint
class AccountActivity : ComponentActivity() {

    private val viewModel: AccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KwLibraryTheme {
                AccountScreen(
                    viewModel = viewModel,
                    navigateToQrCode = ::startQrCodeActivity
                )
            }
        }
    }

    private fun startQrCodeActivity(user: User) {
        QrCodeActivity.startActivity(this, user)
        finish()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, AccountActivity::class.java)
            context.startActivity(intent)
        }
    }
}
