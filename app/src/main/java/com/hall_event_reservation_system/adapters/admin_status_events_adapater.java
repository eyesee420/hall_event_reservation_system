package com.hall_event_reservation_system.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.models.users_add_events_model;

public class admin_status_events_adapater extends FirestoreRecyclerAdapter<users_add_events_model, admin_status_events_adapater.holder>{


    public admin_status_events_adapater(@NonNull FirestoreRecyclerOptions<users_add_events_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull admin_status_events_adapater.holder holder, int i, @NonNull users_add_events_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public admin_status_events_adapater.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events_admin, parent, false);



        return new admin_status_events_adapater.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Context context;
        TextView Date,sports,time,status,fullname,phone_number,home_address;
        Button accept_btn , delete_btn;


        public holder(@NonNull View itemView) {
            super(itemView);

            context =itemView.getContext();
            Date = itemView.findViewById(R.id.Date);
            sports = itemView.findViewById(R.id.sports);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            fullname = itemView.findViewById(R.id.fullname);
//            accept_btn = itemView.findViewById(R.id.accept_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }

        public void bind(users_add_events_model model) {

            Date.setText(model.getDate());
            sports.setText("Game " +model.getSports());
            time.setText(model.getTime_from() +" - "+ model.getTime_to());
            status.setText("Status " +model.getStatus());
            fullname.setText("Scheduled By : "+model.getFullname());

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("confirmed events").document(model.getDoc_id())
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }


    }
}
