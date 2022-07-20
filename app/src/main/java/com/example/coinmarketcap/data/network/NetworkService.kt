package com.example.coinmarketcap.data.network

import com.example.coinmarketcap.data.models.CryptoAssetLatest
import com.example.coinmarketcap.data.models.CryptoCurrencyInfo
import com.example.coinmarketcap.data.models.FiatCurrencies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface NetworkService {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCryptoAsset(@Header("X-CMC_PRO_API_KEY") key: String): Response<CryptoAssetLatest>

    @GET("/v1/fiat/map")
    suspend fun getFiatCurrencies(@Header("X-CMC_PRO_API_KEY") key: String): Response<FiatCurrencies>

    @GET("/v1/cryptocurrency/info")
    suspend fun getCryptoInfo(
        @Header("X-CMC_PRO_API_KEY") key: String,
        @Query("id")  id: String
    ): Response<CryptoCurrencyInfo>

}