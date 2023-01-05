package com.hall_event_reservation_system.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.models.post_models;

public class post_models_adapter extends FirestoreRecyclerAdapter<post_models, post_models_adapter.holder> {

    public post_models_adapter(@NonNull FirestoreRecyclerOptions<post_models> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull post_models_adapter.holder holder,
                                    int position, @NonNull post_models model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public post_models_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);


        return new post_models_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        Context context;
        ImageView image_view;
        TextView  calendar,discriptions;

        public holder(@NonNull  View itemView) {
            super(itemView);

            context =itemView.getContext();
            calendar = itemView.findViewById(R.id.date_time);
            discriptions = itemView.findViewById(R.id.post_text);
            image_view = itemView.findViewById(R.id.image_view_pic);
        }

        public void bind(post_models model) {
            Glide.with(context.getApplicationContext())
                    .load(model.getImage_uri())
                    .into(image_view);


            calendar.setText(model.getDate_time());
            discriptions.setText(model.getPost_descriptions());

        }
    }
}
