package com.iti.mad41.tripia.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding binding;
    AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.setAuthViewModel(authViewModel);
        binding.setLifecycleOwner(this);


    }
}