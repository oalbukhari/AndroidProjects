package it.damianogiusti.dataset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import it.damianogiusti.dataset.adapter.ContactsAdapter;
import it.damianogiusti.dataset.database.ContactsRepository;
import it.damianogiusti.dataset.dialogs.ConfirmDialog;
import it.damianogiusti.dataset.dialogs.EditDialog;
import it.damianogiusti.dataset.model.Contact;

public class MainActivity extends AppCompatActivity implements
        ConfirmDialog.ConfirmationButtonsCallbacks,
        EditDialog.EditContactCallbacks {

    private ListView contactsListView;
    private Button btnAddContact;

    private ContactsRepository contactsRepository;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRepository = Dataset.get(getApplicationContext());
        bind();
        setupListAndAdapter();

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactsRepository.addContact(Contact.randomContact());
                contactsAdapter.notifyDataSetChanged();
            }
        });

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactsAdapter.getItem(i);
                EditDialog.newInstance(contact.getId())
                        .show(getSupportFragmentManager(), EditDialog.class.getSimpleName().toUpperCase());
            }
        });

        contactsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactsAdapter.getItem(i);
                ConfirmDialog.newInstance(contact.getId())
                        .show(getSupportFragmentManager(), ConfirmDialog.class.getSimpleName().toUpperCase());
                return true;
            }
        });
    }

    private void bind() {
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        btnAddContact = (Button) findViewById(R.id.btnAddContact);
    }

    private void setupListAndAdapter() {
        contactsAdapter = new ContactsAdapter(getApplicationContext(), contactsRepository.getContacts());
        contactsListView.setAdapter(contactsAdapter);
    }

    @Override
    public void onConfirmed(Contact contact) {
        contactsRepository.removeContact(contact);
        contactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCanceled(Contact contact) {

    }

    @Override
    public void onContactEdited(Contact editedContact) {
        contactsRepository.updateContact(editedContact);
        contactsAdapter.notifyDataSetChanged();
    }
}
