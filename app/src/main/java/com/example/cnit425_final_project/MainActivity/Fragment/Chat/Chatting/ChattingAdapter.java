/**
 * ChattingAdapter.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.DashboardViewHolder> {

    Context context;
    ArrayList<Chat> list;
    int MSG_LEFT = 0;
    int MSG_RIGHT = 1;
    FirebaseAuth mAuth;

    public ChattingAdapter (Context context, ArrayList<Chat> list) {
        this.context = context;
        this.list = list;
        mAuth = FirebaseAuth.getInstance();
    }

    public static class DashboardViewHolder extends RecyclerView.ViewHolder {
        TextView show_message;
        TextView show_user;
        public DashboardViewHolder(View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            show_user = itemView.findViewById(R.id.user);
        }
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == MSG_LEFT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false);
            return new DashboardViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false);
            return new DashboardViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder holder, int position) {
        holder.show_message.setText(list.get(position).getMessage());
        holder.show_user.setText(list.get(position).getSender_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getSender().equals(mAuth.getCurrentUser().getUid())){
            return MSG_RIGHT;
        } else {
            return MSG_LEFT;
        }
    }
}
