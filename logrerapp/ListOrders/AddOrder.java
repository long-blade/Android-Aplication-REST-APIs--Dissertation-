package com.example.michalis.logrerapp.ListOrders;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.michalis.logrerapp.LoginSuccess;
import com.example.michalis.logrerapp.MainActivity;
import com.example.michalis.logrerapp.MySingleton;
import com.example.michalis.logrerapp.R;
import com.example.michalis.logrerapp.Register;
import com.example.michalis.logrerapp.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddOrder extends AppCompatActivity {
   // String Ord_url = "http://mavroforakis1.ddns.net/add_order.php";

    String http="http://";
    String file1="/add_order.php";

    String Ord_url=http+java.TAG_HOST+file1;



    Button order_button;
    TextView username2;
    EditText Onumber,Oquantity;
    String onumber,oqnt,username;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        getSupportActionBar().setTitle("ORDER PLACEMENT"); //SET PAGE TITLE

        order_button=(Button)findViewById(R.id.btOrder);
        username2=(TextView)findViewById(R.id.tvUsername);
        Onumber=(EditText)findViewById(R.id.etOpnumber);
        Oquantity=(EditText)findViewById(R.id.etOqnt);
        //get users name
        username2.setText(java.TAG_NAME);

        builder = new AlertDialog.Builder(AddOrder.this);

        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=username2.getText().toString();
                onumber=Onumber.getText().toString();
                oqnt=Oquantity.getText().toString();

                if(onumber.equals("") || oqnt.equals("")){
                    builder.setTitle("Something went wrong");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input_error");
                }else{

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Ord_url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonArray = new JSONArray(response);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code = jsonObject.getString("code");
                                            String message = jsonObject.getString("message");
                                            builder.setTitle("Server Response..");
                                            builder.setMessage(message);
                                            displayAlert(code);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("pnumber", onumber);
                                params.put("qnt", oqnt);
                                params.put("UserName", username);

                                return params;
                            }
                        };
                        MySingleton.getmInstance(AddOrder.this).addToRequestque(stringRequest);


                }


            }
        });




    }

    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(code.equals("input_error"))
                {
                    Oquantity.setText("");
                    Onumber.setText("");
                }
                else if(code.equals("success"))
                {
                    //finish();
                    Intent intent = new Intent(AddOrder.this,Orders.class);
                    Bundle bundle = new Bundle();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else if(code.equals("Fail"))
                {
                    Onumber.setText("");


                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
