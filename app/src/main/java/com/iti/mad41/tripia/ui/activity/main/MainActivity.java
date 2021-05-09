package com.iti.mad41.tripia.ui.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityMainBinding;


import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.activity.form.FormActivity;
import com.iti.mad41.tripia.ui.activity.settings.SettingsActivity;


import com.iti.mad41.tripia.ui.fragment.main.previousTrips.PreviousTripsFragment;
import com.iti.mad41.tripia.adapters.TripTabsPagerAdapter;
import com.iti.mad41.tripia.ui.fragment.main.upcomingTrips.UpcomingTripsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    MainViewModel mViewModel;

    ViewPager tripViewPager;
    TabLayout tripTabLayout;

    TripsDataRepository tripsDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tripsDataRepository = TripsDataRepository.getINSTANCE(this);
        mViewModel = new ViewModelProvider(this, new MainViewModelFactory(tripsDataRepository)).get(MainViewModel.class);
        binding.setMainViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        //Check if the application has draw over other apps permission or not?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, Constants.CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            tripViewPager = binding.tripViewPager;
            tripTabLayout = binding.tripTabLayout;

            mViewModel.isNavigateToForm.observe(this, navigateResult -> {
                if (navigateResult) {
                    Log.i(TAG, "onCreate: ********" + navigateResult);
                    startActivity(new Intent(MainActivity.this, FormActivity.class));
                }
            });
            setAdapters();
            setBottomActionBarListener();
        }

    }

    private void setBottomActionBarListener() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    return true;

                case R.id.menuMore:
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    return true;

            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void setAdapters() {
        TripTabsPagerAdapter adapter = new TripTabsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpcomingTripsFragment(), "Upcoming");
        adapter.addFragment(new PreviousTripsFragment(), "Previous");
        tripViewPager.setAdapter(adapter);
        tripTabLayout.setupWithViewPager(tripViewPager);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}