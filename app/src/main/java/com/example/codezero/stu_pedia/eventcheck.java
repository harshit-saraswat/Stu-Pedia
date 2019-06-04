package com.example.codezero.stu_pedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class eventcheck extends AppCompatActivity {

    TextView enTV,coTV,vuTV,dtTV,teTV,dsTV;
    String en,co,vu,dt,te,ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventcheck);

        enTV=(TextView)findViewById(R.id.enTV);
        coTV=(TextView)findViewById(R.id.coTV);
        vuTV=(TextView)findViewById(R.id.vuTV);
        dtTV=(TextView)findViewById(R.id.dtTV);
        teTV=(TextView)findViewById(R.id.teTV);
        dsTV=(TextView)findViewById(R.id.dsTV);

        en=getIntent().getExtras().getString("event_name");
        co=getIntent().getExtras().getString("name");
        vu=getIntent().getExtras().getString("venue");
        dt=getIntent().getExtras().getString("date");
        te=getIntent().getExtras().getString("time");
        ds=getIntent().getExtras().getString("details");

        enTV.setText(en);
        coTV.setText(co);
        vuTV.setText(vu);
        dtTV.setText(dt);
        teTV.setText(te);
        dsTV.setText(ds);

    }
}
