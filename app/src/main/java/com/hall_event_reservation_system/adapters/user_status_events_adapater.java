package com.hall_event_reservation_system.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.models.users_add_events_model;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class user_status_events_adapater  extends FirestoreRecyclerAdapter<users_add_events_model, user_status_events_adapater.holder>{


    public user_status_events_adapater(@NonNull FirestoreRecyclerOptions<users_add_events_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull user_status_events_adapater.holder holder, int i, @NonNull users_add_events_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public user_status_events_adapater.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events, parent, false);



        return new user_status_events_adapater.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {

        Context context;
        TextView Date,sports,time,status,fullname;


        public holder(@NonNull View itemView) {
            super(itemView);

            context =itemView.getContext();
            Date = itemView.findViewById(R.id.Date);
            sports = itemView.findViewById(R.id.sports);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            fullname = itemView.findViewById(R.id.fullname);
        }

        public void bind(users_add_events_model model) {

            Date.setText(model.getDate());
            sports.setText("Game " +model.getSports());
            time.setText(model.getTime_from() +" - "+ model.getTime_to());
            status.setText("Status " +model.getStatus());
            fullname.setText("Scheduled By : "+model.getFullname());
        }
    }
}
