package com.example.user_authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*jasleen bains
 * T00651489*/
public class Forgot_Password extends AppCompatActivity {

    public static final String EXTRA_USER = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        String user = getIntent().getStringExtra(EXTRA_USER);
        if (user == null) {
            finish();
            return;
        }

        DatabaseHelper dbhelper = new DatabaseHelper(this);
        EditText mTextnewpass = findViewById(R.id.mTextnewpass);
        EditText mTextnewpasscon = findViewById(R.id.mTextnewpasscon);
        Button mButtonchangepass = findViewById(R.id.mbuttonchangepass);

        mButtonchangepass.setOnClickListener(view -> {
            String pwd = mTextnewpass.getText().toString().trim();
            String cnfPwd = mTextnewpasscon.getText().toString().trim();
            if (pwd.isEmpty()) {
                toast("Please enter a new password");
            } else if (!pwd.equals(cnfPwd)) {
                toast("Passwords do not match");
            } else if (dbhelper.updateUser(user, pwd) > 0) {
                toast("Password changed");
                finish(); // back to the login screen
            } else {
                toast("Please try again");
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
