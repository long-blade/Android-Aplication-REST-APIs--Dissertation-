package com.example.michalis.logrerapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddShelf extends AppCompatActivity {
   // String add_url = "http://mavroforakis1.ddns.net/add_shelfs.php";

    String http="http://";
    String file1="/add_shelfs.php";
    String add_url=http+java.TAG_HOST+file1;



    Button add;
    EditText Shname,Sdisc;
    String shname,sdisc,sname2;
    TextView username;
    //FOR THE SPINNER
    private Spinner spinner;
    private ArrayList<String> sname;
    private JSONArray result;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelf);

        getSupportActionBar().setTitle("ADD SHELF"); //SET PAGE TITLE
        add=(Button)findViewById(R.id.button);
        Shname = (EditText) findViewById(R.id.etOpnumber);
        Sdisc = (EditText) findViewById(R.id.etDisc);
        spinner = (Spinner) findViewById(R.id.spStorage);
        username=(TextView)findViewById(R.id.tvUsername);
        username.setText(java.TAG_NAME);
        sname = new ArrayList<String>();//pass the sname
       getData();

        builder = new AlertDialog.Builder(AddShelf.this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shname = Shname.getText().toString();
                sdisc = Sdisc.getText().toString();


                sname2=spinner.getSelectedItem().toString();


                if (shname.equals("") || sdisc.equals("")  || sname2.equals("")) {
                    builder.setTitle("Something went wrong");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input_error");
                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, add_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONObject j = null;

                                    try {

                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");

                                        if(code.equals("fail"))
                                        {
                                            builder.setTitle("Error");
                                            displayAlert(jsonObject.getString("message"));
                                        }
                                        else
                                        {
                                            builder.setTitle("Success");
                                            displayAlert(jsonObject.getString("message"));
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddShelf.this,"Error",Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("sname", shname);
                            params.put("sdisc", sdisc);
                            params.put("storname", sname2);
                            params.put("UserName", java.TAG_NAME);
                            return params;
                        }
                    };
                    MySingleton.getmInstance(AddShelf.this).addToRequestque(stringRequest);


                }


            }
        });



    }


    private void getData() {
        //Creating a string request
        StringRequest stringRequest2 = new StringRequest(java.DATA_URL,
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
        requestQueue.add(stringRequest2);
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
        spinner.setAdapter(new ArrayAdapter<String>(AddShelf.this, android.R.layout.simple_spinner_dropdown_item, sname));

    }







    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Pqnt.setText("");
                //Pnumber.setText("");
                //finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



}
