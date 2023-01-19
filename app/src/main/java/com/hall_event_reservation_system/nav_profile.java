package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
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

import java.util.HashMap;
import java.util.Map;

public class nav_profile extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ProgressBar progressBar;
    ActivityNavProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_profile);

        binding = ActivityNavProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

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

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_profile();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_Account();

        }
        });
    }

    private void update_profile() {

        String fullaname = binding.fullName.getText().toString().trim();
        String homeaddress = binding.homeAddress.getText().toString().trim();
        String phonenumber = binding.phoneNumber.getText().toString().trim();



        Map<String, Object> user = new HashMap<>();
        user.put("fullname", fullaname);
        user.put("homeaddress", homeaddress);
        user.put("phonenumber", phonenumber);
        FirebaseUser userid = mAuth.getCurrentUser();
        db.collection("users").document(userid.getUid()).update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(nav_profile.this, "Profile  updated ",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void delete_Account() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete Account");
        alertDialog.setMessage("Are You Sure To Delete This Account ?");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseUser userid = mAuth.getCurrentUser();
                db.collection("users").document(userid.getUid())
                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(nav_profile.this, "Account Deleted", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(nav_profile.this, "ok tnx", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.create().show();



    }
}