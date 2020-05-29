package com.example.ispy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> values;
    private List<String> remValues;
    private  Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        ImageView imgClick;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            imgClick = (ImageView)v.findViewById(R.id.img1);
           // txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        Log.e("value", values.toString());
        Log.e("ERROR", String.valueOf(position));
        remValues.add(position,item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        String test = String.valueOf(position);
        Log.e("ERROR", String.valueOf(position));
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset,List<String> myDevId) {
        values = myDataset;
        remValues = myDevId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = values.get(position);
        final String id = remValues.get(position);

        holder.txtHeader.setText(name);
       holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyAdapter.this,"Registered in DB",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), MapsActivity.class);

                intent.putExtra("id",id);
                //start the activity from the view/context
                v.getContext().startActivity(intent);
            }
        });


        holder.imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
                DatabaseReference dbref;

                //dbref= FirebaseDatabase.getInstance().getReference().child("ChildDetailsRegDB");
                dbref = FirebaseDatabase.getInstance().getReference()
                        .child("ChildDetailsRegDB").child(id);
                dbref.removeValue();

                Intent intent = new Intent(v.getContext(), parlogin.class);

                intent.putExtra("id",id);
                //start the activity from the view/context
                v.getContext().startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return values.size();
    }

}