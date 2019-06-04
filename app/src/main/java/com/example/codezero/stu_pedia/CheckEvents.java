package com.example.codezero.stu_pedia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CheckEvents extends AppCompatActivity {

    String JSON_STRING_SCHEME="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute();
    }

    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String json_schemes_url;

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(json_schemes_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING_SCHEME = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JSON_STRING_SCHEME + "\n");

                }

                Log.v("string:",stringBuilder.toString().trim()) ;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            json_schemes_url = "http://192.168.43.213/stupedia_check_event.php";
        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<EventData> events =  new ArrayList<>();

            JsonParser jsonParser = new JsonParser(result);

            events = jsonParser.parseJsonEvent();

            ListView eventsListView = (ListView)findViewById(R.id.list);

            final EventAdapter adapter = new EventAdapter(CheckEvents.this,events);

            eventsListView.setAdapter(adapter);

            eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent =new Intent(CheckEvents.this,eventcheck.class);
                    EventData currentEvent = adapter.getItem(i);
                    intent.putExtra("event_name",currentEvent.getEvent_name());
                    intent.putExtra("name",currentEvent.getName());
                    intent.putExtra("venue",currentEvent.getVenue());
                    intent.putExtra("date",currentEvent.getDate());
                    intent.putExtra("time",currentEvent.getTime());
                    intent.putExtra("details",currentEvent.getDetails());
                    startActivity(intent);
                }
            });

        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
