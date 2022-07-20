package com.example.coinmarketcap.data.models
import com.google.gson.annotations.SerializedName

data class CryptoCurrencyInfo(
    @SerializedName("data")
    val cryptoInfoData: Map<String, IdData>,
    @SerializedName("status")
    val cryptoInfoStatus: CryptoInfoStatus
)

data class CryptoInfoStatus(
    val credit_count: Int,
    val elapsed: Int,
    val error_code: Int,
    val error_message: Any,
    val notice: Any,
    val timestamp: String
)

data class IdData(
    val category: String,
    val contract_address: List<ContractAddress>,
    val date_added: String,
    val date_launched: Any,
    val description: String,
    val id: Int,
    val is_hidden: Int,
    val logo: String,
    val name: String,
    val notice: String,
    val platform: Any,
    val self_reported_circulating_supply: Any,
    val self_reported_market_cap: Any,
    val self_reported_tags: Any,
    val slug: String,
    val subreddit: String,
    val symbol: String,
    @SerializedName("tag-groups")
    val tagGroups: List<String>,
    @SerializedName("tag-names")
    val tagNames: List<String>,
    val tags: List<String>,
    val twitter_username: String,
    val urls: Urls
)

data class Urls(
    val announcement: List<String>,
    val chat: List<String>,
    val explorer: List<String>,
    val facebook: List<Any>,
    val message_board: List<String>,
    val reddit: List<String>,
    val source_code: List<String>,
    val technical_doc: List<String>,
    val twitter: List<Any>,
    val website: List<String>
)

data class Coin(
    val id: String,
    val name: String,
    val slug: String,
    val symbol: String
)

data class ContractAddress(
    val contract_address: String,
    val platform: Platform
)