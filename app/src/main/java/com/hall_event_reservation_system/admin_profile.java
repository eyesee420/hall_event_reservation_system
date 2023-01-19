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
import com.hall_event_reservation_system.adapters.admin_status_events_adapater;
import com.hall_event_reservation_system.adapters.user_adapter;
import com.hall_event_reservation_system.databinding.ActivityAdminFeedBinding;
import com.hall_event_reservation_system.databinding.ActivityAdminProfileBinding;
import com.hall_event_reservation_system.models.create_users_model;
import com.hall_event_reservation_system.models.users_add_events_model;

public class admin_profile extends AppCompatActivity {

    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    user_adapter adapter;
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

        recyclerview();
    }

    private void recyclerview() {


        Query query = db.collection("users");

        FirestoreRecyclerOptions<create_users_model> options = new FirestoreRecyclerOptions.Builder<create_users_model>()
                .setQuery(query, create_users_model.class).build();


        adapter = new user_adapter(options);
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recycleView.setHasFixedSize(true);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}