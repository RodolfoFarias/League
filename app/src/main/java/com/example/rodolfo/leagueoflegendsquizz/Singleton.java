package com.example.rodolfo.leagueoflegendsquizz;

import java.util.ArrayList;

/**
 * Created by Rodolfo on 11/9/2015.
 */
public class Singleton {
    private static Singleton theInstance;
    public ArrayList<Champions> arrayList;
    public int points;
    public static Singleton getInstance() {
        if (theInstance == null) {
            theInstance = new Singleton(); // Create the instance
        }
        return theInstance;
    }
    private Singleton() {} // The constructor is private
}
