package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import core2.maz.com.core2.R;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class CarouselFragment extends Fragment {


    protected ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.carousel_fragment_layout,container,false);
        return view;
    }

}
