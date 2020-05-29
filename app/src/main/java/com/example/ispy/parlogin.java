package com.example.ispy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class parlogin extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseUser muser;
    FloatingActionButton fb2;
    DatabaseReference db;
    //ArrayList<ChildDetailsRegDB> list;
    ArrayList<String> list;
    ArrayList<String> devIdList;
    FirebaseAuth auth;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;
    private String retrievedUserID;

    ArrayList input =new ArrayList();
    String value ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parlogin);

        fb2 = (FloatingActionButton) findViewById(R.id.fb2);
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ChildDetailsRegDB");
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<String>();

        devIdList = new ArrayList<String>();

        SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
        value = sp.getString("ParentMail", "");
/*

        }*/
            Toast.makeText(parlogin.this, "Login Successful", Toast.LENGTH_LONG).show();


            mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    ChildDetailsRegDB user = childDataSnapshot.getValue(ChildDetailsRegDB.class);
                    if(value!=null && user.getEmail()!=null && user.getEmail().equals(value))
                    {
                        list.add(user.getName());
                        devIdList.add(user.getDevID());
                    }
                }

                mAdapter = new MyAdapter(list,devIdList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(parlogin.this,chidetails.class);
                startActivity(i);
            }
        });


    }
}
