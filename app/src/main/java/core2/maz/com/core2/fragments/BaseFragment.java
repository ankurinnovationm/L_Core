package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.activities.MainActivity;
import core2.maz.com.core2.constants.AppConstants;
import core2.maz.com.core2.managers.AppFeedManager;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 21-11-2016.
 */
public class BaseFragment extends Fragment {

    public int sectionIdentifier;
    private Menu menu;
    private boolean isVisibleToUser = false;
    public  String title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.base_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        menu = (Menu) getArguments().getSerializable("menu");
        sectionIdentifier = getArguments().getInt("section_identifier");

        if(savedInstanceState==null)
        {
          //  getActivity().setTitle(menu.getTitle());
            if (menu != null)
            {
                if (!menu.getType().equalsIgnoreCase("vid"))
                {
                    ArrayList<Menu> menus = AppFeedManager.getMenus(menu.getIdentifier());
                    replaceFragment(menu.getTitle(),sectionIdentifier, menus);
                }
                else
                {

                }
            }
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser)
        {
            this.isVisibleToUser = isVisibleToUser;
            if (menu != null)
            {
                this.isVisibleToUser =isVisibleToUser;
                getActivity().setTitle(menu.getTitle());
            }
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    public void replaceFragment(String title,int sectionIdentifier,ArrayList<Menu> menus)
    {

        Bundle bundle = new Bundle();
        this.title = title;
        if(isVisibleToUser)
            getActivity().setTitle(title);
        bundle.putSerializable("list", menus);
        bundle.putString("title",title);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);
        FragmentManager fragmentManager = getChildFragmentManager();
        // Fragment fragment = inflateFragment(fragmentIdentifier);
        ((MainActivity) getActivity()).mStacks.get(sectionIdentifier).push(listFragment);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, listFragment).commit();
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }



    private Fragment inflateFragment(String fragmentName)
    {
        Fragment fragment = null;
        switch (fragmentName)
        {
            case AppConstants.KEY_CARD_FRAGMENT:
                fragment = new CardFragment();
                break;

            case AppConstants.KEY_CAROUSEL_FRAGMENT:
                fragment = new CarouselFragment();

                break;
            case  AppConstants.KEY_EDGE_FRAGMENT:
                fragment = new EdgeFragment();
                break;
            case AppConstants.KEY_LIST_FRAGMENT:
                fragment = new ListFragment();
                break;
            case AppConstants.KEY_MOSAIC_FRAGMENT:
                fragment = new MosaicFragment();
                break;

        }
        return fragment;
    }
}
