package com.example.fedom.weatherproject.Fragments_NOT_FINISHED;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fedom.weatherproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoCity extends Fragment {
    private ListView cityList;

    public FragmentInfoCity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_info_city, container, false);
        cityList=(ListView)view.findViewById(R.id.list_view_city);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //start connection with the  new Activity
            }
        });
        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //here we are going to delete or to edit thing
                return false;
            }
        });



        return view;
    }

}
