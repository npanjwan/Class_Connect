/**
 * HomeWebActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Home;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnit425_final_project.R;

public class HomeWebActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        webView = findViewById(R.id.webview);

        String webName = getIntent().getStringExtra("web_name");

        setTitle(webName);

        webView.setWebViewClient(new WebViewClient());

        if(webName.equals("Brightspace")){
            webView.loadUrl("https://purdue.brightspace.com/d2l/login");
        } else if(webName.equals("MyPurdue")){
            webView.loadUrl("https://wl.mypurdue.purdue.edu");
        } else if(webName.equals("Directory")){
            webView.loadUrl("https://www.purdue.edu/directory/");
        } else if(webName.equals("Campus Map")){
            webView.loadUrl("https://www.purdue.edu/campus-map/");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
