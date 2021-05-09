package com.iti.mad41.tripia.ui.activity.form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.databinding.ActivityFormBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.activity.main.MainViewModel;
import com.iti.mad41.tripia.ui.fragment.form.FormFragment;
import com.iti.mad41.tripia.ui.fragment.form.FormViewModelFactory;

public class FormActivity extends AppCompatActivity {

    ActivityFormBinding binding;
    FormActivityViewModel mViewModel;
    TripsDataRepository tripsDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form);
        tripsDataRepository = TripsDataRepository.getINSTANCE(this);
        mViewModel = new ViewModelProvider(this, new FormActivityViewModelFactory(tripsDataRepository)).get(FormActivityViewModel.class);
        binding.setFormViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        if(getIntent() != null){
            int tripId = getIntent().getIntExtra(Constants.TRIP_ID_KEY, 5);
            Log.i("TRIP_ID_FORM_ACTIVITY", "ID in Activity main: " + tripId);
            boolean isNavigateToUpdate = getIntent().getBooleanExtra(Constants.IS_NAVIGATE_TO_UPDATE, false);
            Log.i("IsNavigateToUpdate", "navigateToUpdate: " + isNavigateToUpdate);
            FormFragment formFragment = FormFragment.newInstance(tripId, isNavigateToUpdate);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, formFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, new FormFragment(), Constants.FORM_FRAGMENT).commit();
        }


    }
}