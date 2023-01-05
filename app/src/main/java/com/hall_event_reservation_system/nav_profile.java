package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.hall_event_reservation_system.databinding.ActivityNavProfileBinding;
import com.hall_event_reservation_system.databinding.ActivityNavSetScheduleBinding;

public class nav_profile extends AppCompatActivity {

    ActivityNavProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_profile);

        binding = ActivityNavProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNavigationUsers.setSelectedItemId(R.id.nav_profile);
        binding.bottomNavigationUsers.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        startActivity(new Intent(getApplicationContext(), nav_feed.class));
                        overridePendingTransition(0, 0);
                        finish();
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
                        return true;
                }
                return false;
            }
        });
    }
}