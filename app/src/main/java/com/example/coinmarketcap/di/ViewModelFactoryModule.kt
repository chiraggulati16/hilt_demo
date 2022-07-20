package com.example.coinmarketcap.di

import com.example.coinmarketcap.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideCryptoAssetViewModelFactory(
        repository: Repository
    ) = ViewModelFactory(repository)

}