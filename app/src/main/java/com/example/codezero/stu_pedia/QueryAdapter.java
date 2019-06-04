package com.example.codezero.stu_pedia;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit Saraswat on 17-03-2018.
 */

public class QueryAdapter extends ArrayAdapter<QueryData> {

    public QueryAdapter(Activity context, ArrayList<QueryData> queries) {
        super(context, 0, queries);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row_layout, parent, false);
        }

        QueryData currentQuestion = getItem(position);

        TextView nameTV = (TextView) listItemView.findViewById(R.id.nameTV);
        nameTV.setText(currentQuestion.getName());

        TextView usernameTV = (TextView) listItemView.findViewById(R.id.usernameTV);
        usernameTV.setText(currentQuestion.getUsername());

        TextView questionTV = (TextView) listItemView.findViewById(R.id.questionTV);
        questionTV.setText(currentQuestion.getQuestion());

        return listItemView;
    }
}
