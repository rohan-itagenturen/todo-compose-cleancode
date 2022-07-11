package com.app.data.di

import com.app.data.network.mappers.LoginResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideLoginResponseMapper(): LoginResponseMapper {
        return LoginResponseMapper()
    }
}