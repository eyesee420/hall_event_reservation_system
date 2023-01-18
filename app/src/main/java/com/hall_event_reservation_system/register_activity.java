package com.hall_event_reservation_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hall_event_reservation_system.databinding.ActivityRegisterBinding;
import com.hall_event_reservation_system.models.create_users_model;

public class register_activity extends AppCompatActivity {

    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    create_users_model createUsersModel;
    ProgressBar progressBar;
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

      progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });


    }

    private void createUser() {


        mAuth.createUserWithEmailAndPassword(binding.emailAddress.getText().toString().trim()
        ,binding.passWord.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser userid = mAuth.getCurrentUser();
                //String doc_id = db.collection("users").document().getId();
                String fullname = binding.fullName.getText().toString().trim();
                String homeaddress = binding.homeAddress.getText().toString().trim();
                String phonenumber = binding.phoneNumber.getText().toString().trim();
                String email  = binding.emailAddress.getText().toString().trim();

                createUsersModel = new create_users_model(userid.getUid(),fullname,homeaddress,phonenumber,email);

                db.collection("users").document(userid.getUid()).set(createUsersModel)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(register_activity.this, "Account Created",
                                        Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });
    }
}