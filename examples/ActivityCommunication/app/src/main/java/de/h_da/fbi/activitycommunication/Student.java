package de.h_da.fbi.activitycommunication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String city;
    private final int id;

    public Student(int id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Name: %s, Stadt: %s", name, city);
    }
}
