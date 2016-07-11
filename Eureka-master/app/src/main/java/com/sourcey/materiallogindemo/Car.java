package com.sourcey.materiallogindemo;

/**
 * Created by win-8 on 10-07-2016.
 */
public class Car {
    private String make;

    private int iconID;


    public Car(String make,
               int year,
               int iconID,
               String condition)
    {
        super();
        this.make = make;

        this.iconID = iconID;
    }

    public String getMake()
    {
        return make;
    }


    public int getIconID() {
        return iconID;
    }





}
