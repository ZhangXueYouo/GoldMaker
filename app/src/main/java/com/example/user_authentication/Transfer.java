package com.example.user_authentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class Transfer extends AppCompatActivity {

    private EditText setMoney;
    private EditText Amount;
    private Button Set, TransferButton;
    private TextView Savings;
    private TextView newBalance;

    ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Set = findViewById(R.id.SetButton);
        TransferButton = findViewById(R.id.TransferButton);
        setMoney = findViewById(R.id.setMoney);
        Amount = findViewById(R.id.Amount);
        Savings = findViewById(R.id.Savings);
        newBalance = findViewById(R.id.newBalance);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ArrayList<Integer> values = new ArrayList<Integer>();

        values.add(0);
        values.add(0);
        values.add(0);

        Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double temp = 0;

                String str = setMoney.getText().toString().trim();//trim() to remove the first empty space
                if (TextUtils.isEmpty(str)) {
                    builder.setMessage("Please enter a value to set.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog ad = builder.create();
                    ad.show();

                } else {
                    temp = Double.parseDouble(setMoney.getText().toString());
                    Savings.setText(temp + " $");
                    int moneyset = Integer.parseInt(setMoney.getText().toString());
                    values.set(0, moneyset);
                }
            }
        });

        TransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double temp2 = 0;

                String str = Amount.getText().toString().trim();//trim() to remove the first empty space
                if (TextUtils.isEmpty(str)) {
                    builder.setMessage("Please enter a value to transfer .");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog ad = builder.create();
                    ad.show();

                } else {
                    //temp2 = Double.parseDouble(Amount.getText().toString());
                    double transAmount = Double.parseDouble(Amount.getText().toString());
                    int Intrans = (int) transAmount;
                    values.set(1, Intrans);
                    values.set(2, values.get(2) + values.get(1));
                    Savings.setText(values.get(0) - values.get(1) + " $");
                    values.set(0, values.get(0) - values.get(1));
                    newBalance.setText(values.get(2) + " $");
                }

            }
        });
    }
}