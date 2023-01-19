package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hall_event_reservation_system.databinding.ActivityAdminRequestBinding;
import com.hall_event_reservation_system.databinding.ActivityLoginBinding;

public class login_activity extends AppCompatActivity {
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ProgressBar progressBar;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        binding.createAcctText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,register_activity.class));
            }
        });
        binding.adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this , admin_login.class));
            }
        });
        binding.forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this , forgot_password_page.class));
            }
        });
    }

    private void login() {
            String email = binding.emailAddress.getText().toString().trim();
            String password = binding.passWord.getText().toString().trim();
            if (email.isEmpty()){
                binding.emailAddress.requestFocus();
                binding.emailAddress.setError("Required*");
                return;
            }
        if (password.isEmpty()){
            binding.passWord.requestFocus();
            binding.passWord.setError("Required*");
            return;
        }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(login_activity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login_activity.this,nav_feed.class));
                                progressBar.setVisibility(View.INVISIBLE);
                                finish();
                            }else{
                                Toast.makeText(login_activity.this,
                                        "please provide valid account", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });


    }
}