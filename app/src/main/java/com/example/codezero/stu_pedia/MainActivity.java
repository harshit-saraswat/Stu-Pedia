package com.example.codezero.stu_pedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView loginTV,signInTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the views.
        loginTV=(TextView)findViewById(R.id.loginTVButton);
        signInTV=(TextView)findViewById(R.id.signInTVButton);

        //Setting On click listener attributes for login and sign in button.
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
