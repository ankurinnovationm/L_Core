package core2.maz.com.core2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import core2.maz.com.core2.R;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class EdgeFragment extends Fragment {
    private Button next ;
    private int sectionIdentifier;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edge_fragment_layout, container, false);
        sectionIdentifier = ((BaseFragment)getParentFragment()).sectionIdentifier;
        next = (Button) view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // ((BaseFragment) getParentFragment()).replaceFragment(AppConstants.KEY_LIST_FRAGMENT,sectionIdentifier);

            }
        });


        return view;
    }
}
