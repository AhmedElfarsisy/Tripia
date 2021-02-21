package com.iti.mad41.tripia.ui.activity.form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityFormBinding;
import com.iti.mad41.tripia.ui.activity.main.MainViewModel;
import com.iti.mad41.tripia.ui.fragment.form.FormFragment;

public class FormActivity extends AppCompatActivity {

    ActivityFormBinding binding ;
    FormActivityViewModel formActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_form);
        formActivityViewModel = new ViewModelProvider(this).get(FormActivityViewModel.class);
        binding.setFormViewModel(formActivityViewModel);
        binding.setLifecycleOwner(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,new FormFragment()).commit();
    }
}