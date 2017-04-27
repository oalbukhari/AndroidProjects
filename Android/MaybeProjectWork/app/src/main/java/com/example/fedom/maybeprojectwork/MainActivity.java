package com.example.fedom.maybeprojectwork;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.example.fedom.maybeprojectwork.ContentProvider.AnagrafeContentProvider;
import com.example.fedom.maybeprojectwork.Database.AnagrafeTabella;
import com.example.fedom.maybeprojectwork.Dialogs.DialogElimina;
import com.example.fedom.maybeprojectwork.FragmentActivities.ContactsFragment;
import com.example.fedom.maybeprojectwork.FragmentActivities.DetailContact;
import com.example.fedom.maybeprojectwork.FragmentActivities.InserInfoContact;

public class MainActivity extends AppCompatActivity implements ContactsFragment.IContactsFragment , InserInfoContact.IAddContact, DetailContact.IDetailContact, DialogElimina.IDialogEliminaListener{

    private final static String TAG_FRAGMENT_CONTACT="TAG_CONTACT_FRAGMENT";
    private final static String TAG_CONTATC_DETAIL="TAG_CONTACT_DETAIL";
    private final static String TAG_CONTATC_INSERT="TAG_CONTATC_INSERT";
    private final static String TAG_DIALOG_DELETE="TAG_DIALOG_DELETE";
    private ContactsFragment contactsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsFragment=(ContactsFragment)getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_CONTACT);
        if(contactsFragment==null){
            contactsFragment=ContactsFragment.newIstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_layout,contactsFragment,TAG_FRAGMENT_CONTACT)
                    .commit();
        }
    }
    //tap from the list to see al informations
    @Override
    public void getInfoContact(long iD) {
        DetailContact detailContact= (DetailContact) getSupportFragmentManager().findFragmentByTag(TAG_CONTATC_DETAIL);
        if(detailContact==null){
            detailContact=DetailContact.newInstance(iD);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_layout,detailContact)
                    .commit();

        }
    }
    //button to add a new contact
    @Override
    public void addNewContact() {
        InserInfoContact inserInfoContact = (InserInfoContact) getSupportFragmentManager().findFragmentByTag(TAG_CONTATC_INSERT);
        if (inserInfoContact == null) {
            inserInfoContact = InserInfoContact.newIstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_layout, inserInfoContact)
                    .commit();

        }
    }
    //adding values tu my database + going in main
    @Override
    public void addContact(EditText nome, EditText cognome, EditText data_nascita, EditText indirizzo, EditText citta, EditText CAP, EditText telefono, EditText email, EditText provincia) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AnagrafeTabella.EMAIL,email.getText().toString());
        contentValues.put(AnagrafeTabella.BORN_DATE,data_nascita.getText().toString());
        contentValues.put(AnagrafeTabella.STREET,indirizzo.getText().toString());
        contentValues.put(AnagrafeTabella.CITY,citta.getText().toString());
        contentValues.put(AnagrafeTabella.TELEPHONE,telefono.getText().toString());
        contentValues.put(AnagrafeTabella.SURNAME,cognome.getText().toString());
        contentValues.put(AnagrafeTabella.NAME,nome.getText().toString());
        contentValues.put(AnagrafeTabella.CAP,CAP.getText().toString());
        contentValues.put(AnagrafeTabella.PROVINCE,provincia.getText().toString());
        getContentResolver().insert(AnagrafeContentProvider.ANAGRAFE_URI,contentValues);

        backToMain();


    }
    //function to go to main
    void backToMain (){
        Log.d("main","function in");
        contactsFragment=ContactsFragment.newIstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout,contactsFragment,TAG_FRAGMENT_CONTACT)
                .addToBackStack("INFO")
                .commit();
    }


    @Override
    public void DetailDelete(long aItemid) {
        DialogElimina dialogElimina = DialogElimina.getInstance(aItemid);
        dialogElimina.show(getSupportFragmentManager(),TAG_DIALOG_DELETE);

    }

    @Override
    public void deleteInDb(boolean value,long idPassed) {
        if(value) {
            Uri uriToDelete = Uri.parse(AnagrafeContentProvider.ANAGRAFE_URI + "/" + idPassed);
            getContentResolver().delete(uriToDelete, null, null);
            backToMain();
        }
    }
}
