/**
 * EventViewActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Event;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnit425_final_project.R;

import java.util.Calendar;
import java.util.Date;

public class EventViewActivity extends AppCompatActivity {

    TextView eventTitle, eventLocationType, eventTime, eventDate, eventLocation, eventDescription;
    Button add_to_cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle(getIntent().getStringExtra("event_title"));

        eventTitle = findViewById(R.id.title);
        eventTitle.setText(getIntent().getStringExtra("event_title"));

        eventLocationType = findViewById(R.id.type);
        eventLocationType.setText(getIntent().getStringExtra("event_location_type"));

        eventTime = findViewById(R.id.time);
        eventTime.setText(getIntent().getStringExtra("event_time"));

        eventDate = findViewById(R.id.date);
        eventDate.setText(getIntent().getStringExtra("event_date"));

        eventLocation = findViewById(R.id.location);
        eventLocation.setText(getIntent().getStringExtra("event_location"));

        eventDescription = findViewById(R.id.description);
        eventDescription.setText(getIntent().getStringExtra("event_description"));

        eventLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        add_to_cal = findViewById(R.id.add_event);
        add_to_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEvent();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void createEvent(){
        Intent calendarEvent = new Intent(Intent.ACTION_EDIT);
        Calendar date = Calendar.getInstance();
        date.set(Integer.parseInt(eventDate.getText().toString().split("/")[2]),
                Integer.parseInt(eventDate.getText().toString().split("/")[1]),
                Integer.parseInt(eventDate.getText().toString().split("/")[0]),
                0,
                0);
        calendarEvent.setType("vnd.android.cursor.item/event");
        calendarEvent.putExtra(CalendarContract.Events.TITLE,eventTitle.getText().toString());
        calendarEvent.putExtra(CalendarContract.Events.ALL_DAY,false);
        calendarEvent.putExtra(CalendarContract.Events.DTSTART,date.getTimeInMillis());
        calendarEvent.putExtra(CalendarContract.Events.DESCRIPTION,eventDescription.getText().toString());
        calendarEvent.putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation.getText().toString());
        startActivity(calendarEvent);
    }
}
