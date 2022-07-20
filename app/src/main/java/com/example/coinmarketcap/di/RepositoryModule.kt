package com.example.coinmarketcap.di

import com.example.coinmarketcap.data.network.NetworkService
import com.example.coinmarketcap.repository.Repository
import com.example.coinmarketcap.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(networkService: NetworkService, apiKey: String): Repository =
        RepositoryImpl(networkService, apiKey)

}