package com.phonemodels.data.di

import com.phonemodels.data.api.RestAPI
import com.phonemodels.data.repositories.PhonesRepositoryImpl
import com.phonemodels.domain.repositories.PhonesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providePhonesRepository(restAPI: RestAPI): PhonesRepository {
        return PhonesRepositoryImpl(restAPI)
    }
}