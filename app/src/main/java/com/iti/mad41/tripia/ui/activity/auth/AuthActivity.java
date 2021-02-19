package com.iti.mad41.tripia.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityAuthBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleRepo;
import com.iti.mad41.tripia.ui.activity.main.MainActivity;
import com.iti.mad41.tripia.ui.fragment.signin.SiginViewModelFactory;
import com.iti.mad41.tripia.ui.fragment.signin.SigninFragment;
import com.iti.mad41.tripia.ui.fragment.signin.SigninViewModel;

public class AuthActivity extends AppCompatActivity {
    private static final String TAG = "AuthActivity";
    ActivityAuthBinding binding;
    AuthViewModel authViewModel;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SigninFragment signinFragment;
    private FirebaseRepo firebaseRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseRepo = new FirebaseRepo(this);
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

        authViewModel = new ViewModelProvider(this, new AuthFactory(firebaseRepo)).get(AuthViewModel.class);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.setAuthViewModel(authViewModel);
        binding.setLifecycleOwner(this);

        authViewModel.isSignedinSuccessed.observe(this, isSignedin -> {
            if (isSignedin) {
                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleRepo.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            authViewModel.handleSignInResult(task);
        }
    }
}