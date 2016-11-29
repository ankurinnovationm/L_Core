package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.adapter.ListViewAdapter;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class ListFragment extends Fragment {
    private int sectionIdentifier;
    private RecyclerView recyclerView;
    private ArrayList<Menu> menus;
    private String title ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.e("CreateVIEW","CreateView");
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        sectionIdentifier = ((BaseFragment)getParentFragment()).sectionIdentifier;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        menus = (ArrayList<Menu>) getArguments().getSerializable("list");
        title = getArguments().getString("title");
        ListViewAdapter listViewAdapter = new ListViewAdapter(menus,getActivity(),this,sectionIdentifier);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listViewAdapter);

        return view;
    }



}
