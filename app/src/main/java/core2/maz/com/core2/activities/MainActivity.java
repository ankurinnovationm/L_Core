package core2.maz.com.core2.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import core2.maz.com.core2.R;
import core2.maz.com.core2.constants.AppConstants;
import core2.maz.com.core2.fragments.BaseFragment;
import core2.maz.com.core2.fragments.ViewPagerAdapter;
import core2.maz.com.core2.managers.AppFeedManager;
import core2.maz.com.core2.managers.CachingManager;
import core2.maz.com.core2.model.Feed;
import core2.maz.com.core2.model.Menu;
import core2.maz.com.core2.utills.UiUtil;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String ankur;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    private AQuery aQuery;
    public HashMap<Integer, Stack<Fragment>> mStacks;
    public List<Fragment> sectionFragment;
    private CoordinatorLayout coordinatorLayout;
    private Feed feed = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeView()
    {
        aQuery = new AQuery(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (AppFeedManager.myMap != null && !AppFeedManager.myMap.isEmpty())
        {
            onSuccessGetAppFeedAsynkTask();

        }
        else
        {
           // new GetAppFeedAsynkTask().execute();
        }
    }


    public class GetAppFeedAsynkTask extends AsyncTask<Void, Void, Void> {
        Dialog dialog;
        Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = UiUtil.showProgressDialog(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                AppFeedManager.refreshFeed();
            } catch (Exception exception) {
                exception = exception;
                exception.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            UiUtil.dismissDialog(dialog);
            if (exception == null) {
                onSuccessGetAppFeedAsynkTask();
            } else {

            }

        }
    }

    private void onSuccessGetAppFeedAsynkTask() {
        ArrayList<Menu> sections = CachingManager.getSections();
        feed = CachingManager.getAppFeed();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(feed.getPrimaryColor())));
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor(feed.getPrimaryColor()));
        coordinatorLayout.setBackgroundColor(Color.parseColor(feed.getPrimaryColor()));
        tabLayout.setBackgroundColor(Color.parseColor(feed.getPrimaryColor()));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor(feed.getSecondaryColor()));
        prepareSection(sections);

    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    public void onBackPressed() {

        int position = tabLayout.getSelectedTabPosition();

        if(mStacks.get(position).size()>1)
        {
            Fragment fragment =   mStacks.get(position).elementAt(mStacks.get(position).size() - 2);
            String title =  fragment.getArguments().getString("title");
            setTitle(title);
            mStacks.get(position).pop();
            ((BaseFragment)sectionFragment.get(position)).replaceFragment(fragment);
        }
        else
        {
            super.onBackPressed();
        }

    }

    private void prepareSection(final ArrayList<Menu> sections)
    {
        mStacks = new HashMap<>();
        int count =0;

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        sectionFragment = new ArrayList<>();
        for(Menu menu : sections)
        {
            BaseFragment baseFragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", AppConstants.KEY_EDGE_FRAGMENT);
            bundle.putInt("section_identifier", count);
            bundle.putSerializable("menu", menu);
            baseFragment.setArguments(bundle);
            sectionFragment.add(baseFragment);
            mStacks.put(count, new Stack<Fragment>());
            adapter.addFragment(baseFragment, null);
            count++;

        }
        viewPager.setAdapter(adapter);
        setupTabIcons(sections);

    }
    private void setupTabIcons(ArrayList<Menu> sections) {
        int count =0;
        for(Menu menu : sections)
        {
            String sectionTitle = menu.getTitle();
            View tabOne = (View) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            TextView tabText = (TextView) tabOne.findViewById(R.id.tab);
            ImageView tabIcon = (ImageView) tabOne.findViewById(R.id.tabIcon);
            String url = prepareTabUrl(menu.getImage());
            aQuery.id(tabIcon).image(url);
            tabText.setText(sectionTitle);
            tabLayout.getTabAt(count).setCustomView(tabOne);
            count++;

        }


    }

    private String prepareTabUrl(String url) {
        StringBuilder stringBuilder = null;
        if (null != url && url.length() > 0) {
            int endIndex = url.lastIndexOf(".");
            if (endIndex != -1) {
                stringBuilder = new StringBuilder(url);
                stringBuilder.insert(endIndex, "-xxhdpi");
            }
        }
        return stringBuilder.toString();
    }
}
