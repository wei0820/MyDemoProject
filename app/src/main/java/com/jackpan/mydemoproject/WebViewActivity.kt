package com.jackpan.mydemoproject

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient



class WebViewActivity : AppCompatActivity() {
    lateinit var mWebView: WebView
    lateinit var url: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        mWebView = findViewById(R.id.WebView)
        initWebViewSettings()
        getData()




    }
    fun getData(){
        url = intent.extras.getString("Url").replace("http","https")
        Log.d("Jack",url)


        mWebView.loadUrl(url)



    }


    private fun initWebViewSettings() {
        //获取一个webviewsetting对象
        val setting = mWebView!!.getSettings()
        setting.javaScriptEnabled = true
        //显示缩放控制工具
        setting.displayZoomControls = false
        //设置webview支持缩放
        setting.setSupportZoom(true)
        setting.builtInZoomControls = true
        //设置加载进来的页面自适应手机屏幕
        setting.useWideViewPort = true
        setting.loadWithOverviewMode = true
    }


}
