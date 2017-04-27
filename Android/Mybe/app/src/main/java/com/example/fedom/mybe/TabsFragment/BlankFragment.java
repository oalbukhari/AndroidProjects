package com.example.fedom.mybe.TabsFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fedom.mybe.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    TextView textchange;


    public interface IButtonFragmentListener {
        void onErrorPressed();

    }

    private IButtonFragmentListener listener = new IButtonFragmentListener() {
        @Override
        public void onErrorPressed() {

        }

    };

    public static BlankFragment newInstance() {
        BlankFragment buttonFragment = new BlankFragment();
        return buttonFragment;
    }




    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IButtonFragmentListener) {
            listener = (IButtonFragmentListener) activity;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_blank, container, false);

        textchange=(TextView) view.findViewById(R.id.textToChange);


        Button vBtn = (Button) view.findViewById(R.id.buttonChange);
        vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogError();
            }
        });






        return view;


    }



    void dialogError (){
        listener.onErrorPressed();
    }

}

