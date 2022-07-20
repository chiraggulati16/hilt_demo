package com.example.coinmarketcap.di

import com.example.coinmarketcap.data.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    private val BASE_URL = "https://pro-api.coinmarketcap.com"

    @Singleton
    @Provides
    fun provideApiKey() = "334e5af3-111e-41bf-845b-3d340d553de4"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)
}