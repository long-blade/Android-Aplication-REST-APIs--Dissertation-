package com.example.michalis.logrerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michalis.logrerapp.ListOrders.Orders;
import com.example.michalis.logrerapp.ViewProrducts.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConfirmOrder extends AppCompatActivity {
   // private static  final String url_conf="http://mavroforakis1.ddns.net/confirm_order.php";

    String http="http://";
    String file1="/confirm_order.php";
    String url_conf=http+java.TAG_HOST+file1;


    AlertDialog.Builder builder2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        builder2 =new AlertDialog.Builder(ConfirmOrder.this);


        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmOrder.this);
        builder.setTitle("Are you sure the order is CONFIRMED");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //TODO
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url_conf,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");

                                    if(code.equals("fail"))
                                    {

                                        builder2.setTitle("Error");
                                        displayAlert(jsonObject.getString("message"));


                                    }
                                    else
                                    {
                                        Intent intent = new Intent(ConfirmOrder.this,Orders.class);
                                        Toast.makeText(ConfirmOrder.this,"ORDER CONFIRMED",Toast.LENGTH_LONG).show();

                                        startActivity(intent);
                                        finish();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmOrder.this,"Error",Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("UserName",java.TAG_NAME);
                        params.put("pnumber",java.TAG_ORDER);


                        return params;
                    }
                };
                Volley.newRequestQueue(ConfirmOrder.this).add(stringRequest);



                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
                //startActivity(new Intent(ConfirmOrder.this,Orders.class));
                Intent intent = new Intent(ConfirmOrder.this,Orders.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void displayAlert(String message)
    {
        builder2.setMessage(message);
        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //UserName.setText("");
                //Password.setText("");
                //finish();
                Intent intent = new Intent(ConfirmOrder.this,Orders.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();

            }
        });
        AlertDialog alertDialog=builder2.create();
        alertDialog.show();
    }


}
