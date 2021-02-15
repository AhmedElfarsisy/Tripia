package com.iti.mad41.tripia.ui.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityMainBinding;
import com.iti.mad41.tripia.ui.fragment.main.previousTrips.PreviousTripsFragment;
import com.iti.mad41.tripia.adapters.TripTabsPagerAdapter;
import com.iti.mad41.tripia.ui.fragment.main.upcomingTrips.UpcomingTripsFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;
    ViewPager tripViewPager;
    TabLayout tripTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        tripViewPager = binding.tripViewPager;
        tripTabLayout = binding.tripTabLayout;
        setAdapters();
    }

    private void setAdapters(){
        TripTabsPagerAdapter adapter = new TripTabsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpcomingTripsFragment(), "Upcoming");
        adapter.addFragment(new PreviousTripsFragment(), "Previous");
        tripViewPager.setAdapter(adapter);
        tripTabLayout.setupWithViewPager(tripViewPager);
    }
}