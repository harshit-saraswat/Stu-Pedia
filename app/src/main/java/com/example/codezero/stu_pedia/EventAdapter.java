package com.example.codezero.stu_pedia;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Harshit Saraswat on 18-03-2018.
 */

public class EventAdapter extends ArrayAdapter<EventData> {

public EventAdapter(Activity context, ArrayList<EventData> queries) {
        super(context, 0, queries);
        }


public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.activity_check_events, parent, false);
        }

        EventData currentEvent = getItem(position);

        TextView eventNameTV = (TextView) listItemView.findViewById(R.id.eventnameTV);
        eventNameTV.setText(currentEvent.getEvent_name());

        TextView nameTV = (TextView) listItemView.findViewById(R.id.coordinatorTV);
        nameTV.setText(currentEvent.getName());

        return listItemView;
        }
        }
