package com.iti.mad41.tripia.ui.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityMainBinding;


import com.iti.mad41.tripia.ui.activity.form.FormActivity;
import com.iti.mad41.tripia.ui.fragment.form.FormFragment;
import com.iti.mad41.tripia.ui.fragment.notes.NotesFragment;


import com.iti.mad41.tripia.ui.fragment.main.previousTrips.PreviousTripsFragment;
import com.iti.mad41.tripia.adapters.TripTabsPagerAdapter;
import com.iti.mad41.tripia.ui.fragment.main.upcomingTrips.UpcomingTripsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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

/*
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container_view, new FormFragment()).commit();
*/
        tripViewPager = binding.tripViewPager;
        tripTabLayout = binding.tripTabLayout;
        mainViewModel.isNavigateToForm.observe(this,navigateResult -> {
            if (navigateResult)
            {
                Log.i(TAG, "onCreate: ********"+navigateResult);
              //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,new FormFragment()).commit();

                startActivity(new Intent(MainActivity.this, FormActivity.class));
            }
        });
        setAdapters();
    }

    private void setAdapters() {
        TripTabsPagerAdapter adapter = new TripTabsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpcomingTripsFragment(), "Upcoming");
        adapter.addFragment(new PreviousTripsFragment(), "Previous");
        tripViewPager.setAdapter(adapter);
        tripTabLayout.setupWithViewPager(tripViewPager);
    }
}