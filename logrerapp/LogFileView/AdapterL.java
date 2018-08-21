package com.example.michalis.logrerapp.LogFileView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michalis.logrerapp.R;
import com.example.michalis.logrerapp.ViewProrducts.AdapterP;
import com.example.michalis.logrerapp.ViewProrducts.ListItem;

import java.util.List;

/**
 * Created by Michalis on 28/9/2017.
 */

public class AdapterL extends RecyclerView.Adapter<AdapterL.ViewHolder>{
    private List<ListLog> listLogs;
    private Context context;

    public AdapterL(List<ListLog> listLogs, Context context) {
        this.listLogs = listLogs;
        this.context = context;
    }

    @Override
    public AdapterL.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_log,parent,false);
        return new AdapterL.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterL.ViewHolder holder, int position) {
        ListLog listLog=listLogs.get(position);
        holder.textViewLogUser.setText(listLog.getLogUser());
        holder.textViewAction.setText(listLog.getAction());
        holder.textViewDesc.setText(listLog.getDesc());
        holder.textViewDate.setText(listLog.getDate());
        //holder.textViewNumber.setTextColor(Color.RED);

    }
    @Override
    public int getItemCount() {
        return listLogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewLogUser;
        public TextView textViewAction;
        public TextView textViewDesc;
        public TextView textViewDate;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewLogUser=(TextView)itemView.findViewById(R.id.textViewLogUser);
            textViewAction=(TextView)itemView.findViewById(R.id.textViewAction);
            textViewDesc=(TextView)itemView.findViewById(R.id.textViewDesc);

            textViewDate=(TextView)itemView.findViewById(R.id.textViewDate);


        }
    }


}
