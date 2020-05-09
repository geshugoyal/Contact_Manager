package com.example.welcome.familyapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.welcome.familyapp.data.ContactModal;

import java.util.ArrayList;

/**
 * Created by welcome on 7/24/2018.
 */

public class MyBaseAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<ContactModal> contactModals;
    public MyBaseAdapter(Context context, ArrayList<ContactModal> contactModals) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.contactModals = contactModals;

    }

    @Override
    public int getCount() {
        return contactModals.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.list_item,null);
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

//        int firstnameColumnIndex = cursor.getColumnIndex(FamilyContract.friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME);
//        int relationColumnIndex = cursor.getColumnIndex(FamilyContract.friendsEntry.COLUMN_FAMILY_MEMBER_RELATION);

        String personName = contactModals.get(position).getName();//cursor.getString(firstnameColumnIndex);
        String personRelation =contactModals.get(position).getPhoneno();


        if (TextUtils.isEmpty(personRelation)) {
            personRelation = context.getString(R.string.unknown_relation);
        }

        nameTextView.setText(personName);
        summaryTextView.setText(personRelation);


        return view;
    }

    public void setContactModals(ArrayList<ContactModal> contactModals) {
        this.contactModals = contactModals;
    }
}
