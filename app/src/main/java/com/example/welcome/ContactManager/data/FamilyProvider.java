package com.example.welcome.familyapp.data;

import android.content.ContentProvider;

/**
 * Created by welcome on 7/23/2017.
 */
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.welcome.familyapp.data.FamilyContract.friendsEntry;


public class FamilyProvider extends ContentProvider {
    public static final String LOG_TAG = FamilyProvider.class.getSimpleName();
    private static final int PERSON = 100;


    private static final int PERSON_ID = 101;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {

        sUriMatcher.addURI(FamilyContract.CONTENT_AUTHORITY, FamilyContract.PATH_FAMILY_MEMBER, PERSON);


        sUriMatcher.addURI(FamilyContract.CONTENT_AUTHORITY, FamilyContract.PATH_FAMILY_MEMBER + "/#", PERSON_ID);
    }


    private familydbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new familydbHelper((getContext()));
        return true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();


        Cursor cursor ;


        int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSON:

                cursor = database.query(friendsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PERSON_ID:

                selection = friendsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(friendsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);


        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSON:
                return insertPerson(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }


    private Uri insertPerson(Uri uri, ContentValues values) {

        String firstname = values.getAsString(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME);
        if (firstname == null) {
            throw new IllegalArgumentException("Person requires a name");
        }

        String lastname = values.getAsString(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME);
        if (lastname == null) {
            throw new IllegalArgumentException("Person requires a name");
        }

        String relation = values.getAsString(friendsEntry.COLUMN_FAMILY_MEMBER_RELATION);
        if (relation == null) {
            throw new IllegalArgumentException("Person requires a relation");
        }

        String phoneno = values.getAsString(friendsEntry.COLUMN_PHONE_NUMBER);
        if (phoneno == null) {
            throw new IllegalArgumentException("Person requires a phone no.");
        }


        Integer gender = values.getAsInteger(friendsEntry.COLUMN_FAMILY_MEMBER_GENDER);
        if (gender == null || !friendsEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Person requires valid gender");
        }


        String birthday = values.getAsString(friendsEntry.COLUMN_BIRTHDAY);
        if (birthday == null) {
            throw new IllegalArgumentException("Person requires a birthday");
        }

        String email = values.getAsString(friendsEntry.COLUMN_EMAILID);
        if (email == null) {
            throw new IllegalArgumentException("Person requires a email id");
        }

        String otherdetails = values.getAsString(friendsEntry.COLUMN_OTHER_ENTRIES);
        if (otherdetails == null) {
            throw new IllegalArgumentException("Person requires a extra detailing");
        }


        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        long id = database.insert(friendsEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }


        getContext().getContentResolver().notifyChange(uri, null);


        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSON:
                return updatePerson(uri, contentValues, selection, selectionArgs);
            case PERSON_ID:

                selection = friendsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updatePerson(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }


    private int updatePerson(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME)) {
            String name = values.getAsString(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Person requires a name");
            }
        }


        if (values.containsKey(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME)) {
            String name = values.getAsString(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Person requires a name");
            }
        }


        if (values.containsKey(friendsEntry.COLUMN_FAMILY_MEMBER_GENDER)) {
            Integer gender = values.getAsInteger(friendsEntry.COLUMN_FAMILY_MEMBER_GENDER);
            if (gender == null || !friendsEntry.isValidGender(gender)) {
                throw new IllegalArgumentException("Person requires valid gender");
            }
        }

        if (values.containsKey(friendsEntry.COLUMN_FAMILY_MEMBER_RELATION)) {
            String relation = values.getAsString(friendsEntry.COLUMN_FAMILY_MEMBER_RELATION);
            if (relation == null) {
                throw new IllegalArgumentException("Person requires a Relation");
            }
        }

        if (values.containsKey(friendsEntry.COLUMN_BIRTHDAY)) {
            String birthday = values.getAsString(friendsEntry.COLUMN_BIRTHDAY);
            if (birthday == null) {
                throw new IllegalArgumentException("Person requires a birthday");
            }
        }


        if (values.containsKey(friendsEntry.COLUMN_PHONE_NUMBER)) {
            String phoneno = values.getAsString(friendsEntry.COLUMN_PHONE_NUMBER);
            if (phoneno == null) {
                throw new IllegalArgumentException("Person requires a phone number");
            }
        }

        if (values.containsKey(friendsEntry.COLUMN_EMAILID)) {
            String email = values.getAsString(friendsEntry.COLUMN_EMAILID);
            if (email == null) {
                throw new IllegalArgumentException("Person requires a emailid");
            }
        }

        if (values.containsKey(friendsEntry.COLUMN_OTHER_ENTRIES)) {
            String otherdetails = values.getAsString(friendsEntry.COLUMN_OTHER_ENTRIES);
            if (otherdetails == null) {
                throw new IllegalArgumentException("Person requires a other details");
            }
        }


        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        int rowsUpdated = database.update(friendsEntry.TABLE_NAME, values, selection, selectionArgs);


        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSON:

                rowsDeleted = database.delete(friendsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PERSON_ID:

                selection = friendsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(friendsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }


        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }


        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSON:
                return friendsEntry.CONTENT_LIST_TYPE;
            case PERSON_ID:
                return friendsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}




