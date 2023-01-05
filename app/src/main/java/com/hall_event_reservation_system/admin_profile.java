package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.hall_event_reservation_system.databinding.ActivityAdminFeedBinding;
import com.hall_event_reservation_system.databinding.ActivityAdminProfileBinding;

public class admin_profile extends AppCompatActivity {

    ActivityAdminProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        binding = ActivityAdminProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.bottomNavigationAdmin.setSelectedItemId(R.id.nav_profile);
        binding.bottomNavigationAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        startActivity(new Intent(getApplicationContext(), admin_feed.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_events:
                        startActivity(new Intent(getApplicationContext(), admin_events.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_Request:
                        startActivity(new Intent(getApplicationContext(), admin_request.class));
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