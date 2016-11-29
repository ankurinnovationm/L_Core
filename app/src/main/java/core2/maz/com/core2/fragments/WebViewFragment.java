package core2.maz.com.core2.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import core2.maz.com.core2.R;
import core2.maz.com.core2.model.Menu;

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
        webView.loadUrl(url);
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
}
