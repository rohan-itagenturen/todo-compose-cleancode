package com.app.data.repository

import com.app.data.network.UserAuthenticationApi
import com.app.data.network.entities.request.LoginRequest
import com.app.data.network.entities.response.ErrorBody
import com.app.data.network.mappers.LoginResponseMapper
import com.app.domain.entities.LoginInfo
import com.app.domain.exceptions.LoginFailureException
import com.app.domain.repositories.IUserAuthenticationRepository
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class UserAuthenticationRepositoryImpl @Inject constructor(
    private val userAuthAPI: UserAuthenticationApi,
    private val loginRespMapper: LoginResponseMapper,
    private val gson: Gson
) : IUserAuthenticationRepository {

    override suspend fun login(email: String, password: String): LoginInfo {
        try {
            return loginRespMapper.map(userAuthAPI.login(LoginRequest(email, password)))
        } catch (e: Exception) {
            val domainLevelException = when (e) {
                is HttpException -> {
                    @Suppress("BlockingMethodInNonBlockingContext")
                    val errorBody = gson.fromJson(e.response()?.errorBody()?.string(), ErrorBody::class.java)
                    when (e.code()) {
                        400 -> LoginFailureException(errorBody.error.orEmpty())
                        else -> e
                    }
                } else -> e
            }

            throw domainLevelException
        }
    }
}