/**
 * EventFragment.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Event;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.Admin.NewAnnouncement;
import com.example.cnit425_final_project.Admin.NewEvent;
import com.example.cnit425_final_project.MainActivity.Fragment.Announcement.Announcement;
import com.example.cnit425_final_project.MainActivity.Fragment.Announcement.AnnouncementAdapter;
import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventFragment extends Fragment {

    RecyclerView event_list;
    ArrayList<Event> arrayList;
    EventAdapter adapter;
    RecyclerView.LayoutManager viewManager;
    ImageView purdueBackground;
    TextView no_event;
    ProgressBar loading;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events,container,false);
        purdueBackground = view.findViewById(R.id.purdue_background);
        no_event = view.findViewById(R.id.no_event);
        event_list = view.findViewById(R.id.event_list);
        loading = view.findViewById(R.id.loading);
        viewManager = new LinearLayoutManager(getContext());
        event_list.setLayoutManager(viewManager);
        arrayList = new ArrayList<Event>();
        getEvent();
        setHasOptionsMenu(true);
        return view;
    }

    public void getEvent(){
        loading.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference().child("Events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot messages : snapshot.getChildren()){
                    Event event =
                            new Event(messages.child("date").getValue().toString() + "",
                                      messages.child("description").getValue().toString() + "",
                                      messages.child("location").getValue().toString() + "",
                                      messages.child("time").getValue().toString() + "",
                                      messages.child("title").getValue().toString() + "",
                                      messages.child("location_type").getValue().toString() + "",
                                      messages.child("visible").getValue().toString() + "");
                    if(event.getVisible().equals("true")){
                        arrayList.add(event);
                        adapter = new EventAdapter(getContext(), arrayList);
                        event_list.setAdapter(adapter);
                    }
                }
                loading.setVisibility(View.INVISIBLE);

                if(arrayList.size() > 0){
                    purdueBackground.setVisibility(View.VISIBLE);
                    purdueBackground.setAlpha(0.3f);
                    no_event.setVisibility(View.INVISIBLE);
                } else {
                    purdueBackground.setVisibility(View.VISIBLE);
                    no_event.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if((FirebaseAuth.getInstance().getCurrentUser().getUid()).equals("YdY9DLhFrihXgUL36Q2U2o8vKJ22")) {
            inflater.inflate(R.menu.add_group, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getContext(), NewEvent.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
