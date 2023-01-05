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

import java.util.HashMap;
import java.util.Map;

public class admin_status_events__request_adapater extends FirestoreRecyclerAdapter<users_add_events_model, admin_status_events__request_adapater.holder>{


    public admin_status_events__request_adapater(@NonNull FirestoreRecyclerOptions<users_add_events_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull admin_status_events__request_adapater.holder holder, int i, @NonNull users_add_events_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public admin_status_events__request_adapater.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events_request, parent, false);



        return new admin_status_events__request_adapater.holder(view);
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
            phone_number = itemView.findViewById(R.id.phone_number);
            home_address = itemView.findViewById(R.id.home_address);
            accept_btn = itemView.findViewById(R.id.accept_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }

        public void bind(users_add_events_model model) {

            Date.setText(model.getDate());
            sports.setText("Game " +model.getSports());
            time.setText(model.getTime_from() +" - "+ model.getTime_to());
            status.setText("Status " +model.getStatus());
            fullname.setText("Scheduled By : "+model.getFullname());
            phone_number.setText("Phone Number : " +model.getPhonenumber());
            home_address.setText("Home Address : "+model.getHomeaddress());

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("my events").document(model.getDoc_id())
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
            accept_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, Object> stats = new HashMap<>();
                    stats.put("status", "Confirmed");
                    db.collection("my events")
                            .document(model.getDoc_id()).update(stats).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    model.setStatus("Confirmed");
                                    String doc_id = db.collection("confirmed").document().getId();

                                    model.setDoc_id(doc_id);
                                    users_add_events_model models = new users_add_events_model(model.getDoc_id(),model.getSports()
                                            ,model.getFullname(),model.getPhonenumber(),model.getHomeaddress(),model.getTime_from(),model.getTime_to(),
                                            model.getDate(),model.getDatetime(),model.getStatus());
                                    db.collection("confirmed events").document(doc_id).set(models)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(context, "Confirmed", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });


                }
            });

        }


    }
}
