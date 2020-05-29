package com.example.ispy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.text.TextUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class chidetails extends AppCompatActivity {

    Button b11;
    EditText name,age,uname,pass;
    TextView hiddenVal;
    TextView hiddenUserID;
    RadioButton rb1,rb2;
   // DatabaseReference dbref;
    ChildDetailsRegDB regdb;  //from class RegisterDB

    private FirebaseDatabase mDatabase;
    private DatabaseReference dbref;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chidetails);

        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pass);
        hiddenVal = (TextView) findViewById(R.id.hidden_value);
        hiddenUserID = (TextView) findViewById(R.id.userID);
        b11=(Button)findViewById(R.id.b11);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);


        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname=name.getText().toString();
                String cage=age.getText().toString();
                String cuser=uname.getText().toString();
                String cpass=pass.getText().toString();

                // Get the checked Radio Button ID from Radio Grou[
                int selectedRadioButtonID = rg.getCheckedRadioButtonId();

                // If nothing is selected from Radio Group, then it return -1
                if (selectedRadioButtonID != -1) {

                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                    String selectedRadioButtonText = selectedRadioButton.getText().toString();
                    hiddenVal.setText(selectedRadioButtonText);
                }

                if(TextUtils.isEmpty(cname))
                {
                    Toast.makeText(chidetails.this,"Name field is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cage))
                {
                    Toast.makeText(chidetails.this,"Age number field is empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(cuser))
                {
                    Toast.makeText(chidetails.this,"Username field is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cpass))
                {
                    Toast.makeText(chidetails.this,"Password field is empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                mDatabase = FirebaseDatabase.getInstance();
                dbref = mDatabase.getReference("ChildDetailsRegDB");
                //Setting firebase unique key for Hashmap list
                String userId = dbref.push().getKey();

                SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
                String email = sp.getString("ParentMail", "");
                String userID = sp.getString("userID", "");


                SharedPreferences spid=getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=spid.edit();
                ed.putString("userId",userId);
                ed.commit();


                regdb=new ChildDetailsRegDB();
                dbref= FirebaseDatabase.getInstance().getReference().child("ChildDetailsRegDB");

                //Adding to database
                regdb.setName(cname);
                regdb.setAge(cage);
                regdb.setUname(cuser);
                regdb.setPass(cpass);
                regdb.setSelectedRadioValue(hiddenVal.getText().toString());
                regdb.setEmail(email);
                regdb.setDevID(userId);
               // regdb.setLatitude(0.0);
                //regdb.setLongitude(0.0);

                //dbref.push().setValue(regdb);
                hiddenUserID.setText(userId);


                dbref.child(userId).setValue(regdb);




                Toast.makeText(chidetails.this,"Registered in DB",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(chidetails.this, parlogin.class);
                String st = hiddenUserID.getText().toString();
                intent.putExtra("userID",st);
                startActivity(intent);
                finish();

            }
        });
    }
}
