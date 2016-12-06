package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.adapter.CardFragmentAdapter;
import core2.maz.com.core2.managers.CardViewCenterLayoutManager;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class CardFragment extends Fragment {

    public static CardFragment cardFragment ;
    private ArrayList<Menu> menus;
    private RecyclerView recyclerView;
    private String title ;
    private int sectionIdentifier;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        sectionIdentifier = ((BaseFragment)getParentFragment()).sectionIdentifier;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        menus = (ArrayList<Menu>) getArguments().getSerializable("list");
        title = getArguments().getString("title");

        if(menus!=null && !menus.isEmpty())
        {
            if(getActivity()!=null)
            {
                CardFragmentAdapter adapter = new CardFragmentAdapter(getActivity(),menus);
                CardViewCenterLayoutManager cardViewCenterLayoutManager = new CardViewCenterLayoutManager(getActivity());
                recyclerView.setLayoutManager(cardViewCenterLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        }



        return view;
    }


}
