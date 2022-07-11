package com.app.todo_app_android.ui.screens

enum class AppScreen {
    Splash,
    Login,
    Dashboard;


    companion object {
        fun fromRoute(route: String?): AppScreen {
            return when (route?.substringBefore("/")) {
                Splash.name -> Splash
                Login.name -> Login
                Dashboard.name -> Dashboard
                null -> Splash
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}