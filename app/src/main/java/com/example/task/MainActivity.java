package com.example.task;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText title, description , author;
    Button insert, view;
    DBHelper DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.baseline_home_24);
        actionBar.setTitle("CREATE POSTS");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        title=findViewById(R.id.title_main);
        description=findViewById(R.id.des_main);
        author=findViewById(R.id.author_main);

        insert=findViewById(R.id.btn_insert);
        view=findViewById(R.id.btn_view);

        DB=new DBHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UserList.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleTXT = title.getText().toString();
                String desTXT = description.getText().toString();
                String authorTXT = author.getText().toString();

                Boolean  checkInsertData = DB.insertuserdata(titleTXT,desTXT, authorTXT);
                if (checkInsertData)
                {
                    Toast.makeText(MainActivity.this, "new data inserted ", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "new entry not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}