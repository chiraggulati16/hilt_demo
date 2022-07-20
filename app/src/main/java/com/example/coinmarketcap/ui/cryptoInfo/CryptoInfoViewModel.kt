package com.example.coinmarketcap.ui.cryptoInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coinmarketcap.repository.Repository
import com.example.coinmarketcap.data.responseUtil.Resource
import java.lang.Exception

class CryptoInfoViewModel(private val repository: Repository) : ViewModel() {

    fun fetchCryptoInfo(id:String) = liveData {

        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getCryptoInfo(id)))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Error Occurred", null))
        }

    }


}