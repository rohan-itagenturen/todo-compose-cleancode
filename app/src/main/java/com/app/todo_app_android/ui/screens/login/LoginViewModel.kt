package com.app.todo_app_android.ui.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.exceptions.LoginFailureException
import com.app.domain.usecases.LoginUseCase
import com.app.todo_app_android.ui.screens.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow: StateFlow<UiState> get() = _uiStateFlow

    var uiState by mutableStateOf(UiState())
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                when {
                    email.isBlank() ->
                        _uiStateFlow.value = _uiStateFlow.value.copy(
                            localErrorState = UiState.ErrorState.ENTER_EMAIL
                        )
                    Patterns.EMAIL_ADDRESS.matcher(email).matches().not() ->
                        _uiStateFlow.value = _uiStateFlow.value.copy(
                            localErrorState = UiState.ErrorState.INVALID_EMAIL
                        )
                    password.isBlank() ->
                        _uiStateFlow.value = _uiStateFlow.value.copy(
                            localErrorState = UiState.ErrorState.ENTER_PASSWORD
                        )
                    else -> {
                        _uiStateFlow.value = _uiStateFlow.value.copy(
                            loading = true,
                            localErrorState = null
                        )
                        loginUseCase.invoke(email, password)
                        _uiStateFlow.value = _uiStateFlow.value.copy(
                            navigate = AppScreen.Dashboard,
                            loading = false
                        )
                    }
                }
            } catch (e: Exception) {
                when (e) {
                    is LoginFailureException ->
                        _uiStateFlow.value = UiState(
                            networkErrorMessage = e.message
                        )
                    else -> {
                        _uiStateFlow.value = UiState(
                            networkErrorMessage = e.message
                        )
                    }
                }

            }
        }
    }

    fun onNavigate() {
        _uiStateFlow.value = _uiStateFlow.value.apply {
            navigate = null
        }
    }

    fun resetUIState() {
        _uiStateFlow.value = UiState()
    }

    data class UiState(
        var loading: Boolean = false,
        var navigate: AppScreen? = null,
        var networkErrorMessage: String? = null,
        var localErrorState: ErrorState? = null
    ) {
        enum class ErrorState {
            ENTER_EMAIL,
            INVALID_EMAIL,
            ENTER_PASSWORD
        }
    }
}