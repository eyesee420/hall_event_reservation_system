package com.hall_event_reservation_system.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hall_event_reservation_system.fragments.all;
import com.hall_event_reservation_system.fragments.events;
import com.hall_event_reservation_system.fragments.news;
import com.hall_event_reservation_system.fragments.today;

public class viewpager_adapter extends FragmentStateAdapter {


    public viewpager_adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new all();
            case 1:
                return new events();
            case 2:
                return new today();
            case 3:
                return new news();
            default:
                return new all();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
