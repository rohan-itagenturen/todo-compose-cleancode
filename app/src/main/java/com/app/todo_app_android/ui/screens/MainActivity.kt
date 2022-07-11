package com.app.todo_app_android.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.todo_app_android.ui.screens.dashboard.DashboardScreen
import com.app.todo_app_android.ui.screens.login.LoginScreen
import com.app.todo_app_android.ui.screens.login.LoginViewModel
import com.app.todo_app_android.ui.screens.splash.SplashScreen
import com.app.todo_app_android.ui.theme.AppTypography
import com.app.todo_app_android.ui.theme.TodoAppAndroidTheme
import com.app.todo_app_android.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.log()
        setContent {
            ToDoApp()
        }
    }
}

@Composable
fun ToDoApp() {
    TodoAppAndroidTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = AppScreen.Splash.name) {
            composable(AppScreen.Splash.name) {
                SplashScreen(
                    onNavigateTo = {
                        navController.navigate(it) {
                            //This will remove the SplashScreen from the backStack entry
                            popUpTo(AppScreen.Splash.name) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(AppScreen.Login.name) {
                LoginScreen(
                    onNavigateTo = { navController.navigate(it) }
                )
            }
            composable(AppScreen.Dashboard.name) {
                DashboardScreen(
                    onNavigateTo = { navController.navigate(it) }
                )
            }
        }
    }
}

@Composable
fun UI_UX() {
    Column {
        Text(text = "Hello H4", style = AppTypography.h4, color = White)
        Text(text = "Hello H5", style = AppTypography.h5, color = White)
        Text(text = "Hello H6", style = AppTypography.h6, color = White)
        Text(text = "Hello SubTitle", style = AppTypography.subtitle1, color = White)
        Text(text = "Hello SubTitle2", style = AppTypography.subtitle2, color = White)
        Text(text = "Hello Body1", style = AppTypography.body1, color = White)
        Text(text = "Hello Body2", style = AppTypography.body2, color = White)
        Text(text = "Hello Button", style = AppTypography.button, color = White)
        Text(text = "Hello Caption", style = AppTypography.caption, color = White)
        Text(text = "Hello Overline", style = AppTypography.overline, color = White)
    }
}

@Preview(showBackground = true)
@Composable
fun UI_UX_Preview() {
    TodoAppAndroidTheme {
        UI_UX()
    }
}