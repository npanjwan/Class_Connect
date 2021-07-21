/**
 * MainActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cnit425_final_project.MainActivity.Fragment.Announcement.AnnouncementFragment;
import com.example.cnit425_final_project.MainActivity.Fragment.Chat.ChatFragment;
import com.example.cnit425_final_project.MainActivity.Fragment.Event.EventFragment;
import com.example.cnit425_final_project.MainActivity.Fragment.Home.HomeFragment;
import com.example.cnit425_final_project.MainActivity.Fragment.Profile.ProfileFragment;
import com.example.cnit425_final_project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting title
        setTitle("Home");
        //setting initial fragment on
        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_container,new HomeFragment()).commit();

        //Setting bottom navigation options
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        //Setting bottom navigation options item click listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //transiting to specific fragment based on the option selected
                switch (menuItem.getItemId()){
                    //If home button is clicked
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.fragment_container,new HomeFragment()).commit();
                        setTitle("Home");
                        break;
                    //If events button is clicked
                    case R.id.events:
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.fragment_container,new EventFragment()).commit();
                        setTitle("Events");
                        break;
                    //If announcements button is clicked
                    case R.id.announcements:
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.fragment_container,new AnnouncementFragment()).commit();
                        setTitle("Announcements");
                        break;
                    //If chat button is clicked
                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.fragment_container,new ChatFragment()).commit();
                        setTitle("Chat");
                        break;
                    //If profile button is clicked
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.fragment_container,new ProfileFragment()).commit();
                        setTitle("Profile");
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home);

    }
}