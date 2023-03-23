package dev.yjyoon.kwlibrarywearos.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwlibrarywearos.ui.account.AccountActivity
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.qrcode.QrCodeActivity
import dev.yjyoon.kwlibrarywearos.ui.theme.KwLibraryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KwLibraryTheme {
                MainScreen(
                    viewModel = viewModel,
                    navigateToAccount = ::startAccountActivity,
                    navigateToQrCode = ::startQrCodeActivity
                )
            }
        }
    }

    private fun startAccountActivity() {
        AccountActivity.startActivity(this)
        finish()
    }

    private fun startQrCodeActivity(user: User) {
        QrCodeActivity.startActivity(this, user)
        finish()
    }
}
