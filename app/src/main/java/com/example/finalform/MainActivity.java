package com.example.finalform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CalendarView c1;
    Button b1,b2;
    EditText e1,e2,e3,e4;
    RadioButton rb1,rb2;
    RadioGroup r1;
    TextView t1;
    boolean vis = false;
    String firstName, lastName, age, sex, dob,selected;
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b1:
                if(!vis){
                    c1.setVisibility(View.VISIBLE);
                }
                else{
                    c1.setVisibility(View.INVISIBLE);
//                    c1.setVisibility(View.VISIBLE);
                }
                vis = !vis;
                break;
            case R.id.b2:
                t1 = findViewById(R.id.t1);
                firstName = e1.getText().toString();
                lastName = e2.getText().toString();
                age = e3.getText().toString();
                dob = e4.getText().toString();
//                rb1.isChecked();
//                if(rb1.getId() == R.id.r1)
//                    sex = "male";
//                else
//                    sex = "female";
//                e1.setText(firstName +" "+lastName+" "+age+" "+sex);

                Intent change = new Intent(this,SecondActivity.class);
                change.putExtra("firstName",firstName);
                change.putExtra("lastName",lastName);
                change.putExtra("age",age);
                change.putExtra("sex",sex);
                change.putExtra("dob",dob);
                change.putExtra("purpose",selected);
                this.startActivity(change);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.c1);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        r1 = findViewById(R.id.r1);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        c1.setVisibility(View.INVISIBLE);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);

        c1.setOnDateChangeListener(
                new CalendarView
                        .OnDateChangeListener() {
                    @Override

                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
                    {
                        String Date
                                = dayOfMonth + "-"
                                + (month + 1) + "-" + year;
                        e4.setText(Date);
                    }
                });

        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rb1.isChecked()) { sex = "male"; }
                if(rb2.isChecked()) { sex = "female"; }
            }
        });



        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.s1);
//create a list of items for the spinner.
        String[] items = new String[]{"weight loss", "weight gain", "fit"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = items[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
}