package com.sourcey.materiallogindemo;

/**
 * Created by win-8 on 10-07-2016.
 */
public class Car {
    private String make;
    private int year;
    private int iconID;
    private String condition;

    public Car(String make,
               int year,
               int iconID,
               String condition)
    {
        super();
        this.make = make;
        this.condition = condition;
        this.year = year;
        this.iconID = iconID;
    }

    public String getMake()
    {
        return make;
    }
    public int getYear() {
        return year;
    }

    public int getIconID() {
        return iconID;
    }

    public String getCondition() {
        return condition;
    }



}
