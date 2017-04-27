package it.damianogiusti.contentprovider.model;

import java.util.UUID;

/**
 * Created by Damiano Giusti on 14/12/16.
 */
public class Contact {

    private long id;
    private String name;
    private String surname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Contact))
            return false;

        Contact contact = (Contact) obj;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    public static Contact randomContact() {
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().hashCode());
        contact.setName("Random");
        contact.setSurname("Contact");
        return contact;
    }
}
