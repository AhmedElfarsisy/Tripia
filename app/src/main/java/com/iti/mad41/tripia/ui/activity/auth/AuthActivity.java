package com.iti.mad41.tripia.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityAuthBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.ui.fragment.signin.SigninFragment;

public class AuthActivity extends AppCompatActivity {
    private static final String TAG = "AuthActivity";
    ActivityAuthBinding binding;
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentManager.findFragmentByTag(Constants.SIGNIN_FRAGMENT).onActivityResult(requestCode, resultCode, data);
        if(fragmentManager.findFragmentByTag(Constants.SIGNUP_FRAGMENT) != null)
                fragmentManager.findFragmentByTag(Constants.SIGNUP_FRAGMENT).onActivityResult(requestCode, resultCode, data);
    }
}