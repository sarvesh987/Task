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

public class EditPost extends AppCompatActivity {

    DBHelper DB;
    EditText title, des , author;
    Button save_changes;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.baseline_home_24);
        actionBar.setTitle("EDIT POSTS");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        title=findViewById(R.id.title_edit_post);
        des=findViewById(R.id.des_edit_post);
        author=findViewById(R.id.author_edit_post);
        save_changes=findViewById(R.id.save_changes);

        Intent data = getIntent();
        String title_data = data.getStringExtra("Title");
        String description_data = data.getStringExtra("Description");
        String author_data = data.getStringExtra("Author");

        title.setText(title_data);
        des.setText(description_data);
        author.setText(author_data);

        DB=new DBHelper(this);
        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  DB.updateData(title_data,description_data,author_data);
                Toast.makeText(EditPost.this, "update data successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }
}