package com.app.domain.repositories

import com.app.domain.entities.LoginInfo

interface IUserAuthenticationRepository {

    suspend fun login(email: String, password: String): LoginInfo
}