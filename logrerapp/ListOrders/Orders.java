package com.example.michalis.logrerapp.ListOrders;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class Orders extends AppCompatActivity {
   // private static  final String URL_ORDERS="http://mavroforakis1.ddns.net/view_orders.php";

    String http="http://";
    String file1="/view_orders.php";

    String URL_ORDERS=http+java.TAG_HOST+file1;



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    FloatingActionButton AddOrder;
    private List<ListOrders> listOrder;
    TextView username2; //session for log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        getSupportActionBar().setTitle("ORDERS"); //SET PAGE TITLE

        AddOrder=(FloatingActionButton)findViewById(R.id.fabAddorder);
        //floating button order
        AddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Orders.this, AddOrder.class));
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOrder=new ArrayList<>();
        loadRecyclerViewData();

        username2=(TextView)findViewById(R.id.tvUsername);
        username2.setText(java.TAG_NAME);//global var



    }


    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.GET,
                URL_ORDERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("result");

                            for(int i=0;i<array.length();i++){
                                JSONObject jo=array.getJSONObject(i);
                                ListOrders ord = new ListOrders(
                                        jo.getString("pnumber"),
                                        jo.getString("qnt"),
                                        jo.getString("status")

                                );
                                listOrder.add(ord);
                            }
                            adapter = new AdapterO(listOrder,getApplicationContext());
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Orders.this,"Error",Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
