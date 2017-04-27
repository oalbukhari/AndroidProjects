package it.damianogiusti.contentprovider.database;

import java.util.List;

import it.damianogiusti.contentprovider.model.Contact;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public interface ContactsRepository {

    List<Contact> getContacts();

    boolean removeAllContacts();

    Contact getContact(long id);

    Contact addContact(Contact contact);

    boolean removeContact(Contact contact);

    boolean updateContact(Contact contact);
}
