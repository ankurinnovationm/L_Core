package core2.maz.com.core2.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import core2.maz.com.core2.R;
import core2.maz.com.core2.managers.CachingManager;
import core2.maz.com.core2.model.Feed;

/**
 * Created by Ankur Jain on 24-11-2016.
 */
public class SettingActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Feed feed = CachingManager.getAppFeed();
        setContentView(R.layout.setting_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbarContainer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(feed.getPrimaryColor())));
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor(feed.getPrimaryColor()));



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
