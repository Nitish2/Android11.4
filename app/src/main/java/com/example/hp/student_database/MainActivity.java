package com.example.hp.student_database;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaring variables
    TextView textView;
    Student student;
    String[]  firstName, lastName;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding first name
        firstName = new String[]{"Nitish","Karan","Mohit","Varun"};
        //Adding last name
        lastName = new String[]{"Negi","Arora","Khurana","Adhikari"};

        textView = (TextView) findViewById(R.id.textView); // Initializing object by ID
        Log.d("Insert: ", "Inserting .."); // Log.d() method is used to log debug messages.

       database = new Database(this); // Creating database object
        if(database.numberOfRows()>0){
            Log.e("DB","Database already exist."); // Log.e() method is used to log errors.
        }
        else{
            saveDataInDB(); // Saving database
            Log.e("DB ","Data Saved in Database.");// Log.e() method is used to log errors.

        }

        /*
        Handler() is a default constructor associates this handler with the Looper for the
           current thread.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadDataFromDB()) {
                    Log.e("DB ","Data Loaded from Database.");
                }
            }
        }, 0);

    }

    Boolean saveDataInDB() {
        //Saving Student details into the database
        student = new Student();
        for (int i=0;i<firstName.length;i++){
            student.setFirstName(firstName[i]);
            student.setLastName(lastName[i]);
            Log.d("Insert: ", "Inserting ..");
            database.addStudent(student);
        }

        return true;

    }

    Boolean loadDataFromDB() {
        try {
            // Retrieve or load student data from the database
            ArrayList array_list = database.getAllStudent();
            Log.e("Employee Size ", String.valueOf(array_list.size()));

            if(!array_list.isEmpty()){ // if Statement

                /*
                  StringBuilder class is used to create mutable string and
                   it is non-synchronized.
                 */
                StringBuilder stringBuilderFull,stringBuilderStudent;
                stringBuilderFull = new StringBuilder(); // Creating object

                // Creating an ArrayList
                for (int i=0;i<array_list.size();i++){
                    Student stu = (Student) array_list.get(i);
                    stringBuilderStudent=  new StringBuilder().append("\nId : ").append(stu.getId()).append("\n")
                            .append("First Name: ").append(stu.getFirstName()).append("\n")
                            .append("Last Name: ").append(stu.getLastName())
                            .append("\n").append("\n");
                    stringBuilderFull.append(stringBuilderStudent);
                }

                textView.setText(stringBuilderFull);
                Log.e("DB ", "Full details displayed.");
            }else {
                Log.e("DB ", "No Employee available.");
            }

            return true;
        } catch (Exception e) {

            return false;
        }

    }
    }

