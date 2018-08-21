package com.example.michalis.logrerapp.ListOrders;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.michalis.logrerapp.ConfirmOrder;
import com.example.michalis.logrerapp.LoginSuccess;
import com.example.michalis.logrerapp.MainActivity;
import com.example.michalis.logrerapp.R;
import com.example.michalis.logrerapp.ViewProrducts.AdapterP;
import com.example.michalis.logrerapp.ViewProrducts.ListItem;
import com.example.michalis.logrerapp.ViewProrducts.Products;
import com.example.michalis.logrerapp.java;

import java.util.List;

/**
 * Created by Michalis on 26/9/2017.
 */

public class AdapterO extends RecyclerView.Adapter<AdapterO.ViewHolder> {
    private List<ListOrders> listOrders;
    private Context context;
    AlertDialog.Builder builder;
    String Ord_url = "http://mavroforakis1.ddns.net/add_order.php";

    public AdapterO(List<ListOrders> listOrders, Context context) {
        this.listOrders = listOrders;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_orders,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ListOrders listOrder=listOrders.get(position);

        holder.textViewOnumber.setText(listOrder.getOnamber());
        holder.textViewOqnt.setText(listOrder.getQnt());
        holder.textViewStatus.setText(listOrder.getStatus());

       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                //Toast.makeText(context,"VERIFY DELIVERY",Toast.LENGTH_LONG).show();
                //AlertDialog.Builder builder = new AlertDialog.Builder(context);
                java.TAG_ORDER=listOrder.getOnamber();
                //((Activity)context).finish();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(context,ConfirmOrder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);



            }
       });


    }

    @Override
    public int getItemCount() {
        return listOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView textViewOnumber;
        public TextView textViewOqnt;
        public TextView textViewStatus;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewOnumber=(TextView)itemView.findViewById(R.id.textViewOnumber);
            textViewOqnt=(TextView)itemView.findViewById(R.id.textViewOqnt);
            textViewStatus=(TextView)itemView.findViewById(R.id.textViewStatus);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout);


        }
    }


}
