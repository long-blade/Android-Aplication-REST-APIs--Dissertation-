package com.example.michalis.logrerapp.ViewUsers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michalis.logrerapp.R;

import java.util.List;

/**
 * Created by Michalis on 15/9/2017.
 */

public class AdapterU extends RecyclerView.Adapter<AdapterU.ViewHolder>{
    private List<ListUser>listUsers;
    private Context ctext;

    public AdapterU(List<ListUser> listUsers, Context ctext) {
        this.listUsers = listUsers;
        this.ctext = ctext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v2= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_users,parent,false);
        return new AdapterU.ViewHolder(v2);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListUser listUser=listUsers.get(position);
        
        holder.textViewUserId.setText(listUser.getUserId());
        holder.textViewLastName.setText(listUser.getLastName());
        holder.textViewEmail.setText(listUser.getEmail());
        holder.textViewSname.setText(listUser.getSname());

    }

    @Override
    public int getItemCount() {
        return listUsers.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewUserId;
        public TextView textViewLastName;
        public TextView textViewEmail;
        public TextView textViewSname;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewUserId=(TextView)itemView.findViewById(R.id.textViewUserId);
            textViewLastName=(TextView)itemView.findViewById(R.id.textViewLastName);
            textViewEmail=(TextView)itemView.findViewById(R.id.textViewEmail);
            textViewSname=(TextView)itemView.findViewById(R.id.textViewSname);
        }
    }
}
