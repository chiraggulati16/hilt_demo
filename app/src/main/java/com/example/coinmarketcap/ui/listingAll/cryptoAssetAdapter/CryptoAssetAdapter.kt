package com.example.coinmarketcap.ui.listingAll.cryptoAssetAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinmarketcap.R
import com.example.coinmarketcap.databinding.CryptoAssetItemBinding
import com.example.coinmarketcap.data.models.Data

class CryptoAssetAdapter(private val context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    private var cryptoAssetList = ArrayList<Data>()
    interface CallbackCryptoInterface {
        fun passResultCallback(id: String)
    }

    lateinit var callbackInterface: CallbackCryptoInterface

    fun setList(cryptoAssetList: List<Data>?, callbackInterface: CallbackCryptoInterface) {
        if (cryptoAssetList != null) {
            this.cryptoAssetList.addAll(cryptoAssetList)
            this.callbackInterface = callbackInterface
            notifyDataSetChanged()
        }
    }

    fun setFilteredList(cryptoAssetList: List<Data>?) {
        this.cryptoAssetList.clear()
        if (cryptoAssetList != null) {
            this.cryptoAssetList.addAll(cryptoAssetList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.crypto_asset_item, parent, false
        )
    )

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (cryptoAssetList[position].quote.USD.percent_change_24h < 0){
            holder.binding.arrow.setImageResource(R.drawable.down_arrow_red)
        } else {
            holder.binding.arrow.setImageResource(R.drawable.up_arrow_green)
        }

        Glide.with(context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + cryptoAssetList[position].id + ".png")
            .into(holder.binding.cryptoLogoImage)
        holder.binding.name.text = cryptoAssetList[position].name
        holder.binding.symbol.text = cryptoAssetList[position].symbol
        holder.binding.cmcRank.text = cryptoAssetList[position].cmc_rank.toString()
        holder.binding.price.text =
            String.format("USD%.3f", cryptoAssetList[position].quote.USD.price)
        holder.binding.percentChange1h.text =
            String.format("%.2f", cryptoAssetList[position].quote.USD.percent_change_24h) + "%"

        holder.binding.cryptoAssetLayout.setOnClickListener {
            callbackInterface.passResultCallback(cryptoAssetList[position].id.toString())
        }

    }

    override fun getItemCount(): Int = cryptoAssetList.size
}

class MyViewHolder(binding: CryptoAssetItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val binding = binding
}
