/**
 * ChatAdapter.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting.Chat;
import com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting.ChattingActivity;
import com.example.cnit425_final_project.MainActivity.Fragment.Event.EventAdapter;
import com.example.cnit425_final_project.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.DashboardViewHolder>{

    private Context context;
    private ArrayList<Chats> itemList;

    public ChatAdapter (Context context, ArrayList<Chats> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_list_chat_layout,parent,false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder holder, final int position) {
        holder.course_name.setText(itemList.get(position).getDisplay_name());
        holder.course_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChattingActivity.class);
                intent.putExtra("display_name",itemList.get(position).getDisplay_name());
                intent.putExtra("chat_type",itemList.get(position).getChat_type());
                context.startActivity(intent);
            }
        });
        if(itemList.get(position).getChat_type().equals("COURSE")){
            //holder.group_image.setImageResource(R.drawable.group_image_background);
        } else {
            holder.group_image.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class DashboardViewHolder extends RecyclerView.ViewHolder {
        TextView course_name;
        RelativeLayout course_view;
        ImageView group_image;
        public DashboardViewHolder(View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            course_view = itemView.findViewById(R.id.course_enrolled);
            group_image = itemView.findViewById(R.id.group_image);
        }
    }
}
