package com.example.afinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FeastAdapter extends RecyclerView.Adapter <FeastAdapter.MyViewHoler> {

    private ArrayList<Feast> arrayList;

    public class MyViewHoler extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView date;
        protected TextView coast;


        public MyViewHoler(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.coast = (TextView) itemView.findViewById(R.id.coast);
        }
    }

    public FeastAdapter(ArrayList<Feast> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_raw, parent, false);

        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.date.setText(arrayList.get(position).getDate());
        holder.coast.setText(arrayList.get(position).getCoast());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
