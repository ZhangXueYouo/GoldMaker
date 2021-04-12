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
public class Forgot_Password extends AppCompatActivity {

    EditText mTextnewpass;
    EditText mTextnewpasscon;
    Button mButtonchangepass;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        dbhelper = new DatabaseHelper(this);
        mTextnewpass = findViewById(R.id.mTextnewpass);
        mTextnewpasscon = findViewById(R.id.mTextnewpasscon);
        mButtonchangepass = findViewById(R.id.mbuttonchangepass);
        String user = getIntent().getExtras().getString("User");


        mButtonchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTextnewpass.getText().toString().trim().equals(mTextnewpasscon.getText().toString().trim()))
                {
                    long rest = dbhelper.updateUser(user, mTextnewpass.getText().toString().trim());
                    if (rest > 0) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Forgot_Password.this, "Please try again",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Forgot_Password.this, "Password do not match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}