package it.damianogiusti.dataset.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import it.damianogiusti.dataset.R;
import it.damianogiusti.dataset.model.Contact;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactList;

    public ContactsAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int i) {
        return contactList.size() > i ? contactList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return contactList.get(i).getId();
    }

    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {
        View rowView = view;
        ContactViewHolder contactViewHolder;
        Contact contact = contactList.get(i);

        if (view == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.contact_list_item, null);
            contactViewHolder = new ContactViewHolder(rowView);
            rowView.setTag(contactViewHolder);
        } else {
            contactViewHolder = (ContactViewHolder) rowView.getTag();
        }

        contactViewHolder.txtName.setText(contact.getName());
        contactViewHolder.txtSurname.setText(contact.getSurname());

        return rowView;
    }

    private static class ContactViewHolder {
        private TextView txtName;
        private TextView txtSurname;

        private ContactViewHolder(View rowView) {
            txtName = (TextView) rowView.findViewById(R.id.txtName);
            txtSurname = (TextView) rowView.findViewById(R.id.txtSurname);
        }
    }
}
