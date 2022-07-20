package com.example.coinmarketcap.ui.listingAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinmarketcap.R
import com.example.coinmarketcap.ui.listingAll.cryptoAssetAdapter.CryptoAssetAdapter
import com.example.coinmarketcap.adapter.FiatCurrenciesAdapter
import com.example.coinmarketcap.data.models.CurrencyData
import com.example.coinmarketcap.data.models.Data
import com.example.coinmarketcap.data.responseUtil.Status
import com.example.coinmarketcap.databinding.FragmentListingAllBinding
import com.example.coinmarketcap.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListingAll : Fragment(), CryptoAssetAdapter.CallbackCryptoInterface {

    private lateinit var binding: FragmentListingAllBinding
    lateinit var listingAllViewModel: ListingAllViewModel
    private lateinit var adapter: CryptoAssetAdapter
    private lateinit var fiatAdapter: FiatCurrenciesAdapter
    lateinit var assetsList: ArrayList<Data>
    lateinit var currenciesList: ArrayList<CurrencyData>
    var status = 0

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListingAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.combineSearchView.clearFocus()
        binding.combineSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (status == 0) {
                    filterList(newText)
                } else if (status == 1) {
                    filterCurrenciesList(newText)
                }
                return true
            }

        })

        listingAllViewModel = ViewModelProvider(this, factory)[ListingAllViewModel::class.java]
        setActive(status)
        clicks()
    }

    private fun filterCurrenciesList(text: String?) {
        val filteredCurrenciesList = ArrayList<CurrencyData>()

        for (item in currenciesList) {
            if (item.name.lowercase().contains(text!!.lowercase())) {
                filteredCurrenciesList.add(item)
            }
        }
        if (filteredCurrenciesList.isEmpty()) {
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
        } else {
            fiatAdapter.setFilteredList(filteredCurrenciesList)
            fiatAdapter.notifyDataSetChanged()
        }
    }

    private fun filterList(text: String?) {

        val filteredList = ArrayList<Data>()

        for (item in assetsList) {
            if (item.name.lowercase().contains(text!!.lowercase())) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
        } else {
            adapter.setFilteredList(filteredList)
            adapter.notifyDataSetChanged()
        }
    }

    private fun clicks() {

        binding.combineTvCrypto.setOnClickListener {
            status = 0
            setActive(status)
        }

        binding.combineTvExchange.setOnClickListener {
            status = 1
            setActive(status)
        }

        binding.combineTvSectors.setOnClickListener {
            status = 2
            setActive(status)
        }
    }

    private fun setActive(status: Int) {
        when (status) {

            0 -> {
                binding.combineSearchView.visibility = View.VISIBLE
                binding.combineRecyclerView.visibility = View.VISIBLE
                binding.combineSearchView.queryHint = "Search crypto assets"
                binding.combineTvSectors.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_grey
                    )
                )

                binding.combineTvExchange.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_grey
                    )
                )

                binding.combineTvCrypto.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                binding.combineTvCrypto.textSize = 20F
                binding.combineTvExchange.textSize = 14F
                binding.combineTvSectors.textSize = 14F
                initCryptoAssetRecyclerView()
            }

            1 -> {
                binding.combineSearchView.visibility = View.VISIBLE
                binding.combineRecyclerView.visibility = View.VISIBLE
                binding.combineSearchView.queryHint = "Search fiat currencies"
                binding.combineTvCrypto.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_grey
                    )
                )

                binding.combineTvSectors.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_grey
                    )
                )

                binding.combineTvExchange.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.combineTvCrypto.textSize = 14F
                binding.combineTvExchange.textSize = 20F
                binding.combineTvSectors.textSize = 14F
                initFiatCurrenciesRecyclerView()
            }

            2 -> {
                binding.combineSearchView.visibility = View.GONE
                binding.combineRecyclerView.visibility = View.GONE
                binding.combineTvCrypto.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_grey
                    )
                )
                binding.combineTvExchange.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_grey
                    )
                )
                binding.combineTvSectors.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                binding.combineTvCrypto.textSize = 14F
                binding.combineTvExchange.textSize = 14F
                binding.combineTvSectors.textSize = 20F


            }
        }
    }

    private fun initCryptoAssetRecyclerView() {
        binding.combineRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CryptoAssetAdapter(requireContext())
        binding.combineRecyclerView.adapter = adapter
        displayCryptoAssetListing()
    }

    private fun displayCryptoAssetListing() {

        listingAllViewModel.fetchLatestCryptoAsset().observe(viewLifecycleOwner, Observer {

            when (it.status) {
                Status.LOADING -> {
                    binding.combineProgressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.combineProgressBar.visibility = View.GONE
                    assetsList = ArrayList(it.data?.body()?.data)
                    adapter.setList(it?.data?.body()?.data,this)
                    adapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.combineProgressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun initFiatCurrenciesRecyclerView() {
        binding.combineRecyclerView.layoutManager = LinearLayoutManager(context)
        fiatAdapter = FiatCurrenciesAdapter(requireContext())
        binding.combineRecyclerView.adapter = fiatAdapter
        displayFiatCurrencyListing()
    }

    private fun displayFiatCurrencyListing() {
        listingAllViewModel.fetchFiatCurrencies().observe(this, Observer {

            when (it.status) {
                Status.LOADING -> {
                    binding.combineProgressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.combineProgressBar.visibility = View.GONE
                    currenciesList = ArrayList(it.data?.body()?.data)
                    fiatAdapter.setList(it.data?.body()?.data)
                    fiatAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.combineProgressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun passResultCallback(id: String) {
        var bundle = bundleOf("id" to id)
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_combineAll_to_cryptoInfo, bundle)
    }
}