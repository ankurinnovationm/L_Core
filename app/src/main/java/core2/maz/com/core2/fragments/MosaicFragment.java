package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class MosaicFragment extends Fragment {

    private ArrayList<Menu> menus;
    ListView  listView;
//    ArrayList<Menu> myList = CachingManager.getAppFeed().getSections();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mosaic_fragment_layout,container,false);
       // listView = (ListView) view.findViewById(R.id.listView);
        //menus = (ArrayList<Menu>) getArguments().getSerializable("list");

       // ListViewAdapter adapter = new ListViewAdapter(menus,getActivity());
       // listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Menu menu = menus.get(position);
                if (!menu.getType().equals("vid"))
                {
                    ArrayList<Menu> menus = AppFeedManager.getMenus(MosaicFragment.this.menus.get(position).getIdentifier());
                    bundle.putSerializable("list", menus);
                    MosaicFragment mosaicFragment = new MosaicFragment();
                    mosaicFragment.setArguments(bundle);
                    getParentFragment().getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, mosaicFragment, "").addToBackStack("ff").commit();
                }
            }
        });*/


        return view;
    }
}
