package com.example.csarhurtaud.qrcodeapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by César Hurtaud on 07/03/2018.
 */

public class WebViewActivity extends Activity{

    private WebView webView;
    private TextView text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);

        webView = findViewById(R.id.webview_activity);
        webView.getSettings().setJavaScriptEnabled(true);

        text = findViewById(R.id.text_url);


        //code de chargement de l'URL
        String url = getIntent().getStringExtra("message");
        webView.loadUrl(url);

        text.setText(url);



    }

}
