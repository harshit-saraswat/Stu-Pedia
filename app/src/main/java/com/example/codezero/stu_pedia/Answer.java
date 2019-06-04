package com.example.codezero.stu_pedia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;

public class Answer extends AppCompatActivity {

    TextView quesTV,ansTV;
    EditText ansET,etname,etusername;
    String ques,ans,newAns,username,nameet,usernameet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        quesTV=(TextView)findViewById(R.id.ques);
        ansTV=(TextView)findViewById(R.id.answer);
        ansET=(EditText)findViewById(R.id.ansET);
        etname=(EditText)findViewById(R.id.etname);
        etusername=(EditText)findViewById(R.id.etusername);


        username= getIntent().getExtras().getString("username");
        ques= getIntent().getExtras().getString("question");
        ans= getIntent().getExtras().getString("answer");


        quesTV.setText(ques);
        ansTV.setText(ans);

    }

    public void onSubmit(View view){

        newAns=ansET.getText().toString();
        nameet=etname.getText().toString();
        usernameet=etusername.getText().toString();

        if (newAns.length()!=0 && nameet.length()!=0 && usernameet.length()!=0){
            ans+="\n**********************\n";
            ans+="\nName: "+nameet;
            ans+="\nUsername: "+usernameet;
            ans+="\n\nAns: "+newAns;

            BackgroundTask backgroundWorker = new BackgroundTask();
            backgroundWorker.execute(username,ans);
        }

        else{

            Toast.makeText(this, "Enter the empty fields first.", Toast.LENGTH_SHORT).show();
        }
    }


    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String putAnswer_url = "http://192.168.43.213/put_answer.php";

        @Override
        protected String doInBackground(String... params) {

                try {

                    String username= params[0];
                    String answer= params[1];

                    URL url= new URL(putAnswer_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("answer","UTF-8")+"="+URLEncoder.encode(answer,"UTF-8");
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
            Toast.makeText(Answer.this, result, Toast.LENGTH_SHORT).show();

            if (result.contains("Successfully")){

                Intent intent= new Intent(Answer.this,MainView.class);
                startActivity(intent);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }

}
