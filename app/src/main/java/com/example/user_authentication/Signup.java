package com.example.user_authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*jasleen bains
 * T00651489*/
public class Signup extends AppCompatActivity {

    DatabaseHelper dbhelper;
    EditText mUsername;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mButtonRegistered;
    Button mLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        dbhelper = new DatabaseHelper(this);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirmpassword);
        mButtonRegistered = findViewById(R.id.register);
        mLoginText = findViewById(R.id.login);

        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mButtonRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mUsername.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();
                String cnf_pwd = mConfirmPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd)) {
                    long val = dbhelper.addUser(pwd,user);
                    if (val > 0) {
                        Toast.makeText(Signup.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Signup.this, MainActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(Signup.this, "Something went wrong Please try again later", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}