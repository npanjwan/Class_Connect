/**
 * AnnouncementAdapter.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Announcement;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.DashboardViewHolder> {

    //Declaring arraylist and context
    Context context;
    ArrayList<Announcement> itemList;

    //AnnouncementAdapter constructor
    public AnnouncementAdapter (Context context, ArrayList<Announcement> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    //DashboardViewHolder function
    public static class DashboardViewHolder extends RecyclerView.ViewHolder {
        TextView announcementTitle;
        TextView announcementSubject;
        TextView announcementDescription;
        RelativeLayout announcementView;
        public DashboardViewHolder(View itemView) {
            super(itemView);
            announcementTitle = itemView.findViewById(R.id.announcement_title);
            announcementSubject = itemView.findViewById(R.id.announcement_subject);
            announcementDescription = itemView.findViewById(R.id.announcement_description);
            announcementView = itemView.findViewById(R.id.announcement_view);
        }
    }

    //OnCreateViewHolder Function
    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_layout,parent,false);
        return new DashboardViewHolder(view);
    }

    //onBindViewHolder Function
    @Override
    public void onBindViewHolder(final DashboardViewHolder holder, int position) {
        final Announcement announcement = itemList.get(position);
        holder.announcementTitle.setText(announcement.getTitle());
        holder.announcementSubject.setText(announcement.getSubject());
        holder.announcementDescription.setText(announcement.getDescription());
        holder.announcementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, AnnouncementViewActivity.class);
                intent.putExtra("title",announcement.getTitle());
                intent.putExtra("subject",announcement.getSubject());
                intent.putExtra("description",announcement.getDescription());
                context.startActivity(intent);
            }
        });
    }

    //getItemCount Function
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
