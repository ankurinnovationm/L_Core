package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import core2.maz.com.core2.R;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 05-12-2016.
 */
public class AdFragment extends Fragment {

    private Menu menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view  = inflater.inflate(R.layout.ad_fragment_layout,container,false);
        menu = (Menu) getArguments().getSerializable("menu");

        ImageView imageView = (ImageView) view.findViewById(R.id.adImage);
        Picasso.with(getActivity()).load(menu.getImage()).into(imageView);
        return view;
    }
}
