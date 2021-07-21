/**
 * EventAdapter.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Event;
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
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.DashboardViewHolder> {

    Context context;
    ArrayList<Event> itemList;

    public EventAdapter (Context context, ArrayList<Event> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public EventAdapter.DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout,parent,false);
        return new DashboardViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onBindViewHolder(EventAdapter.DashboardViewHolder holder, int position) {
        final Event event = itemList.get(position);
        holder.eventDate.setText(event.getDate());
        holder.eventTitle.setText(event.getTitle());
        holder.eventTime.setText(event.getTime());
        holder.eventDescription.setText(event.getDescription());
        holder.eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventViewActivity.class);
                intent.putExtra("event_date",event.getDate());
                intent.putExtra("event_time",event.getTime());
                intent.putExtra("event_title",event.getTitle());
                intent.putExtra("event_description",event.getDescription());
                intent.putExtra("event_location",event.getLocation());
                intent.putExtra("event_location_type",event.getLocation_type());
                context.startActivity(intent);
            }
        });
    }

    public static class DashboardViewHolder extends RecyclerView.ViewHolder {
        TextView eventDate, eventTime, eventTitle, eventDescription;
        RelativeLayout eventView;
        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            eventDate = itemView.findViewById(R.id.event_date);
            eventTime = itemView.findViewById(R.id.event_time);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventDescription = itemView.findViewById(R.id.event_description);
            eventView = itemView.findViewById(R.id.event_view);
        }
    }
}
