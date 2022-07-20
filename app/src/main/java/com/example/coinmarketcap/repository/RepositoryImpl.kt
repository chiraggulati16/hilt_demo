package com.example.coinmarketcap.repository

import com.example.coinmarketcap.data.models.CryptoAssetLatest
import com.example.coinmarketcap.data.models.CryptoCurrencyInfo
import com.example.coinmarketcap.data.models.FiatCurrencies
import com.example.coinmarketcap.data.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val apiKey: String
) : Repository {

    override suspend fun getLatestCryptoAsset(): Response<CryptoAssetLatest> =
        networkService.getLatestCryptoAsset(apiKey)

    override suspend fun getFiatCurrencies(): Response<FiatCurrencies> =
        networkService.getFiatCurrencies(apiKey)

    override suspend fun getCryptoInfo(id: String): Response<CryptoCurrencyInfo> =
        networkService.getCryptoInfo(apiKey, id)
}