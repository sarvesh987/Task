package com.example.task;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserList extends AppCompatActivity  {
    RecyclerView recyclerView;
    ArrayList<String> title, description, author;
    DBHelper DB;
    MyAdapter adapter;
   // MyAdapter.buttonClickListener buttonClickListener;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ALL POSTS");


        DB=  new DBHelper(this);
        title= new ArrayList<>();
        description= new ArrayList<>();
        author= new ArrayList<>();
        recyclerView= findViewById(R.id.recycleview);


        fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(UserList.this,MainActivity.class));
            }
        });

        adapter= new MyAdapter(this,title, description, author);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();


    }

    private void displaydata()
    {

        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(UserList.this, "no entry exists", Toast.LENGTH_SHORT).show();
            return;
        }else
        {
            while (cursor.moveToNext())
            {
                title.add(cursor.getString(0));
                description.add(cursor.getString(1));
                author.add(cursor.getString(2));
            }

        }

    }

   /* @Override
    public void onButtonClick(int position) {

        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

    }*/
}