package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hall_event_reservation_system.databinding.ActivityAdminPostFeedBinding;
import com.hall_event_reservation_system.models.post_models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class admin_post_feed extends AppCompatActivity {
    ActivityAdminPostFeedBinding binding;
    ImagePicker imagePicker;
    Uri uri;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference reference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_admin_post_feed);

        binding = ActivityAdminPostFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePicker.Companion.with(admin_post_feed.this)
                        .start();
            }
        });

        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uri != null) {
                binding.progressCircular.setVisibility(View.VISIBLE);
                    uploadtofirebase(uri);

                } else {
                    Toast.makeText(admin_post_feed.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadtofirebase(Uri uri) {

        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd/LLL/yyyy");
                        String date = currentDate.format(calendar.getTime());
                        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
                        String time = currentTime.format(calendar.getTime());
                        String mytime = date + " " + time;


                        String my_discription=  binding.discriptions.getText().toString().trim();

                        String doc_id = db.collection("post").document().getId();

                        post_models postModel = new post_models
                                (doc_id,uri.toString(),my_discription,mytime);



                        //  FirebaseUser userid = mAuth.getCurrentUser();
                        // String idd = db.collection("shop and products").document(userid.getUid()).collection("my shops").getId();
                        db.collection("post feed").document(doc_id)
                                .set(postModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });


                       binding.progressCircular.setVisibility(View.INVISIBLE);
                        Toast.makeText(admin_post_feed.this, "uploaded", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    private String getFileExtension(Uri muri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        binding.imageView.setImageURI(uri);


    }
}