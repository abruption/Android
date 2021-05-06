package com.inhatc.android_webview;

import android.view.KeyEvent;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView objWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objWebView = (WebView) findViewById(R.id.webView1);
        objWebView.setWebViewClient(new Android_WebViewClient());       // Modify code
        objWebView.getSettings().setJavaScriptEnabled(true);
        objWebView.loadUrl("https://www.google.com");
    }

    private class Android_WebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            int KeyCode = event.getKeyCode();
            if((KeyCode == KeyEvent.KEYCODE_DPAD_LEFT) && objWebView.canGoBack()){
                objWebView.goBack();
                return true;
            }
            else if((KeyCode == KeyEvent.KEYCODE_DPAD_RIGHT) && objWebView.canGoForward()){
                objWebView.goForward();
                return true;
            }
            return false;
        }
    }
}

