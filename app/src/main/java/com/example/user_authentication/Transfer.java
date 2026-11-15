package com.example.user_authentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class Transfer extends AppCompatActivity {

    private EditText setMoney;
    private EditText amount;
    private TextView savingsText;
    private TextView newBalanceText;

    /** Money currently left in savings. */
    private double savings = 0;
    /** Total amount transferred so far. */
    private double transferred = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Button setButton = findViewById(R.id.SetButton);
        Button transferButton = findViewById(R.id.TransferButton);
        setMoney = findViewById(R.id.setMoney);
        amount = findViewById(R.id.Amount);
        savingsText = findViewById(R.id.Savings);
        newBalanceText = findViewById(R.id.newBalance);

        setButton.setOnClickListener(view -> {
            Double value = readAmount(setMoney, "Please enter a value to set.");
            if (value == null) {
                return;
            }
            savings = value;
            refresh();
        });

        transferButton.setOnClickListener(view -> {
            Double value = readAmount(amount, "Please enter a value to transfer.");
            if (value == null) {
                return;
            }
            if (value > savings) {
                showMessage("Insufficient savings for this transfer.");
                return;
            }
            savings -= value;
            transferred += value;
            refresh();
        });
    }

    /** Parses a positive amount from the field, or shows an error and returns null. */
    private Double readAmount(EditText field, String emptyMessage) {
        String str = field.getText().toString().trim();
        if (TextUtils.isEmpty(str)) {
            showMessage(emptyMessage);
            return null;
        }
        double value;
        try {
            value = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid number.");
            return null;
        }
        if (value <= 0) {
            showMessage("Amount must be greater than 0.");
            return null;
        }
        return value;
    }

    private void refresh() {
        savingsText.setText(String.format(Locale.US, "%.2f $", savings));
        newBalanceText.setText(String.format(Locale.US, "%.2f $", transferred));
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Ok", null)
                .show();
    }
}
