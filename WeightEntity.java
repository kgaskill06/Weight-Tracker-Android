package com.example.weighttracker;

import android.view.View;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "dailyWeights",
foreignKeys = @ForeignKey(entity = User.class,
parentColumns = "username",
childColumns = "username",
onDelete = ForeignKey.CASCADE),
indices = {@Index(value = {"date", "username"})})
public class WeightEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idNumber;

    private String username;
    private double weight;
    private Date date;

    //default constructor
    public WeightEntity(){

    }
    public WeightEntity(Date date, double weight, String username) {
        this.date = date;
        this.weight = weight;
        this.username = username;

    }

    //Getters
    public int getIdNumber() {
        return idNumber;

    }

    public String getUsername() {
        return username;
    }

    public double getWeight() {
        return weight;

    }

    public Date getDate() {
        return date;
    }

    //Setters

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }




}
