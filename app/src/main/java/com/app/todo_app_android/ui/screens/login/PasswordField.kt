package com.app.todo_app_android.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.todo_app_android.R
import com.app.todo_app_android.ui.theme.Montserrat
import com.app.todo_app_android.ui.theme.TodoAppAndroidTheme

@Composable
fun PasswordField(
    password: String,
    hint: String,
    onKeyBoardActionClick: (String) -> Unit = {},
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    isDefaultPasswordVisible: Boolean = false
) {
    var isPasswordVisible by remember { mutableStateOf(isDefaultPasswordVisible) }
    val iconEye = painterResource(if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
    val iconDescription = stringResource(if (isPasswordVisible) R.string.show_pwd_text else R.string.hide_pwd_text)
    val visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontFamily = Montserrat),
        label = { Text(hint) },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = iconEye,
                    contentDescription = iconDescription
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = visualTransformation,
        keyboardActions = KeyboardActions(onDone = {
            onKeyBoardActionClick(password)
        }),
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PasswordFieldPreview() {
    TodoAppAndroidTheme {
        Surface {
            Column {
                Box(modifier = Modifier.padding(20.dp)) {
                    PasswordField("123456", "Your Password", {}, false, {}, false)
                }
                Box(modifier = Modifier.padding(20.dp)) {
                    PasswordField("123456", "Password", {}, false, {}, true)
                }
            }
        }
    }
}