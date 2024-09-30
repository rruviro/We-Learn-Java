package com.application.welearnjava

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.databinding.FragmentCodeBinding

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fix: Attach view later, passing false as the third argument
        binding = FragmentCodeBinding.inflate(inflater, container, false)

        // Configure WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            displayZoomControls = false
            builtInZoomControls = true
            setSupportZoom(true)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cacheMode = WebSettings.LOAD_NO_CACHE
            userAgentString = "Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.96 Mobile Safari/537.36"
        }

        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://www.jdoodle.com/")

        // Fix: Use the correct navigation method to go back
        binding.back.setOnClickListener {
            findNavController().popBackStack() // Go back to the previous fragment
        }

        return binding.root
    }
}
