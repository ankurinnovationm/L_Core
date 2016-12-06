package core2.maz.com.core2.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.InputStream;

import core2.maz.com.core2.R;
import core2.maz.com.core2.model.Menu;
import core2.maz.com.core2.utills.CustomViewPager;

/**
 * Created by Ankur Jain on 29-11-2016.
 */
public class WebViewFragment extends Fragment {

    private WebView webView;
    private ProgressBar progress;
    private Menu  menu;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.webview_fragment_layout,container,false);
        menu = (Menu) getArguments().getSerializable("menu");
        url = menu.getContentUrl();
        initializeView(view);


        return view;
    }

    private void initializeView(View view)
    {
        webView = (WebView) view.findViewById(R.id.webView);
        progress = (ProgressBar) view.findViewById(R.id.progressBar);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebClient());
        webView.setWebChromeClient(new WebViewChromeClient());

        progress.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        progress.setScaleY(1f);
        progress.setMax(100);
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setBuiltInZoomControls(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setVerticalScrollBarEnabled(true);
        if(url!=null)
            webView.addJavascriptInterface(new JavaScriptInterface(getActivity()), "JSInterface");
        webView.loadUrl(url);
       // injectCSS();

    }


    private class WebClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }


            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url)
        {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {

            super.onPageCommitVisible(view, url);
            injectCSS();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);

        }
    }

    private class WebViewChromeClient extends WebChromeClient
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            WebViewFragment.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);

        }
    }


    public void setValue(int progress)
    {
        this.progress.setProgress(progress);
    }

    public class JavaScriptInterface {
        private Activity activity;

        public JavaScriptInterface(Activity activiy) {
            this.activity = activiy;
        }

        @JavascriptInterface
        public void startGallery()
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CustomViewPager viewpager = (CustomViewPager) getActivity().findViewById(R.id.web_pager);
                    viewpager.setPagingEnabled(false);

                }
            });

        }
        @JavascriptInterface
        public void closeGallery()
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CustomViewPager viewpager = (CustomViewPager) getActivity().findViewById(R.id.web_pager);
                    viewpager.setPagingEnabled(true);

                }
            });

        }
    }

    private void injectCSS() {
        try
        {
            InputStream inputStream = getActivity().getAssets().open("style.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            webView.loadUrl("javascript:(function() {" +
                    "l=document.getElementsByClassName('viewGal')[0];"+
                    "l.addEventListener('click',function() {window.JSInterface.startGallery();}); "+
                    "c=document.getElementsByClassName('xBar')[0];"+
                    "c.addEventListener('click',function() {window.JSInterface.closeGallery();}); "+
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    " "+
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
