package com.example.datacollection;

public class User {
    private int id,age;
    private String name;

    // This class is for convenience.
    public User(int id, String name, int age){   //This constructor takes in the 3 values to be displayed in a row....
        this.id = id;                            // And outputs them separately through each getter function.
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
