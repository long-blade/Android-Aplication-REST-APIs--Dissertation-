package com.example.michalis.logrerapp.ViewUsers;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michalis.logrerapp.R;
import com.example.michalis.logrerapp.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteUser extends AppCompatActivity {
    //private static  final String URL_USERS="http://mavroforakis1.ddns.net/users.php";
    //String Delete_url="http://mavroforakis1.ddns.net/del_user.php";


    String http="http://";
    String file1="/users.php";
    String file2="/del_user.php";
    String URL_USERS=http+java.TAG_HOST+file1;
    String Delete_url=http+java.TAG_HOST+file2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    Button delete,Refresh;
    EditText Userid;
    String userid;
    AlertDialog.Builder builder;
    TextView username;
    private List<ListUser> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        getSupportActionBar().setTitle("DELETE");


        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listUser=new ArrayList<>();
        loadRecyclerViewData();

        //DELETE ACTION

        builder =new AlertDialog.Builder(DeleteUser.this);
        delete=(Button)findViewById(R.id.btDelete);
        Userid=(EditText)findViewById(R.id.etID);
        Refresh=(Button)findViewById(R.id.btRefresh);
        username=(TextView)findViewById(R.id.tvUsername);
        username.setText(java.TAG_NAME);

        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid=Userid.getText().toString();


                if(userid.equals("")){ //if no id put
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter ID for delete!");
                }else{
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Delete_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try{JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");

                                        if(code.equals("fail"))
                                        {
                                            builder.setTitle("Error");
                                            displayAlert(jsonObject.getString("message"));
                                        }else {
                                            builder.setTitle("Success");
                                            displayAlert(jsonObject.getString("message"));
                                        }

                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DeleteUser.this,"Error Toast",Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("userid",userid);
                            params.put("UserName", java.TAG_NAME);

                            return params;
                        }
                    };
                    Volley.newRequestQueue(DeleteUser.this).add(stringRequest);
                }
            }
        });


    }
    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET,
                URL_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("result");

                            for(int i=0;i<array.length();i++){
                                JSONObject jo=array.getJSONObject(i);
                                ListUser user = new ListUser(
                                        jo.getString("user_id"),
                                        jo.getString("lastname"),
                                        jo.getString("email"),
                                        jo.getString("sname")
                                );
                                listUser.add(user);
                            }
                            adapter = new AdapterU(listUser,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(DeleteUser.this,"Error",Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Userid.setText("");
                //Password.setText("");

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}
