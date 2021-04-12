package com.example.user_authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void ReminderView(View v) {
        Intent dashboard = new Intent(this, Dashboard.class);
        startActivity(dashboard);
    }


    public void transferView(View v) {
        Intent transfer = new Intent(this, Transfer.class);
        startActivity(transfer);
    }

    //public void expenseView(View v) {
    //    Intent expense = new Intent(this, Expense.class);
     //   startActivity(expense);
   // }
}