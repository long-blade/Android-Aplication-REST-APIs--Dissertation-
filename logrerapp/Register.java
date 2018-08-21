package com.example.michalis.logrerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Register extends AppCompatActivity implements  Spinner.OnItemSelectedListener {
    Button reg_bn;
    Spinner Type;
    EditText Fname, Lname, Email, Username, Password, ConPassword;
    String fname, lname, email, username, password, conpassword, type, sname2,sid;
    String[] items = new String[]{"0", "1"};
    int a;

    //FOR THE SPINNER
    private Spinner spinner;
    private ArrayList<String> sname;
    private JSONArray result;
    private TextView textViewName;
    TextView username2;
    AlertDialog.Builder builder;

   // String reg_url = "http://mavroforakis1.ddns.net/Register.php";

    String http="http://";
    String file1="/Register.php";
    String reg_url=http+java.TAG_HOST+file1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("REGISTER"); //SET PAGE TITLE

        reg_bn = (Button) findViewById(R.id.bRegister);
        Lname = (EditText) findViewById(R.id.etLname);
        Fname = (EditText) findViewById(R.id.etFname);
        Email = (EditText) findViewById(R.id.etEmail);
        Username = (EditText) findViewById(R.id.etUsername);
        Password = (EditText) findViewById(R.id.etPassword);
        ConPassword = (EditText) findViewById(R.id.etPassword2);
        Type = (Spinner) findViewById(R.id.spType);
        sname = new ArrayList<String>();//pass the sname
        textViewName = (TextView) findViewById(R.id.textViewName);
        username2=(TextView)findViewById(R.id.tvUsername);
        username2.setText(java.TAG_NAME);

        //Initializing Spinner sid /sname
        spinner = (Spinner) findViewById(R.id.spID_STOR);
        //Adding an Item Selected Listener to our Spinner!!!!!!!!!!!!!!!!!!!
        // implemented the class Spinner.OnItemSelectedListener to this class iteself passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        Type.setAdapter(adapter);
        //GETING SERVER DATA FOR SPINNER
        getData();

        //sid="";
        //DONE GETING DATA FOR SPINNER
        builder = new AlertDialog.Builder(Register.this);
        reg_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lname = Lname.getText().toString();
                fname = Fname.getText().toString();
                email = Email.getText().toString();
                username = Username.getText().toString();
                password = Password.getText().toString();
                conpassword = ConPassword.getText().toString();
                type = Type.getSelectedItem().toString();
                sname2 = textViewName.getText().toString();

                //sname2=spinner.getSelectedItem().toString();


                if (lname.equals("") || fname.equals("") || email.equals("") || username.equals("") || password.equals("") || conpassword.equals("") || type.equals("") || sname2.equals("")) {
                    builder.setTitle("Something went wrong");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input_error");
                } else {
                    if (!(password.equals(conpassword))) {
                        builder.setTitle("Something went wrong");
                        builder.setMessage("Your Password not mach");
                        displayAlert("input_error");
                    } else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
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
                                params.put("LastName", lname);
                                params.put("FirstName", fname);
                                params.put("UserName", username);
                                params.put("Email", email);
                                params.put("Password", password);
                                params.put("Type", type);
                                params.put("id_Storage", sname2);
                                params.put("UserName2", java.TAG_NAME);
                                return params;
                            }
                        };
                        MySingleton.getmInstance(Register.this).addToRequestque(stringRequest);

                    }
                }


            }
        });

    }

    private void getData() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(java.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(java.JSON_ARRAY);

                            //Calling method getSname to get the Sname from the JSON Array
                            getSname(result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getSname(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the sid to array list
                sname.add(json.getString(java.TAG_SNAME));


                //
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_dropdown_item, sname));

    }

    //Method to get  name of a particular position
    //Doing the same with this method as we did with getName()
    private String getSid(int position) {
        String id = "";
        try {
            JSONObject json = result.getJSONObject(position);
            id = json.getString(java.TAG_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        textViewName.setText(getSid(position));

    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewName.setText(" ");

    }






    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(code.equals("input_error"))
                {
                    Password.setText("");
                    ConPassword.setText("");
                }
                else if(code.equals("reg_success"))
                {
                    finish();
                }
                else if(code.equals("reg_failed"))
                {
                    Lname.setText("");
                    Fname.setText("");
                    Username.setText("");
                    Password.setText("");
                    ConPassword.setText("");
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}
