package com.example.coinmarketcap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinmarketcap.R
import com.example.coinmarketcap.databinding.FiatCurrenciesItemBinding
import com.example.coinmarketcap.data.models.CurrencyData

class FiatCurrenciesAdapter(private val context: Context) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    private var fiatCurrenciesList = ArrayList<CurrencyData>()

    fun setList(fiatCurrenciesList: List<CurrencyData>?) {
        if (fiatCurrenciesList != null) {
            this.fiatCurrenciesList.addAll(fiatCurrenciesList)
        }
    }

    fun setFilteredList(fiatCurrenciesList: List<CurrencyData>?){
        this.fiatCurrenciesList.clear()
        if (fiatCurrenciesList != null) {
            this.fiatCurrenciesList.addAll(fiatCurrenciesList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.fiat_currencies_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {

        Glide.with(context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + fiatCurrenciesList[position].id + ".png")
            .into(holder.binding.fiatListLogo)
        holder.binding.fiatListName.text = fiatCurrenciesList[position].name
        holder.binding.fiatListSign.text = fiatCurrenciesList[position].sign
        holder.binding.fiatListSymbol.text = fiatCurrenciesList[position].symbol

    }

    override fun getItemCount(): Int = fiatCurrenciesList.size
}

class CurrencyViewHolder(binding: FiatCurrenciesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val binding = binding
}
