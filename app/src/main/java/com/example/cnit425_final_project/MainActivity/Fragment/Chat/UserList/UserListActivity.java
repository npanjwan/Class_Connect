/**
 * UserListActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat.UserList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> list, id;
    UserListAdapter adapter;
    RecyclerView.LayoutManager viewManager;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("User List");

        recyclerView = findViewById(R.id.groups_list);

        mAuth = FirebaseAuth.getInstance();

        list = new ArrayList<String>();
        id = new ArrayList<String>();

        viewManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(viewManager);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot users : snapshot.getChildren()) {
                    if (mAuth.getCurrentUser().getUid().equals(users.child("id").getValue().toString())) {

                    } else {
                        list.add(users.child("name").getValue().toString());
                        id.add(users.child("id").getValue().toString());
                    }
                    adapter = new UserListAdapter(getApplicationContext(), list, id);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
