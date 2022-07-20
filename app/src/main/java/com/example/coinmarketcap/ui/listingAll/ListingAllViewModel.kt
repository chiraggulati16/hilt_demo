package com.example.coinmarketcap.ui.listingAll

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coinmarketcap.repository.Repository
import com.example.coinmarketcap.data.responseUtil.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ListingAllViewModel(private val repository: Repository) : ViewModel() {

    fun fetchLatestCryptoAsset() = liveData(Dispatchers.IO) {

        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getLatestCryptoAsset()))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Error Occurred", null))
        }
    }

    fun fetchFiatCurrencies() = liveData(Dispatchers.IO) {

        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getFiatCurrencies()))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Error Occurred", null))
        }
    }
}