package com.example.fedom.navbar;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.fedom.navbar.Dialogs.DialogError;
import com.example.fedom.navbar.TabsFragment.BlankFragment;


public class MainActivity extends AppCompatActivity implements DialogError.IDialogErrorListener, BlankFragment.IButtonFragmentListener{



    private BlankFragment blankFragment;

    private static final String TAGDIALOG="LOLOLOLOLOLOLOL";
    private static final String TAG_BUTTON_FRAGMENT = "tagbtnfragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        blankFragment= (BlankFragment) getSupportFragmentManager().findFragmentByTag(TAG_BUTTON_FRAGMENT);
        if(blankFragment==null){
            blankFragment = BlankFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layoutChange, blankFragment, TAG_BUTTON_FRAGMENT)
                    .commit();
        }

    }


    @Override
    public void errore() {
    }

    @Override
    public void onErrorPressed() {
        DialogError dialog= new DialogError();
        dialog.show(getSupportFragmentManager(),TAGDIALOG);
    }
}
