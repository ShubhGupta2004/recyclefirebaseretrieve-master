package com.example.recyclefirebaseretrieve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class completeTask extends AppCompatActivity {

    TextView Tname,Ttask;
    User user;
    String tokens,id;
    DatabaseReference studentDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_task);
        Tname = findViewById(R.id.title);
        Ttask = findViewById(R.id.tokens);

        studentDbRef = FirebaseDatabase.getInstance().getReference().child("Users");

        String name = getIntent().getStringExtra("firstName");
        String date = getIntent().getStringExtra("date");
        String daysleft = getIntent().getStringExtra("daysleft");
        String age = getIntent().getStringExtra("age");
        id = getIntent().getStringExtra("id");

        int c =0;
        if(age == "Very low")
        {
            c = 5;
        }
        else if(age == "Low")
        {
            c=10;
        }
        else if(age == "Medium")
        {
            c=20;
        }
        else
        {
            c=30;
        }
        float t = Float.parseFloat(findDifference(date));
        float s = Float.parseFloat(daysleft);
        float r = t/s;
        int f = (int) (r*10);

        int token = f * c;
        tokens = String.valueOf(token);

        Tname.setText(name);
        Ttask.setText(tokens);



    }
    private void insertStudentData(String tokens,String id){

        assert id != null;
        Toast.makeText(completeTask.this,tokens,Toast.LENGTH_SHORT).show();
        studentDbRef.child(id).child("tokens").setValue(tokens);
        Toast.makeText(completeTask.this,"Data inserted!",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(completeTask.this,userlist.class);
        startActivity(i);
        finish();
    }
    static String findDifference(String end_date)
    {
        long difference_In_Days=0;
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy");

        // Try Class
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = Calendar.getInstance().getTime();
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
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
    public void yee(View view){
        insertStudentData(tokens,id);
        Intent i = new Intent(this, userlist.class);
        startActivity(i);
    }
}