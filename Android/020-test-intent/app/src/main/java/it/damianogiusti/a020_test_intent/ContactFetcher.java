package it.damianogiusti.a020_test_intent;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;

/**
 * Created by damiano on 04/11/16.
 */

public class ContactFetcher {

    public static final String DISPLAY_NAME = "contact.display_name";
    public static final String PHONE_NUMBER = "contact.phonenumber";

    public interface FetchListener {
        void onContactFetched(Bundle contact);
    }

    private Context context;

    public ContactFetcher(Context context) {
        this.context = context;
    }

    public void getContact(final Uri uri, final FetchListener fetchListener) {
        if (uri == null) {
            fetchListener.onContactFetched(null);
            return;
        }

        new AsyncTask<Uri, Void, Bundle>() {
            @Override
            protected Bundle doInBackground(Uri... uris) {
                String[] projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
                Cursor cursor = context.getContentResolver().query(uris[0], projection,
                        null, null, null);

                Bundle bundle = null;

                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                    bundle = new Bundle();
                    if (nameIndex >= 0)
                        bundle.putString(DISPLAY_NAME, cursor.getString(nameIndex));
                    if (numberIndex >= 0)
                        bundle.putString(PHONE_NUMBER, cursor.getString(numberIndex));
                }

                if (cursor != null)
                    cursor.close();
                return bundle;
            }

            @Override
            protected void onPostExecute(Bundle bundle) {
                super.onPostExecute(bundle);
                fetchListener.onContactFetched(bundle);
            }
        }.execute(uri);
    }
}
