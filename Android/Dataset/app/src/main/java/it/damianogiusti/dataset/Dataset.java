package it.damianogiusti.dataset;

import android.content.Context;

import it.damianogiusti.dataset.database.ContactsRepository;
import it.damianogiusti.dataset.database.ContactsRepositoryImpl;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public class Dataset {

    // --- singleton instance --- //
    private static ContactsRepository instance = null;

    public static synchronized ContactsRepository get(Context context) {
        if (instance == null)
            instance = new ContactsRepositoryImpl(context);
        return instance;
    }
    // --- //
}
