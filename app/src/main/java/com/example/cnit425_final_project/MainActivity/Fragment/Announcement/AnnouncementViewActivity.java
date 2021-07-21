/**
 * AnnouncementViewActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Announcement;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnit425_final_project.R;

public class AnnouncementViewActivity extends AppCompatActivity {

    //Activity object variable declaration
    TextView announcementTitle, announcementSubject, announcementDescription;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //activity object initialization
        announcementTitle = findViewById(R.id.announcement_title);
        announcementTitle.setText(getIntent().getStringExtra("title"));
        setTitle(getIntent().getStringExtra("title"));

        announcementSubject = findViewById(R.id.announcement_subject);
        announcementSubject.setText(getIntent().getStringExtra("subject"));

        announcementDescription = findViewById(R.id.announcement_description);
        announcementDescription.setText(getIntent().getStringExtra("description"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
