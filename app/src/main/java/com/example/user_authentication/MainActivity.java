package com.example.user_authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*jasleen bains
 * T00651489*/
public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mButtonSignup;
    TextView mTextForgotPassword;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbhelper = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.username);
        mTextPassword = findViewById(R.id.password);
        mButtonLogin = findViewById(R.id.loginButton);
        mButtonSignup = findViewById(R.id.registerButton);
        mTextForgotPassword = findViewById(R.id.passwordRS);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                if(!user.isEmpty() && !pwd.isEmpty() ) {
                    Boolean res = dbhelper.checkUser(user, pwd);
                    if (res) {
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        mTextForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = mTextUsername.getText().toString().trim();

                if (dbhelper.checkUser(user))
                {
                    Intent intent = new Intent(getApplicationContext(), Forgot_Password.class);
                    intent.putExtra("User",user);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User not found",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}