package com.example.fedom.maybeprojectwork.FragmentActivities;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ActionBarContainer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.fedom.maybeprojectwork.R;

;

/**
 * A simple {@link Fragment} subclass.
 */
public class InserInfoContact extends Fragment {
    //start private Text
    private EditText name;
    private EditText surname;
    private EditText dateBirth;
    private EditText address;
    private EditText city;
    private EditText cap;
    private EditText telephone;
    private EditText email;
    private EditText province;

    private TextInputLayout name_layout;
    private TextInputLayout surname_layout;
    private TextInputLayout dateBirth_layout;
    private TextInputLayout address_layout;
    private TextInputLayout city_layout;
    private TextInputLayout cap_layout;
    private TextInputLayout telephone_layout;
    private TextInputLayout email_layout;
    private TextInputLayout province_layout;

    Toolbar toolbarinsert;

    public interface IAddContact{
        public void addContact( EditText nome, EditText cognome, EditText data_nascita, EditText indirizzo, EditText citta, EditText CAP, EditText telefono, EditText email, EditText provincia);
    }
    IAddContact iAddContact = new IAddContact() {
        @Override
        public void addContact(EditText nome, EditText cognome, EditText data_nascita, EditText indirizzo, EditText citta, EditText CAP, EditText telefono, EditText email, EditText provincia) {

        }
    };



    public InserInfoContact() {
        // Required empty public constructor
    }

    public static InserInfoContact newIstance(){


        InserInfoContact inserInfoContact= new InserInfoContact();
        Bundle bundle= new Bundle();
        inserInfoContact.setArguments(bundle);
        return inserInfoContact;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_inser_info_contact, container, false);
/*
        toolbarinsert = (Toolbar)view.findViewById(R.id.toolbaredit);
        toolbarinsert.setTitle("AddContact");
        toolbarinsert.setTitleTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().setActionBar(toolbarinsert);
        }*/
        ActionBarContainer actionBarContainer = (ActionBarContainer)view.findViewById(R.id.saveInfo);


        name_layout=(TextInputLayout)view.findViewById(R.id.input_layout_name);
        surname_layout=(TextInputLayout)view.findViewById(R.id.input_layout_surname);
        dateBirth_layout=(TextInputLayout)view.findViewById(R.id.input_layout_date);
        address_layout=(TextInputLayout)view.findViewById(R.id.input_layout_address);
        city_layout=(TextInputLayout)view.findViewById(R.id.input_layout_City);
        cap_layout=(TextInputLayout)view.findViewById(R.id.input_layout_CAP);
        telephone_layout=(TextInputLayout)view.findViewById(R.id.input_layout_telephone);
        email_layout=(TextInputLayout)view.findViewById(R.id.input_layout_email);
        province_layout=(TextInputLayout)view.findViewById(R.id.input_layout_province);

        name=(EditText)view.findViewById(R.id.insertName);
        surname=(EditText)view.findViewById(R.id.insertSurname);
        dateBirth=(EditText)view.findViewById(R.id.insertDate);
        address=(EditText)view.findViewById(R.id.insertAddress);
        city=(EditText)view.findViewById(R.id.InsertCity);
        cap=(EditText)view.findViewById(R.id.insertCAP);
        telephone=(EditText)view.findViewById(R.id.insertPhone);
        email=(EditText)view.findViewById(R.id.insertEmail);
        province=(EditText)view.findViewById(R.id.insertProvince);

        actionBarContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iAddContact.addContact( name,surname,dateBirth,address,city,cap,telephone,email,province );

            }
        });







        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iAddContact=null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ContactsFragment.IContactsFragment){
            iAddContact=(IAddContact)context;
        }
    }
}
