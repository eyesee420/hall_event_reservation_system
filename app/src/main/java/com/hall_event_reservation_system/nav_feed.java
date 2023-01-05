package com.hall_event_reservation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.hall_event_reservation_system.adapters.viewpager_adapter;
import com.hall_event_reservation_system.databinding.ActivityNavFeedBinding;


public class nav_feed extends AppCompatActivity {

    viewpager_adapter viewpagerAdapter;
    ActivityNavFeedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_nav_feed);


        binding = ActivityNavFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewpagerAdapter = new viewpager_adapter(this);

        binding.viewpager.setAdapter(viewpagerAdapter);



//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tablayout, binding.viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//
//                switch (position){
//                    case 0:
//                        tab.setText("Featured");
//                        BadgeDrawable Featured = tab.getOrCreateBadge();
//                        Featured.setBackgroundColor(ContextCompat.getColor
//                                (getApplicationContext(),R.color.maroon));
//                        Featured.setVisible(true);
//                        Featured.setNumber(10);
//                        break;
//                    case 1:
//                        tab.setText("Latest");
//                        BadgeDrawable Latest = tab.getOrCreateBadge();
//                        Latest.setBackgroundColor(ContextCompat.getColor
//                                (getApplicationContext(),R.color.maroon));
//                        Latest.setVisible(true);
//                        Latest.setNumber(15);
//                        Latest.setMaxCharacterCount(3);
//                        break;
//                    case 2:
//                        tab.setText("Trending");
//                        BadgeDrawable Trending = tab.getOrCreateBadge();
//                        Trending.setBackgroundColor(ContextCompat.getColor
//                                (getApplicationContext(),R.color.maroon));
//                        Trending.setVisible(true);
//
//
//                        break;
//                    default:
//
//                        break;
//                }
//            }
//        });
//        tabLayoutMediator.attach();


        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {



            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tablayout.getTabAt(position).select();
            }
        });



        binding.bottomNavigationUsers.setSelectedItemId(R.id.nav_feed);
        binding.bottomNavigationUsers.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        return true;

                    case R.id.nav_set_Schedule:
                        startActivity(new Intent(getApplicationContext(), nav_set_Schedule.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_status:
                        startActivity(new Intent(getApplicationContext(), nav_status.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), nav_profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}