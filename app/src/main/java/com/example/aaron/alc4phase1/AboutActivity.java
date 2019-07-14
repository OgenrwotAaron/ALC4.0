package com.example.aaron.alc4phase1;

import android.content.Intent;
import android.net.http.SslError;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar ab=getSupportActionBar();
        ab.setElevation(0);
        ab.setDisplayHomeAsUpEnabled(true);

        WebView myWebView=findViewById(R.id.myweb_view);
        myWebView.setWebViewClient(new MyBrowser());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.loadUrl("https://andela.com/alc/");
    }

    private class MyBrowser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            view.loadUrl(String.valueOf(request));
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            switch (error.getPrimaryError()){
                case SslError.SSL_UNTRUSTED:
                    Log.d("SslError","The certificate is not trusted");
                    break;
                case SslError.SSL_EXPIRED:
                    Log.d("Ssl Error","Certificate has expired");
                    break;
                case SslError.SSL_IDMISMATCH:
                    Log.d("MisMatch","The certificate hostname mismatch");
                    break;
                case SslError.SSL_NOTYETVALID:
                    Log.d("NotYetValid","The certificate is not yet valid");
                    break;
            }

            handler.proceed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(AboutActivity.this,WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
                default:
                    return onOptionsItemSelected(item);
        }
    }
}
