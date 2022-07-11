package com.app.domain.usecases

import com.app.domain.entities.LoginInfo
import com.app.domain.repositories.IUserAuthenticationRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val repository: IUserAuthenticationRepository) {

    suspend operator fun invoke(email: String, password: String): LoginInfo {
        return repository.login(email, password)
    }
}