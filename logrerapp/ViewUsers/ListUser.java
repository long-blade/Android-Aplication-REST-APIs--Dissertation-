package com.example.michalis.logrerapp.ViewUsers;

/**
 * Created by Michalis on 15/9/2017.
 */

public class ListUser {
    private  String UserId;
    private  String LastName;
    private  String Email;
    private  String Sname;

    public ListUser(String userid, String lastname, String email, String sname) {
        UserId = "ID: "+userid;
        LastName = "LNAME: "+lastname;
        Email = "EMAIL: "+email;
        Sname = "STORAGE: "+sname;


    }

    public String getUserId() {
        return UserId;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getSname() {
        return Sname;
    }
}
