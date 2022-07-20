package com.example.coinmarketcap.ui.listingAll.stroiesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmarketcap.R
import com.example.coinmarketcap.adapter.CurrencyViewHolder
import com.example.coinmarketcap.databinding.FiatCurrenciesItemBinding
import com.example.coinmarketcap.databinding.StoriesItemBinding

class StoriesAdapter :  RecyclerView.Adapter<StoriesViewHolder>() {

    val storyList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder =
        StoriesViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.stories_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = storyList.size
}

class StoriesViewHolder(binding: StoriesItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
