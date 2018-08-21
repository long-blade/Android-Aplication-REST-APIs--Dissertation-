package com.example.michalis.logrerapp.LogFileView;

/**
 * Created by Michalis on 28/9/2017.
 */

public class ListLog {

    private  String LogUser;
    private  String Action;
    private  String Desc;
    private  String Date;


    public ListLog(String user, String action, String desc, String date) {
        LogUser = user;
        Action = action;
        Desc = desc;
        Date = date;


    }

    public String getLogUser() {
        return LogUser;
    }

    public String getAction() {
        return Action;
    }

    public String getDesc() {
        return Desc;
    }

    public String getDate() {
        return Date;
    }





}
