package net.cattaka.android.simplewebbrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true

        val defaultUserAgent = WebSettings.getDefaultUserAgent(this)
        webView.settings.userAgentString = "$defaultUserAgent [UbieApp/Android]"

        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(this@MainActivity)
                        .setTitle(webView.title)
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok) { dialog, which -> result.cancel() }
                        .create()
                        .show()
                return true
            }

            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(this@MainActivity)
                        .setTitle(webView.title)
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok) { dialog, which -> result.confirm() }
                        .setNegativeButton(android.R.string.cancel) { dialog, which -> result.cancel() }
                        .create()
                        .show()

                return true
            }
        }
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        webView.loadUrl("https://www.google.com/")
    }
}
