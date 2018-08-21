package com.example.michalis.logrerapp.LogFileView;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michalis.logrerapp.R;
import com.example.michalis.logrerapp.ViewProrducts.AdapterP;
import com.example.michalis.logrerapp.ViewProrducts.ListItem;
import com.example.michalis.logrerapp.ViewProrducts.Products;
import com.example.michalis.logrerapp.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogFile extends AppCompatActivity {
    //private static  final String URL_LOG="http://mavroforakis1.ddns.net/logfile.php";

    String http="http://";
    String file1="/logfile.php";

    String URL_LOG=http+ java.TAG_HOST+file1;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListLog> listLogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_file);
        getSupportActionBar().setTitle("LOG VIEW"); //SET PAGE TITLE

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listLogs=new ArrayList<>();
        loadRecyclerViewData();

    }

    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET,
                URL_LOG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("result");

                            for(int i=0;i<array.length();i++){
                                JSONObject jo=array.getJSONObject(i);
                                ListLog log = new ListLog(
                                        jo.getString("loguser"),
                                        jo.getString("action"),
                                        jo.getString("desc"),
                                        jo.getString("date_time")

                                );
                                listLogs.add(log);
                            }
                            adapter = new AdapterL(listLogs,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LogFile.this,"Error",Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
