package com.example.codezero.stu_pedia;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PostEvent extends AppCompatActivity {

    EditText enET,coET,vuET,dtET,teET,dsET;
    String en,co,vu,dt,te,ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        enET=(EditText)findViewById(R.id.enET);
        coET=(EditText)findViewById(R.id.nET);
        vuET=(EditText)findViewById(R.id.vET);
        dtET=(EditText)findViewById(R.id.dET);
        teET=(EditText)findViewById(R.id.tET);
        dsET=(EditText) findViewById(R.id.dsET);
    }

    public void onSubmitEvent(View view){

        en=enET.getText().toString();
        co=coET.getText().toString();
        vu=vuET.getText().toString();
        dt=dtET.getText().toString();
        te=teET.getText().toString();
        ds=dsET.getText().toString();


        if (en.length()==0||co.length()==0||vu.length()==0||dt.length()==0||te.length()==0||ds.length()==0){
            Toast.makeText(this, "Fill the empty fields first.", Toast.LENGTH_SHORT).show();
        }

        else{

            BackgroundTask backgroundWorker = new BackgroundTask();
            backgroundWorker.execute(en,co,vu,dt,te,ds);
        }
    }

    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String putQues_url = "http://192.168.43.213/stupedia_post_event.php";

        @Override
        protected String doInBackground(String... params) {

            try {

                String event_name= params[0];
                String name= params[1];
                String venue= params[2];
                String date= params[3];
                String time= params[4];
                String details= params[5];

                URL url= new URL(putQues_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("event_name","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8")+"&"
                        +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("venue","UTF-8")+"="+URLEncoder.encode(venue,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"
                        +URLEncoder.encode("details","UTF-8")+"="+URLEncoder.encode(details,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                Log.v("result:",result);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(PostEvent.this, result, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
