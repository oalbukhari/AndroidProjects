package it.damianogiusti.dataset.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import it.damianogiusti.dataset.Dataset;
import it.damianogiusti.dataset.R;
import it.damianogiusti.dataset.database.ContactHelper;
import it.damianogiusti.dataset.model.Contact;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public class EditDialog extends DialogFragment {

    public interface EditContactCallbacks {
        void onContactEdited(Contact editedContact);
    }

    public static EditDialog newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(ContactHelper._ID, id);
        EditDialog fragment = new EditDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private Contact contact;
    private EditContactCallbacks editContactCallbacks;

    private EditText txtName;
    private EditText txtSurname;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof EditContactCallbacks)
            editContactCallbacks = (EditContactCallbacks) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // obtain contact
        long id = getArguments().getLong(ContactHelper._ID);
        contact = Dataset.get(getActivity().getApplicationContext()).getContact(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(R.layout.edit_dialog, null);
        builder.setView(view);

        // set names
        txtName = (EditText) view.findViewById(R.id.etxtName);
        txtSurname = (EditText) view.findViewById(R.id.etxtSurname);
        txtName.setText(contact.getName());
        txtSurname.setText(contact.getSurname());

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editContactCallbacks != null) {
                    contact.setName(txtName.getText().toString());
                    contact.setSurname(txtSurname.getText().toString());
                    editContactCallbacks.onContactEdited(contact);
                }
            }
        });

        return builder.create();
    }
}
