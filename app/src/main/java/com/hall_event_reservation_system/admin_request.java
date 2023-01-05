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
import com.hall_event_reservation_system.adapters.admin_status_events__request_adapater;
import com.hall_event_reservation_system.databinding.ActivityAdminRequestBinding;
import com.hall_event_reservation_system.models.users_add_events_model;

public class admin_request extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    admin_status_events__request_adapater adapter ;
    ActivityAdminRequestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request);

        binding = ActivityAdminRequestBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.bottomNavigationAdmin.setSelectedItemId(R.id.nav_Request);
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
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), admin_profile.class));
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


        adapter = new admin_status_events__request_adapater(options);
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