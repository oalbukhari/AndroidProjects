package it.damianogiusti.a020_test_intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends MainActivityHelper {

    /*
    https://developer.android.com/guide/components/intents-filters.html
    https://developer.android.com/guide/components/intents-common.html
    https://developer.android.com/reference/android/content/Intent.html
     */

    private static final int PICK_CONTACT = 843;

    private ContactFetcher contactFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactFetcher = new ContactFetcher(getApplicationContext());
    }

    @Override
    protected void onBtnWebPressed() {
        // open a web page
        Uri uri = Uri.parse("http://www.google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // or
        // Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setData(uri);

        Intent chooser = Intent.createChooser(intent, "Il mio titolo");

        // checks if the intent can be handled by the system
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(chooser);
    }

    @Override
    protected void onBtnSmsPressed() {
        // sending out an sms
        Intent intent = new Intent(Intent.ACTION_SEND);
        // target only sms apps
        intent.setData(Uri.parse("smsto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello from the other side!");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    @Override
    protected void onBtnPickPressed() {
        // pick a contact from somewhere
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICK_CONTACT: {
                    if (data.getData() != null) {
                        contactFetcher.getContact(data.getData(), new ContactFetcher.FetchListener() {
                            @Override
                            public void onContactFetched(Bundle contact) {
                                if (contact != null) {
                                    Toast.makeText(MainActivity.this, contact.getString(ContactFetcher.DISPLAY_NAME), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                break;
            }
        } else {
            Toast.makeText(this, "Something wrong happened.", Toast.LENGTH_SHORT).show();
        }
    }
}
