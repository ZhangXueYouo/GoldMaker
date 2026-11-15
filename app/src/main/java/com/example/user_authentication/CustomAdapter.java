package com.example.user_authentication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
/*jasleen bains
 * T00651489*/

public class CustomAdapter extends BaseAdapter {
    private final List<String> eventList;
    private final List<String> subeventList;
    private final LayoutInflater inflater;

    public CustomAdapter(Context context, List<String> eventList, List<String> subeventList) {
        this.eventList = eventList;
        this.subeventList = subeventList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int i) {
        return eventList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_listview, viewGroup, false);
        }
        TextView headtitle = view.findViewById(R.id.headtitle);
        TextView subtitle = view.findViewById(R.id.subtitle);
        headtitle.setText(eventList.get(i));
        subtitle.setText(subeventList.get(i));
        return view;
    }
}
