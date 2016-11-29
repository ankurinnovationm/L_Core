package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import core2.maz.com.core2.R;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class CardFragment extends Fragment {

    public static CardFragment cardFragment ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_fragment_layout,container,false);
        Bundle  bundle = new Bundle();

        return view;
    }


}
