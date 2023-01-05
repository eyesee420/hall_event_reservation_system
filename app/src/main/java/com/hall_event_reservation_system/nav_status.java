package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.hall_event_reservation_system.adapters.post_models_adapter;
import com.hall_event_reservation_system.adapters.user_status_events_adapater;
import com.hall_event_reservation_system.databinding.ActivityNavStatusBinding;
import com.hall_event_reservation_system.models.post_models;
import com.hall_event_reservation_system.models.users_add_events_model;

public class nav_status extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    user_status_events_adapater adapter ;
    ActivityNavStatusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_nav_status);

        binding = ActivityNavStatusBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNavigationUsers.setSelectedItemId(R.id.nav_status);
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

        recyclerview();
    }

    private void recyclerview() {

        Query query = db.collection("my events");

        FirestoreRecyclerOptions<users_add_events_model> options = new FirestoreRecyclerOptions.Builder<users_add_events_model>()
                .setQuery(query, users_add_events_model.class).build();


        adapter = new user_status_events_adapater(options);
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recycleView.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}