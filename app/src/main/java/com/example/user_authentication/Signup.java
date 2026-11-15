package com.example.user_authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*jasleen bains
 * T00651489*/
public class Signup extends AppCompatActivity {

    private DatabaseHelper dbhelper;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbhelper = new DatabaseHelper(this);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirmpassword);
        Button mButtonRegistered = findViewById(R.id.register);
        Button mLoginText = findViewById(R.id.login);

        // Login screen is already below us on the back stack; just go back to it.
        mLoginText.setOnClickListener(view -> finish());

        mButtonRegistered.setOnClickListener(view -> register());
    }

    private void register() {
        String user = mUsername.getText().toString().trim();
        String pwd = mPassword.getText().toString().trim();
        String cnfPwd = mConfirmPassword.getText().toString().trim();

        if (user.isEmpty() || pwd.isEmpty()) {
            toast("Please enter username and password");
        } else if (!pwd.equals(cnfPwd)) {
            toast("Password does not match");
        } else if (dbhelper.checkUser(user)) {
            toast("User already exists");
        } else if (dbhelper.addUser(user, pwd) > 0) {
            toast("Successfully Registered");
            finish();
        } else {
            toast("Something went wrong Please try again later");
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
