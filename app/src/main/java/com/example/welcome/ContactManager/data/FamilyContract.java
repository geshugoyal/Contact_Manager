package com.example.welcome.familyapp.data;


        import android.content.ContentResolver;
        import android.net.Uri;
        import android.provider.BaseColumns;



public final class FamilyContract {


    private FamilyContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.welcome.familyapp";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_FAMILY_MEMBER = "family";
    public static class friendsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FAMILY_MEMBER);


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAMILY_MEMBER;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAMILY_MEMBER;
        public final static String TABLE_NAME = "family";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FAMILY_MEMBER_FIRST_NAME ="first_name";
        public final static String COLUMN_FAMILY_MEMBER_LAST_NAME="last_name";
        public final static String COLUMN_FAMILY_MEMBER_GENDER = "gender";
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        public final static String COLUMN_PHONE_NUMBER ="number";
        public final static String COLUMN_BIRTHDAY ="birthday";
        public final static String COLUMN_EMAILID ="emailid";
        public final static String COLUMN_OTHER_ENTRIES ="others";

        public final static String  COLUMN_FAMILY_MEMBER_RELATION = "relation";



        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }
    }

    }


