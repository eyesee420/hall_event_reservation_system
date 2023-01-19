package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hall_event_reservation_system.databinding.ActivityAdminLoginBinding;
import com.hall_event_reservation_system.databinding.ActivityLoginBinding;

public class admin_login extends AppCompatActivity {

    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ProgressBar progressBar;
    ActivityAdminLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
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
        binding.forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_login.this , forgot_password_page.class));
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

        mAuth.signInWithEmailAndPassword(binding.emailAddress.toString().trim(),
                binding.passWord.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                        
                                    Toast.makeText(admin_login.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(admin_login.this,admin_feed.class));
                                    progressBar.setVisibility(View.INVISIBLE);
                                    finish();
                                }else
                                    {
                                        Toast.makeText(admin_login.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(admin_login.this , login_activity.class));
                                        progressBar.setVisibility(View.INVISIBLE);
                                    finish();
                                    }
                            }
                        });


    }
}