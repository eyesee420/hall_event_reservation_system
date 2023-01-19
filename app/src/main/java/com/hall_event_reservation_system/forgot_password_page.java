package com.hall_event_reservation_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.hall_event_reservation_system.databinding.ActivityForgotPasswordPageBinding;

public class forgot_password_page extends AppCompatActivity {

    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ProgressBar progressBar;
    ActivityForgotPasswordPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);


        binding = ActivityForgotPasswordPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        binding.resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_password();
                progressBar.setVisibility(View.VISIBLE);

            }
        });

    }

    private void reset_password() {

        String email = binding.emailAddress.getText().toString().trim();

        if(email.isEmpty()){
            binding.emailAddress.requestFocus();
            binding.emailAddress.setError("Required*");
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(forgot_password_page.this, "Check your Email", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                binding.emailAddress.setText("");
            }
        });
    }
}