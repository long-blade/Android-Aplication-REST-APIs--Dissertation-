package com.example.michalis.logrerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michalis.logrerapp.LogFileView.LogFile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button login_button;
    EditText UserName,Password,Ddns;
    String username,password,type,ddns,Login_url;

    //String Login_url="http://mavroforakis1.ddns.net/login.php";
    String http="http://";
    String file="/login.php";


    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("LOGIN");

        builder =new AlertDialog.Builder(MainActivity.this);
        login_button=(Button)findViewById(R.id.bLogin);
        UserName=(EditText)findViewById(R.id.etUsername);
        Password=(EditText)findViewById(R.id.etPassword);
        Ddns=(EditText)findViewById(R.id.etDDNS);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=UserName.getText().toString();
                password=Password.getText().toString();
                java.TAG_HOST=Ddns.getText().toString(); //HOST GET

                Login_url= http+java.TAG_HOST+file;


                if(username.equals("")||password.equals(""))
                {
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a Valid username and password");
                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");

                                        if(code.equals("login failed"))
                                        {
                                            builder.setTitle("Login Error");
                                            displayAlert(jsonObject.getString("msg"));
                                        }
                                        else
                                        {
                                            Intent intent = new Intent(MainActivity.this,LoginSuccess.class);
                                           Bundle bundle = new Bundle();
                                            bundle.putString("name",jsonObject.getString("name"));
                                            bundle.putString("email",jsonObject.getString("email"));
                                            bundle.putString("type",jsonObject.getString("type"));
                                            //bundle.putString("Sname",jsonObject.getString("Sname"));

                                            intent.putExtras(bundle);
                                            java.TAG_NAME=bundle.getString("name");
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("UserName",username);
                            params.put("Password",password);


                            return params;
                        }
                    };
                    Volley.newRequestQueue(MainActivity.this).add(stringRequest);
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
                UserName.setText("");
                Password.setText("");

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
