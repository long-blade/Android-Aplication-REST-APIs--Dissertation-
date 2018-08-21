package com.example.michalis.logrerapp.ViewProrducts;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.example.michalis.logrerapp.R;

/**
 * Created by Surface on 12/9/2017.
 */

public class ListItem {
    private  String Name;
    private  String Model;
    private  String Number;
    private  String Qnt;
    private String Sname;

    public ListItem(String name, String model, String number, String qnt,String sname) {
        Name = "NAME: "+name;
        Model = "MODEL: "+model;
        Number = "NUMBER: "+number;
        int qntnumber = Integer.parseInt(qnt);
        if(qntnumber<=5){

            Qnt = "QUANTITY: "+qnt+"(LOW)";


        }else {
            Qnt = " QUANTITY: "+qnt;
        }

        Sname="SHELF: "+sname;

    }

    public String getName() {
        return Name;
    }

    public String getModel() {
        return Model;
    }

    public String getNumber() {
        return Number;
    }

    public String getQnt() {
        return Qnt;
    }

    public String getSname() {
        return Sname;
    }
}
