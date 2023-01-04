package com.example.recyclefirebaseretrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    TextView totoken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totoken = findViewById(R.id.tottoken);


        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int total=0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                    int cost=0;
                    if(user.getTokens()!="") {
                        cost = Integer.parseInt(user.getTokens());
                    }
                    total = total + cost;
                }

                myAdapter.notifyDataSetChanged();
                totoken.setText(Integer.toString(total));
                Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void go(View view){
        Intent i = new Intent(this, addData.class);
        startActivity(i);
    }
}