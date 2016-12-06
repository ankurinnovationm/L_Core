package core2.maz.com.core2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.adapter.WebViewAdapter;
import core2.maz.com.core2.managers.AppFeedManager;
import core2.maz.com.core2.managers.CachingManager;
import core2.maz.com.core2.model.Feed;
import core2.maz.com.core2.model.Menu;
import core2.maz.com.core2.utills.CustomViewPager;

/**
 * Created by Ankur Jain on 29-11-2016.
 */
public class WebViewActivity extends AppCompatActivity {

    private ArrayList<Menu> menus ;
    private CustomViewPager viewPager ;
    private Menu menu;
    private int position;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        Intent intent = getIntent();
        menus = (ArrayList<Menu>) intent.getSerializableExtra("menus");
        position = intent.getIntExtra("position", 0);
        menu = menus.get(position);
        setTitle(AppFeedManager.getParent(menus.get(0).getIdentifier()).getTitle());

        initializeView();

    }

    private void initializeView()
    {
        viewPager = (CustomViewPager) findViewById(R.id.web_pager);
        if(menus!=null && !menus.isEmpty())
        {
            setAdapter();
        }
    }

    private void setAdapter()
    {
        WebViewAdapter adapter = new WebViewAdapter(getSupportFragmentManager(),menus,menu);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Feed feed = CachingManager.getAppFeed();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(feed.getPrimaryColor())));
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor(feed.getPrimaryColor()));
    }



}
