package com.iti.mad41.tripia.ui.activity.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.ui.fragment.more.MoreFragment;
import com.iti.mad41.tripia.ui.fragment.notes.NotesFragment;
import com.iti.mad41.tripia.ui.fragment.profile.ProfileFragment;
import com.iti.mad41.tripia.ui.fragment.signin.SigninFragment;

public class SettingsActivity extends AppCompatActivity {
    MoreFragment moreFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            moreFragment = new MoreFragment();
            fragmentTransaction = fragmentManager.beginTransaction()
                    .add(R.id.settings_fragment_container_view, moreFragment, Constants.MORE_FRAGMENT);
            fragmentTransaction.commit();
        } else {
            moreFragment = (MoreFragment) fragmentManager.findFragmentByTag(Constants.MORE_FRAGMENT);
        }

    }
}