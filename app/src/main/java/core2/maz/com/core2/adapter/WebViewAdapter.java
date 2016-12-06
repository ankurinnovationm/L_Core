package core2.maz.com.core2.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import core2.maz.com.core2.fragments.AdFragment;
import core2.maz.com.core2.fragments.WebViewFragment;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 01-07-2016.
 */
public class WebViewAdapter extends FragmentStatePagerAdapter
{
    private ArrayList<Menu> menus;
    private Menu menu;
    private HashMap<Integer,Fragment> FragmentList;
    public WebViewAdapter(FragmentManager fm,ArrayList<Menu> menus ,Menu menu)
    {
        super(fm);
        this.menus = menus;
        this.menu = menu;
        FragmentList = new HashMap<Integer, Fragment>() ;
    }

    @Override
    public Fragment getItem(int position) {

        Menu menu = menus.get(position);
        Fragment webViewFragment = startNewWebViewFragment(menu);
        FragmentList.put(position,webViewFragment);
        return webViewFragment;
    }

    @Override
    public int getCount()
    {
        return menus.size();
    }

    private Fragment startNewWebViewFragment(Menu menu)
    {
        Bundle bundle =new Bundle();
        bundle.putSerializable("menu", menu);
        Fragment  fragment = null;
        if(menu.getType().equalsIgnoreCase("article"))
        {
            fragment  = new WebViewFragment();
            fragment.setArguments(bundle);
        }
        else
        {
            fragment = new AdFragment();
            fragment.setArguments(bundle);
        }
        return fragment;

    }
    public void destroyItem (ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        FragmentList.remove(position);
    }

    public Fragment getCurrentFragment(int key)
    {
        return FragmentList.get(key);
    }
}
