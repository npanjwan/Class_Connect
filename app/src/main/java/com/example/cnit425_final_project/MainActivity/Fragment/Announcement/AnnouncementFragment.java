/**
 * AnnouncementAdapter.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Announcement;

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
import com.example.cnit425_final_project.MainActivity.Fragment.Event.Event;
import com.example.cnit425_final_project.MainActivity.Fragment.Event.EventAdapter;
import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnnouncementFragment extends Fragment {

    //Activity object variables declaration
    RecyclerView announcement_list;
    ArrayList<Announcement> arrayList;
    AnnouncementAdapter adapter;
    RecyclerView.LayoutManager viewManager;
    ImageView purdueBackground;
    TextView no_announcement;
    ProgressBar loading;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcements,container,false);
        //activity object initialization
        purdueBackground = view.findViewById(R.id.purdue_background);
        no_announcement = view.findViewById(R.id.no_announcements);
        announcement_list = view.findViewById(R.id.announcements_list);
        loading = view.findViewById(R.id.loading);
        viewManager = new LinearLayoutManager(getContext());
        announcement_list.setLayoutManager(viewManager);
        arrayList = new ArrayList<Announcement>();
        Log.i("UID", FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        getAnnouncement();
        setHasOptionsMenu(true);
        return view;
    }

    //get announcement function to get data from firebase database
    public void getAnnouncement(){
        loading.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference().child("Announcements");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot messages : snapshot.getChildren()){
                    Announcement announcement =
                            new Announcement(messages.child("description").getValue().toString() + "",
                                    messages.child("subject").getValue().toString() + "",
                                    messages.child("title").getValue().toString() + "",
                                    messages.child("visible").getValue().toString() + "");

                    if(announcement.getVisible().equals("true")){
                        arrayList.add(announcement);
                        adapter = new AnnouncementAdapter(getContext(), arrayList);
                        announcement_list.setAdapter(adapter);
                    }
                }
                loading.setVisibility(View.INVISIBLE);

                if(arrayList.size() > 0){
                    purdueBackground.setVisibility(View.VISIBLE);
                    purdueBackground.setAlpha(0.3f);
                    no_announcement.setVisibility(View.INVISIBLE);
                } else {
                    purdueBackground.setVisibility(View.VISIBLE);
                    no_announcement.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Create Options Menu function
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if((FirebaseAuth.getInstance().getCurrentUser().getUid()).equals("YdY9DLhFrihXgUL36Q2U2o8vKJ22")) {
            inflater.inflate(R.menu.add_group, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    //On Option Item Selected function
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getContext(), NewAnnouncement.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
