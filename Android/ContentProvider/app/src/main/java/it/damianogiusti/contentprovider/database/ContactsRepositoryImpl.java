package it.damianogiusti.contentprovider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import it.damianogiusti.contentprovider.model.Contact;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public class ContactsRepositoryImpl implements ContactsRepository {

    private DBHelper databaseHelper;
    private List<Contact> contacts;

    public ContactsRepositoryImpl(Context context) {
        this.databaseHelper = new DBHelper(context);
        this.contacts = new ArrayList<>();
    }

    @Override
    public List<Contact> getContacts() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor = database.query(ContactHelper.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactHelper._ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactHelper.NAME)));
            contact.setSurname(cursor.getString(cursor.getColumnIndex(ContactHelper.SURNAME)));
            contacts.add(contact);
        }
        cursor.close();
        database.close();
        return contacts;
    }

    @Override
    public boolean removeAllContacts() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int affectedRows = database.delete(ContactHelper.TABLE_NAME, null, null);
        database.close();
        return affectedRows > 0;
    }

    @Override
    public Contact getContact(long id) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(ContactHelper.TABLE_NAME, null, ContactHelper._ID + " = " + id, null, null, null, null);
        assert cursor.getCount() == 1;
        Contact contact = new Contact();
        while (cursor.moveToNext()) {
            contact.setId(id);
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactHelper.NAME)));
            contact.setSurname(cursor.getString(cursor.getColumnIndex(ContactHelper.SURNAME)));
        }
        cursor.close();
        database.close();
        return contact;
    }

    @Override
    public Contact addContact(Contact contact) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactHelper.NAME, contact.getName());
        contentValues.put(ContactHelper.SURNAME, contact.getSurname());
        long newID = database.insert(ContactHelper.TABLE_NAME, null, contentValues);
        contact.setId(newID);
        database.close();
        contacts.add(contact);
        return contact;
    }

    @Override
    public boolean removeContact(Contact contact) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int deletedRows = database.delete(ContactHelper.TABLE_NAME,
                String.format("%s = %d", ContactHelper._ID, contact.getId()), null);
        database.close();

        contacts.remove(contact);
        return deletedRows == 1;
    }

    @Override
    public boolean updateContact(Contact contact) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactHelper.NAME, contact.getName());
        contentValues.put(ContactHelper.SURNAME, contact.getSurname());
        int updatedRows = database.update(ContactHelper.TABLE_NAME, contentValues,
                String.format("%s = %d", ContactHelper._ID, contact.getId()), null);

        contacts.set(contacts.indexOf(contact), contact);
        database.close();
        return updatedRows == 1;
    }
}
