package com.example.michalis.logrerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michalis.logrerapp.ListOrders.Orders;
import com.example.michalis.logrerapp.LogFileView.LogFile;
import com.example.michalis.logrerapp.ViewProrducts.AddProduct;
import com.example.michalis.logrerapp.ViewProrducts.Products;
import com.example.michalis.logrerapp.ViewProrducts.SearchP;
import com.example.michalis.logrerapp.ViewUsers.DeleteUser;

public class LoginSuccess extends AppCompatActivity  {
    TextView name,email;
    TextView textView;
    String type = "1";
    Button reg_bt,products,search,userDel,addProduct,addShelf,orders,logfile;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish("Are you sure, Log out?");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        getSupportActionBar().setTitle("PANEL");

        name=(TextView)findViewById(R.id.etName);
        email=(TextView)findViewById(R.id.etEmail);
        reg_bt=(Button)findViewById(R.id.btReg);
        products=(Button)findViewById(R.id.btProducts);
        search=(Button)findViewById(R.id.btSearch);
        userDel=(Button)findViewById(R.id.btDeleteUser);
        addProduct=(Button)findViewById(R.id.btAddP);
        addShelf=(Button)findViewById(R.id.btAddS);
        orders=(Button)findViewById(R.id.btOrders);
        logfile=(Button)findViewById(R.id.btLog);

        Bundle bundle=getIntent().getExtras();

        name.setText("Welcome "+bundle.get("name"));
        email.setText("Your Email is: "+bundle.get("email"));
        //textView=(TextView)findViewById(R.id.reg_txt);
        reg_bt.setVisibility(View.INVISIBLE);
        userDel.setVisibility(View.INVISIBLE);
        logfile.setVisibility(View.INVISIBLE);

        if(bundle.get("type").equals(type)){
            reg_bt.setVisibility(View.VISIBLE);
            userDel.setVisibility(View.VISIBLE);
            logfile.setVisibility(View.VISIBLE);
        }
        reg_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSuccess.this,Register.class));
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSuccess.this,Products.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this,SearchP.class));
            }
        });

        userDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this,DeleteUser.class));
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this,AddProduct.class));
            }
        });

        addShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this,AddShelf.class));
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this,Orders.class));
            }
        });

        logfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this,LogFile.class));
            }
        });



    }

    public void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginSuccess.this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }
}
