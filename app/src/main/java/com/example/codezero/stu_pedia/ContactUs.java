package com.example.codezero.stu_pedia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUs extends AppCompatActivity {

    EditText etName,etAadhaar,etEmail,etQuery;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //Recognise the views and buttons.
        etName =(EditText)findViewById(R.id.etname);
        etAadhaar =(EditText)findViewById(R.id.etaadhaar);
        etEmail =(EditText)findViewById(R.id.etemail);
        etQuery =(EditText)findViewById(R.id.etquery);
        sendBtn =(Button)findViewById(R.id.sendbutton);

        //Send button on click listener to send an email intent.
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "Name: "+etName.getText().toString();
                data+="\nAadhaar: "+etAadhaar.getText().toString();
                data+="\nEmail: "+etEmail.getText().toString();
                data+="\nQuery: "+etQuery.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contacting for a query.");
                intent.putExtra(Intent.EXTRA_EMAIL,"saraswat2204@gmail.com");
                intent.putExtra(Intent.EXTRA_TEXT, data);
                startActivity(Intent.createChooser(intent,"Send Mail"));
            }
        });

    }
}
