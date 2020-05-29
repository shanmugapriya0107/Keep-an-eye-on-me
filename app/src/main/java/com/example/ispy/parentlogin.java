package com.example.ispy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class parentlogin extends AppCompatActivity {

    private Button b4,b5;
    private EditText txtEmailAddress,txtPassword;
    // private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmailAddress = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.pass);
        b4=(Button)findViewById(R.id.b4);
        b5=(Button)findViewById(R.id.b5);
        firebaseAuth = FirebaseAuth.getInstance();

        b4.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                   (firebaseAuth.signInWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                                              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<AuthResult> task) {
                                                      if (task.isSuccessful()) {
                                                          Toast.makeText(parentlogin.this, "Login Successful", Toast.LENGTH_LONG).show();
                                                          SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
                                                          SharedPreferences.Editor ed=sp.edit();
                                                          ed.putString("ParentMail",txtEmailAddress.getText().toString());
                                                          ed.commit();
                                                          Intent intent = new Intent(parentlogin.this, parlogin.class);
                                                          startActivity(intent);
                                                      } else {
                                                          Log.e("ERROR", task.getException().toString());
                                                          Toast.makeText(parentlogin.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                      }
                                                  }
                                              });
                                  }
                              });
                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openparReg();
                    }
                });
            }

            public void openparReg() {
                Intent intent = new Intent(parentlogin.this, parReg.class);
                startActivity(intent);
            }
        }
