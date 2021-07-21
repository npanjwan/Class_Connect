/**
 * NewEvent.java
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

public class NewEvent extends AppCompatActivity {

    //Database reference variable declaration
    DatabaseReference reference;
    //Activity Objects variable declaration
    Button submit;
    EditText title, locationType, date, time, location, description;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //Activity objects initialization
        submit = findViewById(R.id.create_event);
        title = findViewById(R.id.event_title);
        locationType = findViewById(R.id.event_location_type);
        date = findViewById(R.id.event_date);
        location = findViewById(R.id.event_location);
        time = findViewById(R.id.event_time);
        description = findViewById(R.id.event_description);

        //database reference initialization
        reference = FirebaseDatabase.getInstance().getReference("Events");

        //submit button on click listener
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating new hash map to send data to firebase
                HashMap<String,String> map = new HashMap<>();
                map.put("date",date.getText().toString());
                map.put("description",description.getText().toString());
                map.put("location",location.getText().toString());
                map.put("location_type",locationType.getText().toString());
                map.put("time",time.getText().toString());
                map.put("title",title.getText().toString());
                map.put("visible","true");
                reference.push().setValue(map);
                title.setText("");
                locationType.setText("");
                date.setText("");
                location.setText("");
                time.setText("");
                description.setText("");
                //Toasting success message
                Toast.makeText(getApplicationContext(),"Event successfully created!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
