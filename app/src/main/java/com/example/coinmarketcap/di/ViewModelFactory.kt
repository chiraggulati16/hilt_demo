package com.example.coinmarketcap.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinmarketcap.repository.Repository
import com.example.coinmarketcap.ui.cryptoInfo.CryptoInfoViewModel
import com.example.coinmarketcap.ui.listingAll.ListingAllViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(ListingAllViewModel::class.java) -> {
                ListingAllViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CryptoInfoViewModel::class.java) -> {
                CryptoInfoViewModel(repository) as T
            }
            else -> {
                null as T
            }
        }
    }
}