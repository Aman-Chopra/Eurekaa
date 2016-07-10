package com.sourcey.materiallogindemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by win-8 on 08-07-2016.
 */
public class TodoItem {
    @SerializedName("id")
    public String id;
    @SerializedName("email")
    public String email;
    @SerializedName("text")
    public String text;
    @SerializedName("password")
    public String password;


}
