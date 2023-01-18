package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hall_event_reservation_system.databinding.ActivityNavProfileBinding;
import com.hall_event_reservation_system.databinding.ActivityNavSetScheduleBinding;
import com.hall_event_reservation_system.models.create_users_model;

public class nav_profile extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
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



        FirebaseUser userid = mAuth.getCurrentUser();
        DocumentReference docRef = db.collection("users").document(userid.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                create_users_model create = documentSnapshot.toObject(create_users_model.class);
                binding.fullName.setText(create.getFullname());
                binding.homeAddress.setText(create.getHomeaddress());
                binding.phoneNumber.setText(create.getPhonenumber());
                binding.emailAddress.setText(create.getEmailaddress());
            }
        });


        binding.toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.toggleBtn.isChecked()){
                    binding.fullName.setEnabled(true);
                    binding.homeAddress.setEnabled(true);
                    binding.phoneNumber.setEnabled(true);

                }else{
                    binding.fullName.setEnabled(false);
                    binding.homeAddress.setEnabled(false);
                    binding.phoneNumber.setEnabled(false);

                }
            }
        });
    }
}