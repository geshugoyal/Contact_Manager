package com.example.welcome.familyapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.welcome.familyapp.data.FamilyContract.friendsEntry;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_PERSON_LOADER = 0;
    DayNotification dnObject;

    private Uri mCurrentPersonUri;
    EditText e;
    EditText e1;
    EditText e2;
    private static final int CONTACT_PICKER_RESULT = 1;
    private static final String DEBUG_TAG = null;


    private EditText mfirstname;
    private EditText mlastname;
    private EditText mphonenumber;
    private EditText mbirthday;
    private EditText mrelation;
    private EditText motherdetails;
    private Spinner mGenderSpinner;
    private int mGender = friendsEntry.GENDER_UNKNOWN;
    private EditText memailid;
    private boolean mPersonHasChanged = false;
    private static final String[] RELATION_STRING = new String[] {
            "Mother", "Father", "Brother", "Sister", "Friend","Colleague"
    };
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPersonHasChanged = true;
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentPersonUri = intent.getData();


        if (mCurrentPersonUri == null) {

            setTitle(getString(R.string.editor_activity_title_new_person));

            invalidateOptionsMenu();
        } else {

            setTitle(getString(R.string.editor_activity_title_edit_person));


            getLoaderManager().initLoader(EXISTING_PERSON_LOADER, null, this);
        }

        mfirstname = (EditText) findViewById(R.id.first_name);
        mlastname = (EditText) findViewById(R.id.last_name);
        mbirthday = (EditText) findViewById(R.id.birthday);
        mrelation = (EditText) findViewById(R.id.relation);
        motherdetails = (EditText) findViewById(R.id.otherdetails);
        mphonenumber = (EditText) findViewById(R.id.phone);


        // alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        //   public void onClick(DialogInterface dialog, int whichButton) {
        // Canceled.
        //  }
        // });
        memailid = (EditText) findViewById(R.id.emailid);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);


        mfirstname.setOnTouchListener(mTouchListener);
        mlastname.setOnTouchListener(mTouchListener);
        memailid.setOnTouchListener(mTouchListener);
        mphonenumber.setOnTouchListener(mTouchListener);
        motherdetails.setOnTouchListener(mTouchListener);
        mGenderSpinner.setOnTouchListener(mTouchListener);
        mbirthday.setOnTouchListener(mTouchListener);
        mrelation.setOnTouchListener(mTouchListener);

        setupSpinner();

    }



    private void setRelation(){

        ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, RELATION_STRING);
        mrelation = (EditText) findViewById(R.id.relation);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.relation);
        textView.setAdapter(adapter12);
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);
 //mGender = mGenderSpinner.getSelectedItemPosition();
        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = 1; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = 2; // Female
                    } else {
                        mGender = 0; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }


    public void addBirthday(String bday) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date date = dateFormat.parse(bday);
            Date d1= new Date();
            Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            Calendar cal1= Calendar.getInstance();
            cal.setTime(date);
            cal1.setTime(d1);
            int month= cal.get(cal.MONTH);
            int day= cal.get(cal.DAY_OF_MONTH);
            int year=cal.get(cal1.YEAR);
            cal.set(day,month,year);
            long t1=cal.getTimeInMillis();
            System.out.println("Time in Milised" +t1);
            int var, var1;
            if(year%4==0){
                var=366;
            }
         else{
                var=365;
            }
           // dnObject.notiMethod(day,month);
            Intent intent = new Intent(EditorActivity.this,MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast
                    (this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, t1 ,
                 60*60*1000*24*var , pendingIntent); }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void addCalendarEvent() {

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "Sample Event");
        intent.putExtra("description", "Write description of the event");
        startActivity(intent);
    }


    public void doLaunchContactPicker(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    Cursor cursor = null;
                    String name = "";
                    String phone = "";
                    String firstname = "";
                    String lastname = "";
                    try {
                        Uri result = data.getData();
                        Log.v(DEBUG_TAG, "Got a contact result: "
                                + result.toString());

                        // get the contact id from the Uri
                        String id = result.getLastPathSegment();

                        // query for name
                        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id},
                                null);
                        int phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                        int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);


                        if (cursor != null && cursor.moveToFirst()) {

                            phone = cursor.getString(phoneIdx);
                            name = cursor.getString(nameIdx);
                            String arr[] = name.split(" ", 2);
                            firstname = arr[0];
                            lastname= arr[1];
                            //lastname = name.substring(name.lastIndexOf(" ")+1);

                            System.out.println("firstname"+name);

                        }


                    } catch (Exception e) {
                        Log.e(DEBUG_TAG, "Failed to get name", e);
                        System.out.println("------------------>>>"+e.toString());
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                        e = (EditText) findViewById(R.id.first_name);
                        e.setText(firstname);
                        e2 = (EditText) findViewById(R.id.last_name);
                        e2.setText(lastname);
                        e1 = (EditText) findViewById(R.id.phone);
                        e1.setText(phone);
                        if (firstname.length() == 0) {

                            Toast.makeText(this, "Name not found for contact.",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    break;
            }

        } else {
            Log.w(DEBUG_TAG, "Warning: activity result not ok");
        }
    }
    Intent intent=null;
    public void showMap() {

        //  intent = new Intent(android.content.Intent.ACTION_VIEW);
        // intent.setData(Uri.parse("geo:0,0?q=current+location"));
        // startActivity(intent);
        String details=  motherdetails.getText().toString();
        String url = "http://maps.google.com/maps?daddr="+details;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
        startActivity(intent);

    }






    private void insertPerson(){
        String firstnameString= mfirstname.getText().toString().trim();
        String lastnameString= mlastname.getText().toString().trim();
        String relationString= mrelation.getText().toString().trim();
        String phonenumberString= mphonenumber.getText().toString().trim();
        String otherdetailsString= motherdetails.getText().toString().trim();
        String birthdayString= mbirthday.getText().toString().trim();
        String emailidString= memailid.getText().toString().trim();


        if (mCurrentPersonUri == null &&
                TextUtils.isEmpty(firstnameString) && TextUtils.isEmpty(lastnameString) &&
                TextUtils.isEmpty(relationString) && mGender == friendsEntry.GENDER_UNKNOWN && TextUtils.isEmpty(birthdayString)&&
                TextUtils.isEmpty(phonenumberString) && TextUtils.isEmpty(emailidString) && TextUtils.isEmpty(otherdetailsString)) {

            return;
        }

        // familydbHelper mdbHelper= new familydbHelper(this);
        // SQLiteDatabase db= mdbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME,firstnameString);
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME,lastnameString);
        values.put(friendsEntry.COLUMN_PHONE_NUMBER,phonenumberString);
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_RELATION,relationString);
        values.put(friendsEntry.COLUMN_EMAILID,emailidString);
        values.put(friendsEntry.COLUMN_BIRTHDAY,birthdayString);
        values.put(friendsEntry.COLUMN_OTHER_ENTRIES,otherdetailsString);
        values.put(friendsEntry.COLUMN_FAMILY_MEMBER_GENDER, mGender);


        addBirthday(birthdayString);
        System.out.println("String for birthday" +birthdayString);
        if (mCurrentPersonUri == null) {

            Uri newUri = getContentResolver().insert(friendsEntry.CONTENT_URI, values);


            if (newUri == null) {

                Toast.makeText(this, getString(R.string.editor_insert_person_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_person_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {

            int rowsAffected = getContentResolver().update(mCurrentPersonUri, values, null, null);

            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.editor_update_person_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_update_person_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void sendsms(){

        //Intent i = new Intent(EditorActivity.this,MainActivity.class);
        //startActivity(i);
        String x= mphonenumber.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", x, null)));
        // protected void sendSMS()


    }

    private void dialContactPhone() {

        String phoneNumber=  mphonenumber.getText().toString();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
  /*  private void launchApp(String packageName, String name)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(packageName, name));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    protected void sendEmail(String packageName){
        try
        {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);

     for(ResolveInfo info : resolveInfoList)
     if(info.activityInfo.packageName.equalsIgnoreCase(packageName))
              {
                 Toast toast = Toast.makeText(EditorActivity.this, "Gmail opened", Toast.LENGTH_LONG);
                  toast.show();
                  launchApp(info.activityInfo.packageName, info.activityInfo.name);
                  return;
              }
      }
      catch (Exception e)
      {
          Toast toast = Toast.makeText(EditorActivity.this, "Can't find Gmail", Toast.LENGTH_LONG);
          toast.show();
      }
    }
*/



    protected void sendEmail() {

        Log.i("Send email", "");
        // String TO =  memailid.getText().toString();
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{memailid.getText().toString()});
        //emailIntent.putExtra("address", TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EditorActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mCurrentPersonUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_choose:
                //  Intent i= new Intent(EditorActivity.this,MainActivity.class);
                // startActivity(i);

                return true;

            case R.id.action_save:

                insertPerson();

                finish();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case R.id.action_sms:
                sendsms();
                return true;



            case R.id.action_event:
                addCalendarEvent();
                return true;

            case R.id.action_location:
                showMap();
                return true;

            case R.id.action_mail:
                // sendEmail(String.valueOf(memailid));
                // sendEmail("com.google.android.gm");
                sendEmail();
                return true;

            case R.id.action_contact:

                dialContactPhone();


                return true;

            case android.R.id.home:
                if (!mPersonHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }


                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (!mPersonHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);
    }


    // long newRowId = db.insert(friendsEntry.TABLE_NAME, null, values);

    //    if (newRowId == -1) {
    // If the row ID is -1, then there was an error with insertion.
    //        Toast.makeText(this, "Error with saving member", Toast.LENGTH_SHORT).show();
    //    } else {
    // Otherwise, the insertion was successful and we can display a toast with the row ID.
    //        Toast.makeText(this, "Member saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
    //    }
    // }



    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                friendsEntry._ID,
                friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME,
                friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME,
                friendsEntry.COLUMN_FAMILY_MEMBER_RELATION,
                friendsEntry.COLUMN_BIRTHDAY,
                friendsEntry.COLUMN_FAMILY_MEMBER_GENDER,
                friendsEntry.COLUMN_EMAILID,
                friendsEntry.COLUMN_PHONE_NUMBER,
                friendsEntry.COLUMN_OTHER_ENTRIES};


        return new CursorLoader(this,   // Parent activity context
                mCurrentPersonUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            startManagingCursor(cursor);

            return;
        }

        if (cursor.moveToFirst()) {
            int firstnameColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME);
            int lastnameColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME);
            int genderColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_FAMILY_MEMBER_GENDER);
            int relationColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_FAMILY_MEMBER_RELATION);
            int phonenoColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_PHONE_NUMBER);
            int birthdayColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_BIRTHDAY);
            int emailidColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_EMAILID);
            int otherdetailsColumnIndex = cursor.getColumnIndex(friendsEntry.COLUMN_OTHER_ENTRIES);



            String firstname = cursor.getString(firstnameColumnIndex);
            String lastname = cursor.getString(lastnameColumnIndex);
            int gender = cursor.getInt(genderColumnIndex);
            String relation = cursor.getString(relationColumnIndex);
            String birthday = cursor.getString(birthdayColumnIndex);
            String emailid = cursor.getString(emailidColumnIndex);

            String phoneno = cursor.getString(phonenoColumnIndex);
            String otherdetails = cursor.getString(otherdetailsColumnIndex);

            mfirstname.setText(firstname);
            mlastname.setText(lastname);
            mrelation.setText(relation);
            mbirthday.setText(birthday);
            mphonenumber.setText(phoneno);
            memailid.setText(emailid);
            motherdetails.setText(otherdetails);


            switch (gender) {
                case friendsEntry.GENDER_MALE:
                    mGenderSpinner.setSelection(1);
                    break;
                case friendsEntry.GENDER_FEMALE:
                    mGenderSpinner.setSelection(2);
                    break;
                default:
                    mGenderSpinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mfirstname.setText("");
        mlastname.setText("");
        mbirthday.setText("");
        mphonenumber.setText("");
        mrelation.setText("");
        memailid.setText("");
        motherdetails.setText("");
        mGenderSpinner.setSelection(0); // Select "Unknown" gender
    }


    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deletePerson();
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


    private void deletePerson() {
        if (mCurrentPersonUri != null) {

            int rowsDeleted = getContentResolver().delete(mCurrentPersonUri, null, null);

            if (rowsDeleted == 0) {
                Toast.makeText(this,  getString(R.string.editor_delete_person_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_person_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }

}