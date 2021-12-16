package com.example.weighttracker;
/*
for reference (notes for me)
https://stackoverflow.com/questions/4548816/when-should-we-implement-serializable-interface
https://stackoverflow.com/questions/30460596/jpa-column-unique-true-what-is-really-point-of-having-unique-attribute
 */

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users", indices = {@Index(value = {"username"}, unique = true)})
public class User implements Serializable {

// PrimaryKey assigns username with unique ID number that shares with the relational database
    @PrimaryKey(autoGenerate = true)
    private int idNumber;
    private String username, password;


    // Constructor for username and password parameters
    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    //Default constructor
    public User() {
        username = null;
        password = null;
    }

    // Getters for username and password information

    public int getIdNumber() {
        return idNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
