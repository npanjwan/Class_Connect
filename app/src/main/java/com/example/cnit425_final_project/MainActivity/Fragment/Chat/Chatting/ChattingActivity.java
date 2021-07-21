/**
 * ChattingActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnit425_final_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ChattingActivity extends AppCompatActivity {

    ImageButton send_button;
    EditText send_text;
    RecyclerView recyclerView;
    ArrayList<Chat> chatList;
    ChattingAdapter adapter;
    ProgressBar loading;
    DatabaseReference reference, sendReference;
    FirebaseAuth mAuth;
    String chat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle(getIntent().getStringExtra("display_name").split("\n")[0]);

        loading = findViewById(R.id.loading);
        send_button = findViewById(R.id.send_button);
        send_text = findViewById(R.id.send_text);
        recyclerView = findViewById(R.id.messaging_activity);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAuth = FirebaseAuth.getInstance();
        chatList = new ArrayList<>();

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!send_text.getText().toString().equals("")){
                    sendMessage(mAuth.getCurrentUser().getUid(),
                            getIntent().getStringExtra("display_name"),
                            send_text.getText().toString(),
                            getIntent().getStringExtra("chat_type"));
                    send_text.setText("");
                }
            }
        });

        send_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if(!send_text.getText().toString().equals("")){
                        sendMessage(mAuth.getCurrentUser().getUid(),
                                getIntent().getStringExtra("display_name"),
                                send_text.getText().toString(),
                                getIntent().getStringExtra("chat_type"));
                        send_text.setText("");
                    }
                    return true;
                }
                return false;
            }
        });

        readMessage(getIntent().getStringExtra("chat_type"),getIntent().getStringExtra("display_name"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void sendMessage(String sender, String group, String message, String chat_type){
        String sender_name = mAuth.getCurrentUser().getDisplayName() + "";
        if(chat_type.equals("COURSE")){
            sendReference = FirebaseDatabase.getInstance()
                    .getReference("Messages")
                    .child("COURSE")
                    .child(group.split("\n")[0]);
            HashMap<String,String> map = new HashMap<>();
            map.put("sender",sender);
            map.put("sender_name",sender_name);
            map.put("message",message);
            sendReference.push().setValue(map);
        } else {
            sendReference = FirebaseDatabase.getInstance()
                    .getReference("Messages")
                    .child("Private")
                    .child(chat_name);
            HashMap<String,String> map = new HashMap<>();
            map.put("sender",sender);
            map.put("sender_name",sender_name);
            map.put("message",message);
            sendReference.push().setValue(map);
        }
    }

    public void readMessage(final String chat_type, final String display_name){
        reference = FirebaseDatabase.getInstance().getReference().child("Messages");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    //Course Chat type
                    if(snapshot1.getKey().equals(chat_type) && chat_type.equals("COURSE")){
                        for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                            if(snapshot2.getKey().split("\n")[0].equals(display_name.split("\n")[0])){
                                for(DataSnapshot message : snapshot2.getChildren()){
                                    Log.i("TAG",message.child("message").getValue().toString());
                                    Chat chat = new Chat(message.child("sender").getValue().toString(),
                                                         message.child("message").getValue().toString(),
                                                         snapshot2.getKey().toString(),
                                                         message.child("sender_name").getValue().toString());
                                    chatList.add(chat);
                                    adapter = new ChattingAdapter(getApplicationContext(),chatList);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        }
                    } else {
                        // Private Chat type
                        for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                            if(!snapshot2.getKey().toString().contains(":")){
                                String user_one = snapshot2.getKey().toString().split(" & ")[0];
                                String user_two = snapshot2.getKey().toString().split(" & ")[1];
                                Log.i("USER_ONE", user_one);
                                Log.i("USER_TWO",user_two);
                                if(user_one.equals(mAuth.getCurrentUser().getUid())
                                        || user_two.equals(mAuth.getCurrentUser().getUid())){
                                    chat_name = snapshot2.getKey().toString();
                                    for(DataSnapshot message : snapshot2.getChildren()){
                                        Log.i("TAG",message.child("message").getValue().toString());
                                        Chat chat = new Chat(message.child("sender").getValue().toString(),
                                                message.child("message").getValue().toString(),
                                                snapshot2.getKey().toString(),
                                                message.child("sender_name").getValue().toString());

                                        chatList.add(chat);
                                        adapter = new ChattingAdapter(getApplicationContext(),chatList);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
