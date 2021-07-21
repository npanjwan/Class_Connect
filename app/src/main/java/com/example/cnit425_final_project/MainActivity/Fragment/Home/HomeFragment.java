/**
 * HomeFragment.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Home;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.cnit425_final_project.R;

public class HomeFragment extends Fragment {

    ImageButton brightSpace, myPurdue, directory, campusMap;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        final Intent intent = new Intent(getContext(), HomeWebActivity.class);

        brightSpace = view.findViewById(R.id.brightspace);
        brightSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("web_name","Brightspace");
                startActivity(intent);
            }
        });

        myPurdue = view.findViewById(R.id.mypurdue);
        myPurdue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("web_name","MyPurdue");
                startActivity(intent);
            }
        });

        directory = view.findViewById(R.id.directory);
        directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("web_name","Directory");
                startActivity(intent);
            }
        });

        campusMap = view.findViewById(R.id.map);
        campusMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("web_name","Campus Map");
                startActivity(intent);
            }
        });

        return view;
    }
}
