package com.example.coinmarketcap.repository

import com.example.coinmarketcap.data.models.CryptoAssetLatest
import com.example.coinmarketcap.data.models.CryptoCurrencyInfo
import com.example.coinmarketcap.data.models.FiatCurrencies
import retrofit2.Response

interface Repository {

    suspend fun getLatestCryptoAsset(): Response<CryptoAssetLatest>
    suspend fun getFiatCurrencies(): Response<FiatCurrencies>
    suspend fun getCryptoInfo(id:String) : Response<CryptoCurrencyInfo>

}