package com.example.coinmarketcap.ui.cryptoWebView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinmarketcap.databinding.FragmentUrlWebViewBinding

class CryptoWebView : Fragment() {

    private lateinit var binding: FragmentUrlWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUrlWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var url = arguments?.get("url").toString()

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)
    }
}