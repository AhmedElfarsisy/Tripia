package com.iti.mad41.tripia.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityAuthBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.ui.fragment.signin.SigninFragment;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding binding;
    AuthViewModel authViewModel;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SigninFragment signinFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            signinFragment = new SigninFragment();
            fragmentTransaction = fragmentManager.beginTransaction()
                    .add(R.id.auth_fragment_container_view, signinFragment, Constants.SIGNIN_FRAGMENT);
            fragmentTransaction.commit();
        } else {
            signinFragment = (SigninFragment) fragmentManager.findFragmentByTag(Constants.SIGNIN_FRAGMENT);
        }

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.setAuthViewModel(authViewModel);
        binding.setLifecycleOwner(this);


    }
}