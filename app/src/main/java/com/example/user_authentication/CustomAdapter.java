package com.example.user_authentication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
/*jasleen bains
 * T00651489*/

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> eventList = new ArrayList<>();
    ArrayList<String> subeventList = new ArrayList<>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList<String> eventList, ArrayList<String> subeventList) {
        this.context = context;
        this.eventList = eventList;
        this.subeventList = subeventList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView headtitle = (TextView) view.findViewById(R.id.headtitle);
        TextView subtitle = (TextView) view.findViewById(R.id.subtitle);
        headtitle.setText(eventList.get(i));
        subtitle.setText(subeventList.get(i));
        return view;
    }
}
