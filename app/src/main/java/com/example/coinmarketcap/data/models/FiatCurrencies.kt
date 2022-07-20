package com.example.coinmarketcap.data.models

data class FiatCurrencies(
    val `data`: List<CurrencyData>,
    val status: FiatCurrenciesStatus
)
data class CurrencyData(
    val id: Int,
    val name: String,
    val sign: String,
    val symbol: String
)

data class FiatCurrenciesStatus(
    val credit_count: Int,
    val elapsed: Int,
    val error_code: Int,
    val error_message: Any,
    val notice: Any,
    val timestamp: String
)
