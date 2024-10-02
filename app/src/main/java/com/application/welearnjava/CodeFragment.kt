package com.application.welearnjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.databinding.FragmentCodeBinding

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using the binding
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
        binding.webView.loadUrl("https://onecompiler.com/java")

        // Disable vertical scrolling
        binding.webView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                return@setOnTouchListener true // Consume the event to prevent scrolling
            }
            false
        }

        // Handle back button
        binding.back.setOnClickListener {
            findNavController().popBackStack() // Go back to the previous fragment
        }

        return binding.root
    }
}
