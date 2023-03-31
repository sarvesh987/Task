package com.example.task;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPost extends AppCompatActivity {

    TextView title , description, author;
    Button delete,edit;
    Dialog dialog;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.baseline_home_24);
        actionBar.setTitle("VIEW POSTS");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        title= findViewById(R.id.title_view_post);
        description =findViewById(R.id.des_view_post);
        author= findViewById(R.id.author_view_post);
        delete=findViewById(R.id.delete_btn);
        edit=findViewById(R.id.edit_btn);

        Intent data = getIntent();
        String title_data = data.getStringExtra("Title");
        String description_data = data.getStringExtra("Description");
        String author_data = data.getStringExtra("Author");

        title.setText(title_data);
        description.setText(description_data);
        author.setText(author_data);

        dialog= new Dialog(ViewPost.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_border));

        Button yes = dialog.findViewById(R.id.yes_btn);
        Button cancel = dialog.findViewById(R.id.cancel_btn);

        DB=new DBHelper(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from sqlite database and then goes to all post activity i.e UserList.

                String getTitle = title_data.toString();
                DB.deleteData(getTitle);
                startActivity(new Intent(ViewPost.this,UserList.class));
                Toast.makeText(ViewPost.this, "Delete Post", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(view.getContext(), EditPost.class);
                intent.putExtra("Title", title_data);
                intent.putExtra("Description", description_data);
                intent.putExtra("Author", author_data);
                view.getContext().startActivity(intent);

            }
        });

    }
}