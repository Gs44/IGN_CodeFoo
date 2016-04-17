package com.garygrossi.androidcodefoo3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by Gary on 4/16/2016.
 * An activity for loading the content of an article in a WebView
 */
public class WebViewActivity extends Activity {
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage_layout);

        webView = (WebView) findViewById(R.id.webpageView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                TextView textView = (TextView) findViewById(R.id.loadText);
                textView.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        webView.loadUrl(intent.getExtras().getString("articleURL"));
    }
}
