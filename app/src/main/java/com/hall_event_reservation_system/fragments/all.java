package com.hall_event_reservation_system.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.adapters.post_models_adapter;
import com.hall_event_reservation_system.databinding.FragmentAllBinding;
import com.hall_event_reservation_system.models.post_models;


public class all extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FragmentAllBinding binding;
    post_models_adapter adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAllBinding.inflate(inflater,container,false);
        return binding.getRoot();

        // Inflate the layout for this fragment
     //  return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview();
        binding.searchBar.setVisibility(View.GONE);

    }


    private void recyclerview() {

        Query query = db.collection("post feed");

        FirestoreRecyclerOptions<post_models> options = new FirestoreRecyclerOptions.Builder<post_models>()
                .setQuery(query, post_models.class).build();



        adapter = new post_models_adapter(options);
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