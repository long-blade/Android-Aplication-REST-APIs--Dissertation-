package com.example.michalis.logrerapp.ListOrders;

/**
 * Created by Michalis on 26/9/2017.
 */

public class ListOrders {

    private  String Onumber;
    private  String Qnt;
    private  String Status;


    public ListOrders(String onumber, String qnt, String status) {
        Onumber = onumber;
        Qnt = "Quantity: "+qnt;
        Status = "Status: "+status;



    }

    public String getOnamber() {
        return Onumber;
    }

    public String getQnt() {
        return Qnt;
    }

    public String getStatus() {
        return Status;
    }



}
