package com.example.welcome.familyapp;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.welcome.familyapp.data.FamilyContract.friendsEntry;


public class CatalogActivity  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {


    private static final int PERSON_LOADER = 0;


    FamilyCursorAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);


            }
        });



        ListView personListView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);
        personListView.setEmptyView(emptyView);


        mCursorAdapter = new FamilyCursorAdapter(this, null);


        // FamilyCursorAdapter.sort(new Comparator<FamilyContract>() {
        //    @Override
        //   public int compare(FamilyContract firstname1, FamilyContract firstname2) {
        //      return firstname1.PATH_FAMILY_MEMBER .compareTo(firstname2.PATH_FAMILY_MEMBER);
        //  }
        // });



        personListView.setAdapter(mCursorAdapter);


        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);


                Uri currentPersonUri = ContentUris.withAppendedId(friendsEntry.CONTENT_URI, id);


                intent.setData(currentPersonUri);


                startActivity(intent);
            }
        });


        getLoaderManager().initLoader(PERSON_LOADER, null, this);
    }


    private void insertPerson() {
        // Gets the database in write mode
        ContentValues values = new ContentValues();
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME, "ABC");
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME, "XYZ");
        values.put(friendsEntry.COLUMN_PHONE_NUMBER, "7218739754");
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_RELATION, "Relative");
        values.put(friendsEntry.COLUMN_EMAILID, "abcxyz@pqr.com");
        values.put(friendsEntry.COLUMN_BIRTHDAY, "08/11/1997");
        values.put(friendsEntry.COLUMN_OTHER_ENTRIES, "Sonipat");
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_GENDER, friendsEntry.GENDER_FEMALE);

        //long newRowId = db.insert(friendsEntry.TABLE_NAME, null, values);
        Uri newUri = getContentResolver().insert(friendsEntry.CONTENT_URI, values);


    }

    private void deleteAllPersons() {
        int rowsDeleted = getContentResolver().delete(friendsEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from person database");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // Do nothing for now
                insertPerson();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                showDeleteConfirmationDialog();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {

        String[] projection = {
                friendsEntry._ID,
                friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME,
                friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME,
                friendsEntry.COLUMN_FAMILY_MEMBER_RELATION,
                friendsEntry.COLUMN_BIRTHDAY,
                friendsEntry.COLUMN_PHONE_NUMBER,
                friendsEntry.COLUMN_EMAILID,
                friendsEntry.COLUMN_OTHER_ENTRIES

        };


        return new CursorLoader(this,   // Parent activity context
                friendsEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME  + " ASC");                  // Default sort order
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mCursorAdapter.swapCursor(null);
    }
    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_multimsg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteAllPersons();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}





