package com.example.recyclefirebaseretrieve;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;



    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        holder.age.setText(user.getAge());
        holder.date.setText(user.getDate());
        holder.tokens.setText(user.getTokens());


        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        final String age = user.getAge();
        final String date = user.getDate();
        final String daysleft = user.getDaysleft();
        final String id = user.getID();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), completeTask.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("age", age);
                intent.putExtra("date", date);
                intent.putExtra("daysleft", daysleft);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }

        });
//        holder.daysleft.setText(user.getDaysleft());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName, lastName, age,date,daysleft,tokens;
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tvfirstName);
            lastName = itemView.findViewById(R.id.tvlastName);
            age = itemView.findViewById(R.id.tvage);
            date = itemView.findViewById(R.id.date);
            tokens = itemView.findViewById(R.id.tokener);
            view = itemView;
//            daysleft = itemView.findViewById(R.id.daysleft);

        }
    }

}