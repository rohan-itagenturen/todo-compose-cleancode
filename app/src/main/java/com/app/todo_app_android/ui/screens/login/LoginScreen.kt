package com.app.todo_app_android.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.todo_app_android.R
import com.app.todo_app_android.ui.theme.*


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateTo: (String) -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiStateFlow by viewModel.uiStateFlow.collectAsState()


    if (uiStateFlow.navigate != null) {
        onNavigateTo.invoke(uiStateFlow.navigate?.name.orEmpty())
        viewModel.onNavigate()
    } else {
        uiStateFlow.networkErrorMessage?.let {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {
                    TextButton(onClick = { viewModel.resetUIState() }) {
                        Text(text = stringResource(R.string.btn_ok))
                    }
                },
                title = { Text(text = stringResource(R.string.alert_title_login_failed), modifier) },
                text = { Text(text = it, modifier) },
            )
        }
    }
    LoginScreenComponent(
        modifier = modifier,
        isLoading = uiStateFlow.loading,
        uiStateFlow.localErrorState
    ) { email, password -> viewModel.login(email, password) }
}

@Composable
fun LoginScreenComponent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    localErrorState: LoginViewModel.UiState.ErrorState? = null,
    onLoginClick: (String, String) -> Unit
) {
    Surface {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Spacer(modifier = modifier.weight(1f, true))
                Text(
                    text = stringResource(R.string.login),
                    style = AppTypography.h4,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    textStyle = TextStyle(fontFamily = Montserrat),
                    label = { Text(stringResource(R.string.your_email)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    isError = (localErrorState == LoginViewModel.UiState.ErrorState.ENTER_EMAIL
                            || localErrorState == LoginViewModel.UiState.ErrorState.INVALID_EMAIL),
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordField(
                    password = password,
                    hint = stringResource(R.string.your_password),
                    onKeyBoardActionClick = { onLoginClick(email, it) },
                    isError = localErrorState == LoginViewModel.UiState.ErrorState.ENTER_PASSWORD,
                    onValueChange = { password = it }
                )
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .background(Primary),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { onLoginClick.invoke(email, password) }) {
                    Text(
                        text = stringResource(R.string.submit),
                        color = White
                    )
                }
                Spacer(modifier = modifier.weight(1f, true))
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TodoAppAndroidTheme {
        LoginScreenComponent { _, _ -> }
    }
}