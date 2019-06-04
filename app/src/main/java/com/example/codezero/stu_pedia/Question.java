package com.example.codezero.stu_pedia;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class Question extends AppCompatActivity {

    EditText nameET,usernameET,questionET,answerET;
    String name,username,question,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        nameET=(EditText)findViewById(R.id.name_TV);
        usernameET=(EditText)findViewById(R.id.username_TV);
        questionET=(EditText)findViewById(R.id.ques_TV);
        answerET=(EditText)findViewById(R.id.ans_TV);
    }

    public void onSubmitQues(View view){

        name=nameET.getText().toString();
        username=usernameET.getText().toString();
        question=questionET.getText().toString();
        answer=answerET.getText().toString();

        if (question.length()==0){
            Toast.makeText(this, "Fill the question field first", Toast.LENGTH_SHORT).show();
        }

        else{

            if(name.length()==0){ name= "anonymous";}
            else if(username.length()==0){username= "anonymous";}
            else if(answer.length()==0){answer="";}
            Log.v("username=",username);
            BackgroundTask backgroundWorker = new BackgroundTask();
            backgroundWorker.execute(name,username,question,answer);
        }
    }

    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String putQues_url = "http://192.168.43.213/put_question.php";

        @Override
        protected String doInBackground(String... params) {

            try {

                String name= params[0];
                String username= params[1];
                String question= params[2];
                String answer= params[3];

                URL url= new URL(putQues_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                +URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(question,"UTF-8")+"&"
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
            Toast.makeText(Question.this, result, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
