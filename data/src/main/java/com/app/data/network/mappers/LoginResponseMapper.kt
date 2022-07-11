package com.app.data.network.mappers

import com.app.data.base.Mapper
import com.app.data.network.entities.response.LoginResponse
import com.app.domain.entities.LoginInfo

class LoginResponseMapper : Mapper<LoginResponse, LoginInfo> {
    override fun map(input: LoginResponse): LoginInfo {
        return LoginInfo(input.token.orEmpty())
    }
}