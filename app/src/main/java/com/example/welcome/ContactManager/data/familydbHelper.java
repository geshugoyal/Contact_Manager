package com.example.welcome.familyapp.data;



        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import com.example.welcome.familyapp.data.FamilyContract.friendsEntry;



public class familydbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = familydbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "memberManager.db";
    private static final int DATABASE_VERSION = 1;


    public familydbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_FAMILY_TABLE =
                "CREATE TABLE " + friendsEntry.TABLE_NAME  +  "("
               + friendsEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
               + friendsEntry.COLUMN_FAMILY_MEMBER_FIRST_NAME + " TEXT NOT NULL, "
               + friendsEntry.COLUMN_FAMILY_MEMBER_RELATION +" TEXT, "
               + friendsEntry.COLUMN_FAMILY_MEMBER_LAST_NAME + " TEXT NOT NULL, "
               + friendsEntry.COLUMN_PHONE_NUMBER + " TEXT NOT NULL, "
               + friendsEntry.COLUMN_BIRTHDAY +" TEXT, "
               + friendsEntry.COLUMN_FAMILY_MEMBER_GENDER +  " INTEGER NOT NULL DEFAULT 0, "
               +friendsEntry.COLUMN_EMAILID +" TEXT, "
               + friendsEntry.COLUMN_OTHER_ENTRIES +" TEXT );";

            db.execSQL(SQL_CREATE_FAMILY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over


    }
}