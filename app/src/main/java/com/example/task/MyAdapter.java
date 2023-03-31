package com.example.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList title_id, des_id, author_id;

  //  buttonClickListener buttonClickListener;

    public MyAdapter(Context context, ArrayList title, ArrayList description, ArrayList author) {
        this.context = context;
        this.title_id = title;
        this.des_id = description;
        this.author_id = author;
      //  this.buttonClickListener=buttonClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {

        holder.title_id.setText(String.valueOf(title_id.get(position)));
        holder.des_id.setText(String.valueOf(des_id.get(position)));
        holder.author_id.setText(String.valueOf(author_id.get(position)));
        holder.read_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), ViewPost.class);
                intent.putExtra("Title", String.valueOf(title_id.get(position)));
                intent.putExtra("Description", String.valueOf(des_id.get(position)));
                intent.putExtra("Author", String.valueOf(author_id.get(position)));
                view.getContext().startActivity(intent);

            }
        });


        final int[] count = {0};
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    count[0]++;
                    holder.like_no.setText(String.valueOf(count[0]));

            }
        });

        final int[] count2 = {0};
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count2[0]--;
                holder.dislike_no.setText(String.valueOf(count2[0]));

            }
        });



    }

    @Override
    public int getItemCount() {
        return title_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title_id, des_id, author_id,like_no,dislike_no;
        Button read_more_btn;
        ImageView like,dislike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_id = itemView.findViewById(R.id.title_userentry);
            des_id = itemView.findViewById(R.id.email_userentry);
            author_id = itemView.findViewById(R.id.author_userentry);

            read_more_btn=itemView.findViewById(R.id.readmore_btn);
            like=itemView.findViewById(R.id.like_btn);
            like_no=itemView.findViewById(R.id.like_no);
            dislike=itemView.findViewById(R.id.dislike_image);
            dislike_no=itemView.findViewById(R.id.dislike_no);

           /* this.buttonClickListener=buttonClickListener;
            read_more_btn.setOnClickListener(this);*/

        }

        @Override
        public void onClick(View view)
        {
           // buttonClickListener.onButtonClick(getAdapterPosition());
        }
    }

}
