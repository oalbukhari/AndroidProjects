package it.damianogiusti.dataset.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import it.damianogiusti.dataset.Dataset;
import it.damianogiusti.dataset.R;
import it.damianogiusti.dataset.database.ContactHelper;
import it.damianogiusti.dataset.model.Contact;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public class ConfirmDialog extends DialogFragment {

    public static ConfirmDialog newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(ContactHelper._ID, id);
        ConfirmDialog fragment = new ConfirmDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public interface ConfirmationButtonsCallbacks {
        void onConfirmed(Contact contact);

        void onCanceled(Contact contact);
    }

    private Contact contact;
    private ConfirmationButtonsCallbacks confirmationButtonsCallbacks;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ConfirmationButtonsCallbacks)
            confirmationButtonsCallbacks = (ConfirmationButtonsCallbacks) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // obtain contact
        long id = getArguments().getLong(ContactHelper._ID);
        contact = Dataset.get(getActivity().getApplicationContext()).getContact(id);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.delete_confirmation, String.format("%s %s", contact.getName(), contact.getSurname())))
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (confirmationButtonsCallbacks != null)
                            confirmationButtonsCallbacks.onConfirmed(contact);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (confirmationButtonsCallbacks != null)
                            confirmationButtonsCallbacks.onCanceled(contact);
                    }
                })
                .create();
        return dialog;
    }
}
