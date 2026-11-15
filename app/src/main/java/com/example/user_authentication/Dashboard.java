package com.example.user_authentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.user_authentication.App.CHANNEL_1_ID;
/*jasleen bains
 * T00651489*/

public class Dashboard extends AppCompatActivity {
    private static final long NOTIFICATION_DELAY_MS = 5000;

    private final ArrayList<String> eventList = new ArrayList<>();
    private final ArrayList<String> subeventList = new ArrayList<>();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private NotificationManagerCompat notificationManager;
    private CustomAdapter customAdapter;
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");

        notificationManager = NotificationManagerCompat.from(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        ListView listView = findViewById(R.id.listview);
        customAdapter = new CustomAdapter(this, eventList, subeventList);
        listView.setAdapter(customAdapter);

        floatingActionButton.setOnClickListener(view -> showAddEventDialog());
    }

    private void showAddEventDialog() {
        ViewGroup root = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_dialog_box, root, false);
        Button cancel = dialogView.findViewById(R.id.cancel_button);
        Button save = dialogView.findViewById(R.id.save_button);
        EditText eventName = dialogView.findViewById(R.id.eventname);
        EditText eventDescription = dialogView.findViewById(R.id.eventdescription);
        TextView datePick = dialogView.findViewById(R.id.datepick);

        AlertDialog alertDialog = new AlertDialog.Builder(this).setView(dialogView).create();
        alertDialog.show();

        datePick.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this,
                    (picker, year, month, day) -> datePick.setText(day + "-" + (month + 1) + "-" + year),
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
                    .show();
        });

        save.setOnClickListener(v -> {
            String name = eventName.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Please enter a reminder name", Toast.LENGTH_SHORT).show();
                return;
            }
            String desc = eventDescription.getText().toString().trim()
                    + " (" + datePick.getText().toString().trim() + ")";
            eventList.add(name);
            subeventList.add(desc);
            customAdapter.notifyDataSetChanged();
            showNotification(name, desc);
            alertDialog.dismiss();
        });

        cancel.setOnClickListener(v -> alertDialog.dismiss());
    }

    private void showNotification(String heading, String desc) {
        Intent notificationIntent = new Intent(this, NotificationActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(heading)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // Capture the id now so each reminder gets its own notification.
        final int id = notificationId++;
        handler.postDelayed(() -> notificationManager.notify(id, notification), NOTIFICATION_DELAY_MS);
    }
}
