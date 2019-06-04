package com.example.codezero.stu_pedia;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nameET, contactET, emailET, usernameET, passwordET;
    TextView dobTV;
    Spinner genderSP;

    Calendar mDOB;
    int day,month,year;

    Button signInButton;

    String name,dob,gender,contact,email,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Setting the views

        //DOB Text View
        dobTV = (TextView) findViewById(R.id.dobTextView);
        mDOB=Calendar.getInstance();
        day=mDOB.get(Calendar.DAY_OF_MONTH);
        month=mDOB.get(Calendar.MONTH);
        year=mDOB.get(Calendar.YEAR);
        month+=1;

        dobTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog mDatePickerDialog=new DatePickerDialog(SignInActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        int month=monthOfYear+1;
                        dob=dayOfMonth+"/"+month+"/"+year;
                        dobTV.setText(dob);
                    }
                },year,month,day);
                mDatePickerDialog.show();
            }
        });

        //Setting the spinner for gender list.
        genderSP = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSP.setAdapter(adapter);
        genderSP.setOnItemSelectedListener(this);

        //Setting other views.
        nameET = (EditText)findViewById(R.id.nameEditText);
        contactET = (EditText)findViewById(R.id.contactEditText);
        emailET = (EditText)findViewById(R.id.addressEditText);
        usernameET = (EditText)findViewById(R.id.usernameEditText);
        passwordET = (EditText)findViewById(R.id.passwordEditText);
        signInButton = (Button)findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=nameET.getText().toString();
                contact=contactET.getText().toString();
                email=emailET.getText().toString();
                username=usernameET.getText().toString();
                password=passwordET.getText().toString();
                String type = "register";

                if (name.length() == 0 || dob.length() == 0 || gender.length() == 0 || contact.length() == 0 || email.length() == 0 || username.length() == 0 || password.length() == 0) {

                    Toast.makeText(view.getContext(), "One or more fields may be empty.Please fill them.", Toast.LENGTH_LONG).show();

                } else if (contact.length() > 10) {

                    Toast.makeText(view.getContext(), "Contact number shouldn't be more than 10 digits.", Toast.LENGTH_LONG).show();

                } else {
                    BackgroundWorker backgroundWorker = new BackgroundWorker(view.getContext());
                    backgroundWorker.execute(type, name, dob, gender, contact,email, username, password);
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
