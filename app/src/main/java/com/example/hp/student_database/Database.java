package com.example.hp.student_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


//SQLiteOpenHelper create a helper object to create, open, and/or manage a database.
public class Database extends SQLiteOpenHelper {//Creating a class and extends from SQLiteOpenHelper

    // Declaring database variables
    public static final String DATABASE_NAME ="student.db"; // Initializing Database_Name
    public static final String DATABASE_TABLE_NAME = "Students"; // Initializing Table_Name
    public static final String STUDENT_ID = "id"; // Initializing Student_ID
    public static final String STUDENT_FIRST_NAME = "FirstName"; // Initializing First_Name
    public static final String STUDENT_LAST_NAME = "LasttName"; // Initializing Last_Name

    private static final String CREATE_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                    STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + STUDENT_FIRST_NAME + " text,"
                    + STUDENT_LAST_NAME + " text);";


    Database(Context context){
        super(context,DATABASE_NAME,null,2);
    }

    // onCreate is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase database) {
        //here we creating table in database
        database.execSQL(CREATE_TABLE);

    }

    //onUpgrade method is called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS Students");
        onCreate(database);


    }

    public boolean addStudent(Student student) { //Creating method
        /*
          getWritableDatabase() Create or open a database that will be used for
           reading and writing.
         */
        SQLiteDatabase database = this.getWritableDatabase();
        //ContentValues is used to get the values from database tables
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_FIRST_NAME, student.firstName);
        contentValues.put(STUDENT_LAST_NAME, student.lastName);
        database.insert(DATABASE_TABLE_NAME, null, contentValues);
        return true;
    }



    public int numberOfRows(){ //Creating method
        // getReadableDatabase() Create or open a database.

        SQLiteDatabase database = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(database, DATABASE_TABLE_NAME);
    }

    public ArrayList<Student> getAllStudent() { // Creating an ArrayList
        ArrayList<Student> studentList = new ArrayList<>();
        String selectQuery = "Select * From Students";
        SQLiteDatabase database = this.getWritableDatabase();

        //Cursor exposes results from a query on a SQLiteDatabase.
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) { // If statement
            do { // do while loop to add rows into the list
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));

                //Adding student data into list
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        //Closing databse
        database.close();

        return studentList; // Return student list

    }

}
