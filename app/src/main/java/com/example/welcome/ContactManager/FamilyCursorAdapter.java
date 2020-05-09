package com.example.welcome.familyapp;

/**
 * Created by welcome on 7/23/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.welcome.familyapp.data.FamilyContract.friendsEntry;


public class FamilyCursorAdapter extends CursorAdapter {

    public FamilyCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

        int firstnameColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME);
        int lastnameColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME);
        int relationColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_PHONE_NUMBER);

        String personFirstName = cursor.getString(firstnameColumnIndex);
        String personLastName = cursor.getString(lastnameColumnIndex);
        String personName= personFirstName +" " +personLastName;
        String personRelation = cursor.getString(relationColumnIndex);


        if (TextUtils.isEmpty(personRelation)) {
            personRelation = context.getString(R.string.unknown_relation);
        }

        nameTextView.setText(personName);
        summaryTextView.setText(personRelation);
    }



}