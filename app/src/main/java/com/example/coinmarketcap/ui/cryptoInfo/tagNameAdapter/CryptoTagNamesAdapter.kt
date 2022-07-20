package com.example.coinmarketcap.ui.cryptoInfo.tagNameAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmarketcap.R
import com.example.coinmarketcap.databinding.CryptoTagNamesItemBinding

class CryptoTagNamesAdapter : RecyclerView.Adapter<TagNamesHolder>() {

    private var cryptoTagNames = ArrayList<String>()

    fun setList(cryptoTagNames: List<String>?) {
        if (cryptoTagNames != null) {
            this.cryptoTagNames.addAll(cryptoTagNames)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagNamesHolder =
        TagNamesHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.crypto_tag_names_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: TagNamesHolder, position: Int) {

        holder.binding.cryptoTagNames.text = cryptoTagNames[position]

    }

    override fun getItemCount(): Int = cryptoTagNames.size
}

class TagNamesHolder(binding: CryptoTagNamesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    var binding = binding

}
