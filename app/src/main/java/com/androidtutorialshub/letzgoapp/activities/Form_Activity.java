package com.androidtutorialshub.letzgoapp.activities;

import android.app.DatePickerDialog;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.androidtutorialshub.letzgoapp.R;
import com.androidtutorialshub.letzgoapp.model.Travel_Details;
import com.androidtutorialshub.letzgoapp.sql.DatabaseHelper;

public class Form_Activity extends AppCompatActivity {
    private final AppCompatActivity activity = Form_Activity.this;
    private DatabaseHelper databaseHelper;
    private Travel_Details td;
    Calendar myCalendar;
    Calendar myCalendar1;
    EditText edittext;
    Button letzgo;
    private EditText textDate;
    private EditText textDate1;
    private EditText textLocation;
    private RelativeLayout rl;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    DatePickerDialog d1;
    DatePickerDialog d2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_);
        getSupportActionBar().hide();
        edittext= findViewById(R.id.edit_text);
        letzgo=findViewById(R.id.letzgo);
        myCalendar=Calendar.getInstance();
        myCalendar1=Calendar.getInstance();
        databaseHelper = new DatabaseHelper(activity);
        td=new Travel_Details();
        textDate=findViewById(R.id.edit_text);
        textDate1=findViewById(R.id.edit_text2);
        textLocation=findViewById(R.id.editText);
        rl=findViewById(R.id.rl);
        radioGroup=findViewById(R.id.rbg);


        letzgo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();

            }

        };
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();

            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                d1=new DatePickerDialog(Form_Activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                        d1.show();
            }
        });
        textDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                d2=new DatePickerDialog(Form_Activity.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH));
                        d2.show();
            }
        });
    }
    public void postDataToSQLite(){
        td.setStart_date(textDate.getText().toString().trim());
        int selectedId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(selectedId);
        td.setTrip_type(radioButton.getText().toString().trim());
        td.setLoc(textLocation.getText().toString().trim());

        databaseHelper.addDetails(td);
        databaseHelper.addSeasons();
        databaseHelper.addTag();
        databaseHelper.addType();

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(rl, getString(R.string.details_save), Snackbar.LENGTH_LONG).show();
        Intent listIntent = new Intent(activity, List_Activity.class);
        listIntent.putExtra("SEASON", td.getStart_date());
        listIntent.putExtra("TYPE", td.getTrip_type());
        emptyInputEditText();
        startActivity(listIntent);
    }
    private void emptyInputEditText(){
        textLocation.setText(null);
        textDate.setText(null);
        textDate1.setText(null);
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel1() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textDate1.setText(sdf.format(myCalendar1.getTime()));
    }
}
