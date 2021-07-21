/**
 * UserListAdapter.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat.UserList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.MainActivity.Fragment.Chat.ChatFragment;
import com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting.ChattingActivity;
import com.example.cnit425_final_project.MainActivity.MainActivity;
import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.DashboardViewHolder> {

    ArrayList<String> list;
    ArrayList<String> id;
    Context context;
    DatabaseReference reference;


    public UserListAdapter(Context context, ArrayList<String> list, ArrayList<String> id){
        this.context = context;
        this.list = list;
        this.id = id;
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {
        TextView user_name;
        ImageButton add_user;
        public DashboardViewHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.course_name);
            add_user = itemView.findViewById(R.id.course_info);
        }
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_list_layout,parent,false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DashboardViewHolder holder, final int position) {
        holder.user_name.setText(list.get(position));

        holder.user_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"New Chat Created",Toast.LENGTH_SHORT).show();
                reference = FirebaseDatabase.getInstance().getReference("Chats")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("PRIVATE");
                HashMap<String,String> map = new HashMap<>();
                map.put("chat_type","PRIVATE");
                map.put("display_name",list.get(position));
                map.put("receiver_id",id.get(position));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
