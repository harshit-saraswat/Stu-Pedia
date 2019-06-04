package com.example.codezero.stu_pedia;

import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Harshit Saraswat on 17-03-2018.
 */

public class JsonParser {

    String mJson;

    JsonParser(String json){
        mJson=json;
    }
    String name = "";
    String username = "";
    String question = "";
    String answer = "";

    String event_name="";
    String venue="";
    String date="";
    String time="";
    String details ="";

    public ArrayList<QueryData> parseJson(){
        ArrayList<QueryData> queries =  new ArrayList<>();
        try {
            JSONObject root = new JSONObject(mJson);
            JSONArray userArray = root.getJSONArray("server_response");


            for (int i = 0; i < userArray.length(); i++) {
                JSONObject jsonObject = userArray.getJSONObject(i);
                name = jsonObject.getString("name");
                username = jsonObject.getString("username");
                question = jsonObject.getString("question");
                answer = jsonObject.getString("answer");

                QueryData queryData = new QueryData(name,username,question,answer);
                queries.add(queryData);
            }
            return queries;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<EventData> parseJsonEvent(){
        ArrayList<EventData> queries =  new ArrayList<>();
        try {
            JSONObject root = new JSONObject(mJson);
            JSONArray userArray = root.getJSONArray("server_response");


            for (int i = 0; i < userArray.length(); i++) {
                JSONObject jsonObject = userArray.getJSONObject(i);
                event_name = jsonObject.getString("event_name");
                name = jsonObject.getString("name");
                venue = jsonObject.getString("venue");
                date = jsonObject.getString("date");
                time=jsonObject.getString("time");
                details=jsonObject.getString("details");

                EventData eventData = new EventData(event_name,name,venue,date,time,details);
                queries.add(eventData);
            }
            return queries;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
