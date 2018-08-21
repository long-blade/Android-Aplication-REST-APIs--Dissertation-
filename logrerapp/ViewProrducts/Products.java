package com.example.michalis.logrerapp.ViewProrducts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
import java.util.List;

public class Products extends AppCompatActivity {

    //private static  final String URL_PRODUCTS="http://mavroforakis1.ddns.net/products2.php";
    String http="http://";
    String file1="//products2.php";
    String URL_PRODUCTS=http+ java.TAG_HOST+file1;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    FloatingActionButton AddProduct;
    private List<ListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getSupportActionBar().setTitle("PRODUCTS"); //SET PAGE TITLE

        AddProduct=(FloatingActionButton)findViewById(R.id.fabAddproduct);
        //floating button order
        AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products.this,AddProduct.class));
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listItems=new ArrayList<>();
        loadRecyclerViewData();


    }

    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET,
                URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("result");

                            for(int i=0;i<array.length();i++){
                                JSONObject jo=array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        jo.getString("pname"),
                                        jo.getString("pmodel"),
                                        jo.getString("pnumber"),
                                        jo.getString("pqnt"),
                                        jo.getString("sname")
                                );
                                listItems.add(item);
                            }
                            adapter = new AdapterP(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Products.this,"Error",Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
