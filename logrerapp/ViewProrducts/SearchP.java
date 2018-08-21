package com.example.michalis.logrerapp.ViewProrducts;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michalis.logrerapp.MySingleton;
import com.example.michalis.logrerapp.R;
import com.example.michalis.logrerapp.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchP extends AppCompatActivity {
   // private static  final String URL_SEARCH="http://mavroforakis1.ddns.net/search_product_number.php";
    //String Update_url="http://mavroforakis1.ddns.net/update_product.php";

    String http="http://";
    String file1="/search_product_number.php";
    String file2="/update_product.php";
    String URL_SEARCH=http+java.TAG_HOST+file1;
    String Update_url=http+java.TAG_HOST+file2;


    Button search,update;
    EditText Pnumber,Pname,Pmodel,Pqnt,Pshelf;
    String pnumber,pshelf,pmodel,pname,pqnt;
    Spinner spshelf;
    TextView username;



    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_p);

        builder=new AlertDialog.Builder(SearchP.this);
        search=(Button)findViewById(R.id.btSearch);
        update=(Button)findViewById(R.id.btUpdate);
        Pnumber=(EditText)findViewById(R.id.etOpnumber);

        Pname=(EditText)findViewById(R.id.etName);
        Pmodel=(EditText)findViewById(R.id.etModel);
        Pqnt=(EditText)findViewById(R.id.etOqnt);

        Pshelf=(EditText)findViewById(R.id.etShelf);

        username=(TextView)findViewById(R.id.tvUsername);
        username.setText(java.TAG_NAME);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnumber = Pnumber.getText().toString();

                if (pnumber.equals("")) {
                    builder.setTitle(("Error!"));
                    displayAlert("Enter a Valid Product Number!");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEARCH,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONObject j = null;
                                    try {

                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");

                                        if (code.equals("Product Not Found")) {
                                            builder.setTitle("Error");
                                            displayAlert(jsonObject.getString("msg"));
                                        } else {
                                            Pname.setText(jsonObject.getString("pname"));
                                            Pmodel.setText(jsonObject.getString("pmodel"));
                                            Pqnt.setText(jsonObject.getString("pqnt"));
                                            Pshelf.setText(jsonObject.getString("sname"));
                                            //idshelf=jsonObject.getString("idshelf"); //for the update

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }


                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SearchP.this, "Error", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }

                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("Product_Number",pnumber);
                            //params.put("","");

                            return params;
                        }
                    };
                    Volley.newRequestQueue(SearchP.this).add(stringRequest);
                }

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnumber = Pnumber.getText().toString();
                pmodel=Pmodel.getText().toString();
                pname=Pname.getText().toString();
                pqnt=Pqnt.getText().toString();
                pshelf=Pshelf.getText().toString();

                if(pnumber.equals("")||pmodel.equals("")||pname.equals("")||pqnt.equals("")||pshelf.equals("")){
                    builder.setTitle("Something went wrong");
                    displayAlert("Null String/s");
                }else{
                    StringRequest stringRequest2=new StringRequest(Request.Method.POST, Update_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
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
                           Toast.makeText(SearchP.this,"Error Toast",Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("pnumber",pnumber);

                            params.put("pname",pname);
                            params.put("pmodel",pmodel);
                            params.put("pqnt",pqnt);
                            params.put("pshelf",pshelf);
                            params.put("UserName", java.TAG_NAME);

                            return params;
                        }
                    };
                    MySingleton.getmInstance(SearchP.this).addToRequestque(stringRequest2);

                }


            }
        });

}

    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Pnumber.setText("");


            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }





}
