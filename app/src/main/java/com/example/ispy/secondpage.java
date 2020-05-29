package com.example.ispy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class secondpage extends AppCompatActivity {

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);

        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openparentlogin();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openchildlogin();
            }
        });


    }
    public void openparentlogin()
    {
        Intent intent=new Intent(this, parentlogin.class);
        startActivity(intent);
    }

    public void openchildlogin()
    {
        Intent intent1=new Intent(this, childlogin.class);
        startActivity(intent1);
    }
}
