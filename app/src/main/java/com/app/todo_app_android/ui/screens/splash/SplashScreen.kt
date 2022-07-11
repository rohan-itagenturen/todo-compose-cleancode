package com.app.todo_app_android.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.todo_app_android.R
import com.app.todo_app_android.animations.PulsatingEffect
import com.app.todo_app_android.ui.screens.AppScreen
import com.app.todo_app_android.ui.theme.AppTypography
import com.app.todo_app_android.ui.theme.TodoAppAndroidTheme
import com.app.todo_app_android.ui.theme.White
import kotlinx.coroutines.delay

private const val SplashWaitTimeMillis = 3000L

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigateTo: (String) -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentOnTimeOut by rememberUpdatedState(newValue = onNavigateTo)

        LaunchedEffect(key1 = true, block = {
            delay(SplashWaitTimeMillis)
            currentOnTimeOut(AppScreen.Login.name)
        })

        PulsatingEffect {
            Text(
                text = stringResource(id = R.string.todo_app),
                style = AppTypography.h4,
                color = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TodoAppAndroidTheme {
        SplashScreen(onNavigateTo = { })
    }
}