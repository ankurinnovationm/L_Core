package core2.maz.com.core2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.adapter.WebViewAdapter;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 29-11-2016.
 */
public class WebViewActivity extends AppCompatActivity {

    private ArrayList<Menu> menus ;
    private ViewPager viewPager ;
    private Menu menu;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        Intent intent = getIntent();
        menus = (ArrayList<Menu>) intent.getSerializableExtra("menus");
        position = intent.getIntExtra("position",0);
        menu = menus.get(position);
        initializeView();

    }

    private void initializeView()
    {
        viewPager = (ViewPager) findViewById(R.id.web_pager);
    }

    private void setAdapter()
    {
        WebViewAdapter adapter = new WebViewAdapter(getSupportFragmentManager(),menus,menu);
        viewPager.setAdapter(adapter);
    }
}
