package com.example.welcome.familyapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.welcome.familyapp.data.ContactModal;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.welcome.familyapp.data.ContactModal;
import com.example.welcome.familyapp.data.FamilyContract;

import java.util.ArrayList;

/**
 * Created by welcome on 7/24/2018.
 */

public class MyBaseAdapter1  {
    /*
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<EditorModal> editorModals;
    public MyBaseAdapter1(Context context, ArrayList<EditorModal> editorModals) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.editorModals = editorModals;

    }

    @Override
    public int getCount() {
        return editorModals.size();
    }

    @Override
    public Object getItem(int position) {
        return editorModals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.activity_editor,null);
        EditText nameTextView = (EditText) view.findViewById(R.id.first_name);
        EditText lastTextView = (EditText) view.findViewById(R.id.last_name);
        EditText relationTextView = (EditText) view.findViewById(R.id.relation);
        EditText phoneTextView = (EditText) view.findViewById(R.id.phone);
        EditText genderTextView = (EditText) view.findViewById(R.id.spinner_gender);
        EditText emailTextView = (EditText) view.findViewById(R.id.emailid);
        EditText birthdayTextView = (EditText) view.findViewById(R.id.birthday);
        EditText otherdetailsTextView = (EditText) view.findViewById(R.id.otherdetails);

//        int firstnameColumnIndex = cursor.getColumnIndex(FamilyContract.friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME);
//        int relationColumnIndex = cursor.getColumnIndex(FamilyContract.friendsEntry.COLUMN_FAMILY_MEMBER_RELATION);

        String personFirstName = editorModals.get(position).getFirstname();//cursor.getString(firstnameColumnIndex);
        String personLastName =editorModals.get(position).getLastname();
        String personRelation =editorModals.get(position).getRelation();
        String personPhone =editorModals.get(position).getPhoneno();
        Spinner personGender = editorModals.get(position).getGender();
        String personEmail =editorModals.get(position).getEmail();
        String personBirthday =editorModals.get(position).getBirthday();
        String personOtherdetails =editorModals.get(position).getOtherdetails();


        if (TextUtils.isEmpty(personRelation)) {
            personRelation = context.getString(R.string.unknown_relation);
        }

        nameTextView.setText(personFirstName);
        lastTextView.setText(personLastName);
        relationTextView.setText(personRelation);
        phoneTextView.setText(personPhone);
        genderTextView.setText((CharSequence) personGender);
        emailTextView.setText(personEmail);
        birthdayTextView.setText(personBirthday);
        otherdetailsTextView.setText(personOtherdetails);

        return view;
    }

    public void setEditorModals(ArrayList<EditorModal> editorModals) {
        this.editorModals = editorModals;
    }
    */
}

