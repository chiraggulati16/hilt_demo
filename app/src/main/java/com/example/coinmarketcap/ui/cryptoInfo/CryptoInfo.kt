package com.example.coinmarketcap.ui.cryptoInfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coinmarketcap.R
import com.example.coinmarketcap.ui.cryptoInfo.tagNameAdapter.CryptoTagNamesAdapter
import com.example.coinmarketcap.databinding.CryptoInfoFragmentBinding
import com.example.coinmarketcap.data.responseUtil.Status
import com.example.coinmarketcap.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CryptoInfo : Fragment() {

    private lateinit var cryptoInfoViewModel: CryptoInfoViewModel
    private lateinit var binding: CryptoInfoFragmentBinding
    private lateinit var tagNames: ArrayList<String>
    private lateinit var cryptoTagNamesAdapter: CryptoTagNamesAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CryptoInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cryptoInfoBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        cryptoInfoViewModel = ViewModelProvider(this, factory)[CryptoInfoViewModel::class.java]
        val id = arguments?.getString("id")

        if (id != null) {
            cryptoInfoViewModel.fetchCryptoInfo(id).observe(viewLifecycleOwner, Observer {

                when (it.status) {
                    Status.LOADING -> {
                        binding.cryptoInfoProgressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        val response = it.data?.body()?.cryptoInfoData
                        binding.cryptoInfoProgressBar.visibility = View.GONE

                        for (items in response?.keys!!) {
                            Glide.with(requireContext())
                                .load(response[items]?.logo)
                                .into(binding.cryptoInfoLogo)

                            binding.cryptoInfoHead.text = response[items]?.name
                            binding.cryptoInfoName.text = response[items]?.name
                            binding.cryptoInfoSymbol.text = response[items]?.symbol
                            binding.cryptoInfoCategory.text = "[" + response[items]?.category + "]"
                            binding.cryptoInfoDesc.text = response[items]?.description
                            tagNames = ArrayList(response[items]!!.tagNames)
                            if(response[items]?.urls?.website?.isEmpty() == true){
                                binding.cryptoInfoWebSite.visibility = View.GONE
                            } else {
                                binding.cryptoInfoWebSite.text = response[items]?.urls?.website?.get(0)
                            }
                           if (response[items]?.urls?.message_board?.isEmpty() == true){
                               binding.cryptoInfoMessLink.visibility = View.GONE
                           }
                           else {
                               binding.cryptoInfoMessLink.text =
                                   response[items]?.urls?.message_board?.get(0)
                           }
                        }
                        initializeCryptoInfoRecycler()
                    }
                    Status.ERROR -> {
                        binding.cryptoInfoProgressBar.visibility = View.GONE
                        Log.d("Error", "onViewCreated: " + it.message)
                    }
                }
            })
        }

        binding.cryptoInfoWebSite.setOnClickListener {
            val url = bundleOf("url" to binding.cryptoInfoWebSite.text)
            Navigation.findNavController(view).navigate(R.id.action_cryptoInfo_to_urlWebView, url)
        }

        binding.cryptoInfoMessLink.setOnClickListener {
            val url = bundleOf("url" to binding.cryptoInfoMessLink.text)
            Navigation.findNavController(view).navigate(R.id.action_cryptoInfo_to_urlWebView, url)
        }
    }

    private fun initializeCryptoInfoRecycler() {
        binding.cryptoInfoRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cryptoTagNamesAdapter = CryptoTagNamesAdapter()
        binding.cryptoInfoRecycler.adapter = cryptoTagNamesAdapter
        cryptoTagNamesAdapter.setList(tagNames)
        cryptoTagNamesAdapter.notifyDataSetChanged()
    }
}