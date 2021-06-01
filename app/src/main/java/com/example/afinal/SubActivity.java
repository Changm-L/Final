package com.example.afinal;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SubActivity extends FragmentActivity {

    TabLayout tabs;

    HomeFragment fragment_home;
    FeastFragment fragment_feast;
    ReviewFragment fragment_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        fragment_home = new HomeFragment();
        fragment_feast = new FeastFragment();
        fragment_review = new ReviewFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment_home).commit();

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("홈"));
        tabs.addTab(tabs.newTab().setText("공연•행사"));
        tabs.addTab(tabs.newTab().setText("리뷰"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0)
                    selected = fragment_home;
                else if(position == 1)
                    selected = fragment_feast;
                else if(position == 2)
                    selected = fragment_review;

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}