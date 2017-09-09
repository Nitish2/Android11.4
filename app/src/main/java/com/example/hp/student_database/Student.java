package com.example.hp.student_database;


public class Student {

    // Declaring Variables
    int id;
    String firstName;
    String lastName;

    public Student(){

    }

    //Creating getter and setter for the method
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
