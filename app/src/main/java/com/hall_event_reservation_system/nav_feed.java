package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.hall_event_reservation_system.adapters.viewpager_adapter;
import com.hall_event_reservation_system.databinding.ActivityNavFeedBinding;


public class nav_feed extends AppCompatActivity {

    viewpager_adapter viewpagerAdapter;
    ActivityNavFeedBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_nav_feed);


        binding = ActivityNavFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewpagerAdapter = new viewpager_adapter(this);

        binding.viewpager.setAdapter(viewpagerAdapter);







        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {



            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tablayout.getTabAt(position).select();
            }
        });



        binding.bottomNavigationUsers.setSelectedItemId(R.id.nav_feed);
        binding.bottomNavigationUsers.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        return true;

                    case R.id.nav_set_Schedule:
                        startActivity(new Intent(getApplicationContext(), nav_set_Schedule.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_status:
                        startActivity(new Intent(getApplicationContext(), nav_status.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), nav_profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}