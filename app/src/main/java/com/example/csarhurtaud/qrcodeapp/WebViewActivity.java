package com.example.csarhurtaud.qrcodeapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by César Hurtaud on 07/03/2018.
 */

public class WebViewActivity extends Activity{

    private WebView webView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);

        webView = findViewById(R.id.webview_activity);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        //code de chargement de l'URL
        String url = getIntent().getStringExtra("message");
        webView.loadUrl(url);

    }

}
