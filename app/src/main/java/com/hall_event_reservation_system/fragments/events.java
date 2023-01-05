package com.hall_event_reservation_system.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.adapters.post_models_adapter;
import com.hall_event_reservation_system.adapters.user_status_events_adapater;
import com.hall_event_reservation_system.databinding.FragmentAllBinding;
import com.hall_event_reservation_system.databinding.FragmentEventsBinding;
import com.hall_event_reservation_system.models.post_models;
import com.hall_event_reservation_system.models.users_add_events_model;

public class events extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FragmentEventsBinding binding;
    user_status_events_adapater adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEventsBinding.inflate(inflater,container,false);
        return binding.getRoot();
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview();
    }

    private void recyclerview() {
        Query query = db.collection("confirmed events");

        FirestoreRecyclerOptions<users_add_events_model> options = new FirestoreRecyclerOptions.Builder<users_add_events_model>()
                .setQuery(query, users_add_events_model.class).build();



        adapter = new user_status_events_adapater(options);
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setHasFixedSize(true);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}