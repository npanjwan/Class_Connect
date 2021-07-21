/**
 * NewAnnouncement.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnit425_final_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewAnnouncement extends AppCompatActivity {

    //Activity Object variable declaration
    EditText title, subject, message;
    Button submit;

    //Database Reference variable Declaration
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);

        //Activity objects initialization
        title = findViewById(R.id.announcement_title);
        subject = findViewById(R.id.announcement_subject);
        message = findViewById(R.id.announcement_message);
        submit = findViewById(R.id.new_announcement_button);

        //Database Reference initialization
        reference = FirebaseDatabase.getInstance().getReference("Announcements");
        //submit button on click listener
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //New hashmap to send data to firebase
                HashMap<String,String> map = new HashMap<>();
                map.put("description",message.getText().toString());
                map.put("subject",subject.getText().toString());
                map.put("title",title.getText().toString());
                map.put("visible","true");
                reference.push().setValue(map);
                title.setText("");
                subject.setText("");
                message.setText("");
                //Toasting success messages
                Toast.makeText(getApplicationContext(),"Announcement successfully created!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
