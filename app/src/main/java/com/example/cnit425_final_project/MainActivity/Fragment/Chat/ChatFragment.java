/**
 * ChatFragment.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat;

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

import com.example.cnit425_final_project.MainActivity.Fragment.Announcement.Announcement;
import com.example.cnit425_final_project.MainActivity.Fragment.Announcement.AnnouncementAdapter;
import com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting.Chat;
import com.example.cnit425_final_project.MainActivity.Fragment.Chat.UserList.UserListActivity;
import com.example.cnit425_final_project.MainActivity.Fragment.Event.Event;
import com.example.cnit425_final_project.MainActivity.Fragment.Event.EventAdapter;
import com.example.cnit425_final_project.MainActivity.MainActivity;
import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    RecyclerView group_list;
    ArrayList<Chats> arrayList;
    ChatAdapter adapter;
    RecyclerView.LayoutManager viewManager;
    ImageView purdueBackground;
    TextView noGroup;
    ProgressBar loading;
    TextView tap_add;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        setHasOptionsMenu(true);

        purdueBackground = view.findViewById(R.id.purdue_background);
        noGroup = view.findViewById(R.id.no_group);
        tap_add = view.findViewById(R.id.tap_add);
        group_list = view.findViewById(R.id.enrolled_groups);
        loading = view.findViewById(R.id.loading);
        viewManager = new LinearLayoutManager(getContext());
        group_list.setLayoutManager(viewManager);
        arrayList = new ArrayList<Chats>();
        auth = FirebaseAuth.getInstance();
        getUserGroups();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_group, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_group){
            Intent intent = new Intent(getContext(), UserListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getUserGroups() {
        loading.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference().child("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot msg : snapshot.getChildren()){
                    if(msg.getKey().equals(auth.getCurrentUser().getUid())){
                        for(DataSnapshot message: msg.getChildren()){
                            Log.i("MESSAGE",message.getKey().toString()); //COURSE OR PRIVATE
                            for(DataSnapshot messages : message.getChildren()) {
                                Chats chats = new
                                        Chats(messages.child("display_name").getValue().toString() + "",
                                        messages.child("chat_type").getValue().toString() + "");
                                arrayList.add(chats);
                                adapter = new ChatAdapter(getContext(), arrayList);
                                group_list.setAdapter(adapter);
                            }
                        }

                    }
                }
                loading.setVisibility(View.INVISIBLE);

                if(arrayList.size() > 0){
                    purdueBackground.setVisibility(View.VISIBLE);
                    purdueBackground.setAlpha(0.3f);
                    noGroup.setVisibility(View.INVISIBLE);
                } else {
                    purdueBackground.setVisibility(View.VISIBLE);
                    noGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
