package com.example.michalis.logrerapp.ViewProrducts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michalis.logrerapp.R;

import java.util.List;

/**
 * Created by Surface on 12/9/2017.
 */

public class AdapterP extends RecyclerView.Adapter<AdapterP.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public AdapterP(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_products,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem=listItems.get(position);
        holder.textViewName.setText(listItem.getName());
        holder.textViewModel.setText(listItem.getModel());
        holder.textViewNumber.setText(listItem.getNumber());
        //holder.textViewNumber.setTextColor(Color.RED);
        holder.textViewQnt.setText(listItem.getQnt());
        holder.textViewSname.setText(listItem.getSname());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewModel;
        public TextView textViewNumber;
        public TextView textViewQnt;
        public TextView textViewSname;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName=(TextView)itemView.findViewById(R.id.textViewName);
            textViewModel=(TextView)itemView.findViewById(R.id.textViewModel);
            textViewNumber=(TextView)itemView.findViewById(R.id.textViewModel);

            textViewQnt=(TextView)itemView.findViewById(R.id.textViewQnt);

            textViewSname=(TextView)itemView.findViewById(R.id.textViewSname);
        }
    }
}
