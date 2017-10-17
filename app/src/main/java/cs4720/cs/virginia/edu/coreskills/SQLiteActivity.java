package cs4720.cs.virginia.edu.coreskills;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**

 Assignment Notes: For this activity, the user should be able to
 save and load the username and computing ID from SQLite.  Several
 helper classes (DatabaseHelper and Section) are included to make
 this a bit easier.

 NOTE: YOU MUST ONLY SHOW THE LAST RECORD FROM THE DATABASE.  i.e.
 the record that was most recently added!

 Reference:
 https://github.com/marksherriff/StorageExample
 https://developer.android.com/training/basics/data-storage/databases.html

 */

public class SQLiteActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText compIDEditText;

    TextView nameTextView;
    TextView compIDTextView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        compIDEditText = (EditText) findViewById(R.id.compIDEditText);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        compIDTextView = (TextView) findViewById(R.id.compIDTextView);

    }

    public void saveToDatabase(View view) {
        DbHelper myDbHelper = DbHelper.getInstance(getApplicationContext());
        db = myDbHelper.getWritableDatabase();
        ContentValues personInfo = new ContentValues();
        String name = nameEditText.getText().toString();
        String compID = compIDEditText.getText().toString();
        Toast.makeText(this, "name" + name + " compID: " + compID, Toast.LENGTH_SHORT).show();
        personInfo.put(DbHelper.FeedEntry.COLUMN_NAME_NAME, name);
        personInfo.put(DbHelper.FeedEntry.COLUMN_NAME_COMPID, compID);
        db.insert(DbHelper.FeedEntry.TABLE_NAME, null, personInfo);

        // Add code here to save to the database

    }

    public void loadFromDatabase(View view) {
        Cursor cursor = db.rawQuery("select * from " + DbHelper.FeedEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()){
            cursor.moveToLast();
            String name = cursor.getString(cursor.getColumnIndex((DbHelper.FeedEntry.COLUMN_NAME_NAME)));
            String compID = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_COMPID));
            nameTextView.setText(name);
            compIDTextView.setText(compID);
            Toast.makeText(this, "name" + name + " compID: " + compID, Toast.LENGTH_SHORT).show();
        }

    }
}
