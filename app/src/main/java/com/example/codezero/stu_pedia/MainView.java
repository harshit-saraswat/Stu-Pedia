package com.example.codezero.stu_pedia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class MainView extends AppCompatActivity {


    RelativeLayout dbRL,faqRL,ceRL,peRL,auRL,cuRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        //Set the views.
        dbRL=(RelativeLayout)findViewById(R.id.db);
        faqRL=(RelativeLayout)findViewById(R.id.ask);
        ceRL=(RelativeLayout)findViewById(R.id.events);
        peRL=(RelativeLayout)findViewById(R.id.postevents);
        auRL=(RelativeLayout)findViewById(R.id.aboutus);
        cuRL=(RelativeLayout)findViewById(R.id.contactus);

        dbRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainView.this,Dashboard.class);
                startActivity(intent);
            }
        });

        faqRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainView.this,Question.class);
                startActivity(intent);
            }
        });

        auRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainView.this,AboutUs.class);
                startActivity(intent);
            }
        });

        cuRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainView.this,ContactUs.class);
                startActivity(intent);
            }
        });

        ceRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainView.this,CheckEvents.class);
                startActivity(intent);
            }
        });

        peRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainView.this,PostEvent.class);
                startActivity(intent);
            }
        });
    }
}
