package com.hall_event_reservation_system.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.models.create_users_model;

public class user_adapter extends FirestoreRecyclerAdapter<create_users_model, user_adapter.holder> {

    public user_adapter(@NonNull FirestoreRecyclerOptions<create_users_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull user_adapter.holder holder, int i, @NonNull create_users_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public user_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_row,parent,false);
        return  new user_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView full_name,home_address, phone_number,email_address;

        public holder(@NonNull View itemView) {
            super(itemView);

            full_name = itemView.findViewById(R.id.full_name);
            home_address = itemView.findViewById(R.id.home_address);
            phone_number = itemView.findViewById(R.id.phone_number);
            email_address = itemView.findViewById(R.id.email_address);


        }

        public void bind(create_users_model model) {

            full_name.setText(model.getFullname());
            home_address.setText(model.getHomeaddress());
            phone_number.setText(model.getPhonenumber());
            email_address.setText(model.getEmailaddress());
        }
    }
}
