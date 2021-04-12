package com.example.user_authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.user_authentication.App.CHANNEL_1_ID;
/*jasleen bains
 * T00651489*/

public class Dashboard extends AppCompatActivity{
    FloatingActionButton floatingActionButton;
    ListView listView;
    ArrayList<String> eventList = new ArrayList<>();
    ArrayList<String> subeventList = new ArrayList<>();
    private NotificationManagerCompat notificationManager;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");


        notificationManager = NotificationManagerCompat.from(Dashboard.this);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.headtitle, eventList);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), eventList, subeventList);
        listView.setAdapter(customAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button cancel;
                Button save;
                EditText eventname;
                EditText eventdescription;
                TextView datepick;

                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_dialog_box, viewGroup, false);
                cancel = dialogView.findViewById(R.id.cancel_button);
                save = dialogView.findViewById(R.id.save_button);
                eventname = dialogView.findViewById(R.id.eventname);
                eventdescription = dialogView.findViewById(R.id.eventdescription);
                datepick = dialogView.findViewById(R.id.datepick);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                datepick.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {

                        Calendar c  = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Dashboard.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        datepick.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, year, month, day);
                        datePickerDialog.show();
                    }

                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        eventList.add(eventname.getText().toString().trim());
                        subeventList.add(eventdescription.getText().toString().trim() + "(" + datepick.getText().toString().trim() +")");
                        customAdapter.notifyDataSetChanged();
                        shownotification(eventname.getText().toString().trim(),eventdescription.getText().toString().trim() + "(" + datepick.getText().toString().trim() +")");
                        alertDialog.cancel();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });


                }
        });

    }

    private void shownotification(String heading, String desc) {


        i++;
                int time = 5000;

                Intent notificationIntent = new Intent(Dashboard.this, NotificationActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(Dashboard.this);
                stackBuilder.addParentStack(NotificationActivity.class);
                stackBuilder.addNextIntent(notificationIntent);

                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


                Notification notification2 = new NotificationCompat.Builder(Dashboard.this, CHANNEL_1_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(heading)
                        .setContentText(desc)
                        .setContentIntent(pendingIntent)
                        .build();



                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        notificationManager.notify(i, notification2);
                    }
                }, time);
    }

}