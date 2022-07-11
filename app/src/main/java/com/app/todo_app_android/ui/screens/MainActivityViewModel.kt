package com.app.todo_app_android.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber

class MainActivityViewModel : ViewModel() {

    init {
        Timber.e("init")
    }

    fun log() {
        Timber.e("init")
    }
}