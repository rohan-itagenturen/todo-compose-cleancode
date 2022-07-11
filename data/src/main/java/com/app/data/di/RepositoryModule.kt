package com.app.data.di

import com.app.data.network.UserAuthenticationApi
import com.app.data.network.mappers.LoginResponseMapper
import com.app.data.repository.UserAuthenticationRepositoryImpl
import com.app.domain.repositories.IUserAuthenticationRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserAuthenticationRepository(userAuthAPI: UserAuthenticationApi, loginRespMapper: LoginResponseMapper, gson: Gson): IUserAuthenticationRepository {
        return UserAuthenticationRepositoryImpl(userAuthAPI, loginRespMapper, gson)
    }
}