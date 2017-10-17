package cs4720.cs.virginia.edu.coreskills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ErinA on 7/25/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Students";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COMPID = "compId";
        //public static final String COLUMN_NAME_CHECKED = "checked";
    }




    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentList";
    private static DbHelper sInstance;

    //public static final String IS_CHECKED = "is_checked";


    private static final String SQL_CREATE_ENTRIES = " CREATE TABLE " +
            DbHelper.FeedEntry.TABLE_NAME + " (" + DbHelper.FeedEntry._ID + " INTEGER PRIMARY KEY,"
            + FeedEntry.COLUMN_NAME_NAME+ " TEXT," + FeedEntry.COLUMN_NAME_COMPID + " Text)";


    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onDelete(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
    }

    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
        onCreate(db);
    }


    public static synchronized DbHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DbHelper(context.getApplicationContext());
        }
        return sInstance;
    }
}


