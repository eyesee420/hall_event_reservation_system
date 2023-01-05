package com.hall_event_reservation_system.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.models.post_models;

public class admin_post_models_adapter extends FirestoreRecyclerAdapter<post_models, admin_post_models_adapter.holder> {

    public admin_post_models_adapter(@NonNull FirestoreRecyclerOptions<post_models> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull admin_post_models_adapter.holder holder,
                                    int position, @NonNull post_models model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public admin_post_models_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed_admin, parent, false);


        return new admin_post_models_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Context context;
        ImageView image_view;
        TextView  calendar,discriptions;
        Button delete_btn;

        public holder(@NonNull  View itemView) {
            super(itemView);

            context =itemView.getContext();
            calendar = itemView.findViewById(R.id.date_time);
            discriptions = itemView.findViewById(R.id.post_text);
            image_view = itemView.findViewById(R.id.image_view_pic);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }

        public void bind(post_models model) {
            Glide.with(context.getApplicationContext())
                    .load(model.getImage_uri())
                    .into(image_view);


            calendar.setText(model.getDate_time());
            discriptions.setText(model.getPost_descriptions());
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("post feed").document(model.getDoc_id()).delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "post deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }
}
