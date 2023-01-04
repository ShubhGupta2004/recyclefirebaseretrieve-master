package com.example.recyclefirebaseretrieve;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addData extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;
    EditText firstName;
    TextView Heading;
    EditText lastName;
    Spinner age;
    Button btnInsertData;
    DatabaseReference studentDbRef;
    User user = new User();
    static String f1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Heading=findViewById(R.id.heading);
        int width = Heading.getWidth();
        @ColorInt int[] color1 = new int[3];
        color1[0]=getResources().getColor(R.color.c1);
        color1[1]=getResources().getColor(R.color.c2);
        color1[2]=getResources().getColor(R.color.c3);

        Shader shade = new LinearGradient(0f,0f,width,Heading.getTextSize(),color1,null,Shader.TileMode.CLAMP);
        Heading.getPaint().setShader(shade);

        firstName = findViewById(R.id.firstnameET);
        lastName = findViewById(R.id.lastnameET);
        age = findViewById(R.id.ageSpin);
        btnInsertData = findViewById(R.id.btnInsertData);
        studentDbRef = FirebaseDatabase.getInstance().getReference("Users");
        editText=findViewById(R.id.BirthDate);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addData.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudentData();
            }
        });
    }

//    private void calculateTokens()
//    {
//        int c=0;
//
//        String complex = age.getSelectedItem().toString();
//        if(complex=="Very low")
//        {
//            c= 5;
//        }
//        else if(complex=="Low")
//        {
//            c =10;
//        }
//        else if(complex=="Medium")
//        {
//            c= 20;
//        }
//        else
//        {
//            c = 30;
//        }
//
//    }
    static String findDifference(String end_date)
    {
        long difference_In_Days=0;
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Try Class
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = Calendar.getInstance().getTime();
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            assert d2 != null;
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in seconds,


            difference_In_Days
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(difference_In_Time)
                    % 365;



            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

        }

        catch (ParseException e) {
            e.printStackTrace();
        }
        String a = Integer.toString((int)difference_In_Days);

        return a;
    }

    private void insertStudentData(){
        String name = firstName.getText().toString();
        String lastname = lastName.getText().toString();
        String Age = age.getSelectedItem().toString();
        String id = studentDbRef.push().getKey();
        String daysleft = findDifference(f1);
        String token = "";


        user.User1(name,lastname,Age,id,f1,daysleft,token);
        assert id != null;
        studentDbRef.child(id).setValue(user);
        Toast.makeText(addData.this,"Data inserted!",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(addData.this,userlist.class);
        startActivity(i);
        finish();
    }
    public void back(View view){
        Intent i = new Intent(this, userlist.class);
        startActivity(i);
    }
    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
        f1=dateFormat.format(myCalendar.getTime());

    }
}