package dev.yjyoon.kwlibrarywearos.ui.account

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() : ViewModel() {

    val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState

    fun setId(id: String) = _uiState.update { it.copy(id = id) }
    fun setPassword(password: String) = _uiState.update { it.copy(password = password) }
    fun setPhone(phone: String) = _uiState.update { it.copy(phone = phone) }
}