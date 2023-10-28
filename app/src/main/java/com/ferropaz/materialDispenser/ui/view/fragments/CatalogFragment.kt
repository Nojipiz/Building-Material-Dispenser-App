package com.ferropaz.materialDispenser.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.ferropaz.materialDispenser.databinding.FragmentCatalogBinding

class CatalogFragment: Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    companion object{
        private const val BASE_URL = "https://ferropaz.com/index.php?id_category=15&controller=category"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val viewR = binding.root
        componentsInit()
        return viewR
    }

    private fun componentsInit(){
        // WebView
        binding.catalogView.loadUrl(BASE_URL)
        binding.catalogView.webViewClient = WebViewClient();
    }
}