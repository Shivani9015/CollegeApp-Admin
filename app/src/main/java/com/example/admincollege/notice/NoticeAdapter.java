package com.example.admincollege.notice;

import android.content.Context;
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
import com.example.admincollege.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;
    String Notice="";

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout,parent,false);
    return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {
        NoticeData currentItem = list.get(position);


        holder.deletenoticeTitle.setText(currentItem.getTitle());

        try {
            if(currentItem.getImage()!=null) {
                Picasso.get().load(currentItem.getImage()).into(holder.deleteNoticeImage);
            }
        } catch (Exception e) {
         e.printStackTrace();
        }
       holder.deleteNotice.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               
               DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Notice");
               reference.child(currentItem.getKey()).removeValue()
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                           }
                       });
               notifyItemRemoved(holder.getAdapterPosition());
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        private Button deleteNotice;
       private TextView deletenoticeTitle;
       private ImageView deleteNoticeImage;
        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);

            deleteNotice = itemView.findViewById(R.id.deleteNotice);
            deletenoticeTitle= itemView.findViewById(R.id.deletenoticeTitle);
            deleteNoticeImage= itemView.findViewById(R.id.deleteNoticeImage);
        }
    }
}
