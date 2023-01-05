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
import com.hall_event_reservation_system.adapters.admin_post_models_adapter;
import com.hall_event_reservation_system.adapters.post_models_adapter;
import com.hall_event_reservation_system.databinding.ActivityAdminEventsBinding;
import com.hall_event_reservation_system.databinding.ActivityAdminFeedBinding;
import com.hall_event_reservation_system.models.post_models;

public class admin_feed extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ActivityAdminFeedBinding binding;
    admin_post_models_adapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_admin_feed);


        binding = ActivityAdminFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.bottomNavigationAdmin.setSelectedItemId(R.id.nav_feed);
        binding.bottomNavigationAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_feed:

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
                        startActivity(new Intent(getApplicationContext(), admin_profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_feed.this , admin_post_feed.class));

            }
        });

        recyclerview();
    }

    private void recyclerview() {


        Query query = db.collection("post feed");

        FirestoreRecyclerOptions<post_models> options = new FirestoreRecyclerOptions.Builder<post_models>()
                .setQuery(query, post_models.class).build();



        adapter = new admin_post_models_adapter(options);
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