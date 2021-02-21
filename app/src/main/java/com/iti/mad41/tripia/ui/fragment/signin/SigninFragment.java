package com.iti.mad41.tripia.ui.fragment.signin;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.SigninFragmentBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.facebook.FacebookRepo;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleRepo;
import com.iti.mad41.tripia.ui.activity.main.MainActivity;
import com.iti.mad41.tripia.ui.fragment.register.RegisterFragment;

public class SigninFragment extends Fragment {
    private static final String TAG = "SigninFragment";
    private SigninViewModel mViewModel;
    SigninFragmentBinding binding;
    String email;
    String password;
    private CallbackManager callbackManager;
    private FirebaseRepo firebaseRepo;
    private FacebookRepo facebookRepo;
    private GoogleRepo googleRepo;

    public static SigninFragment newInstance() {
        return new SigninFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.signin_fragment, container, false);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRepo = new FirebaseRepo(getActivity());
        callbackManager = CallbackManager.Factory.create();
        facebookRepo = new FacebookRepo(this, callbackManager);
        googleRepo = new GoogleRepo(getActivity());
        mViewModel = new ViewModelProvider(this, new SiginViewModelFactory(firebaseRepo, facebookRepo, googleRepo)).get(SigninViewModel.class);
        binding.setSigninViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        mViewModel.doSignin.observe(getViewLifecycleOwner(), readyToSignin -> {
            if (readyToSignin) {
                password = binding.inputTextPassword.getText().toString();
                email = binding.inpuTextEmail.getText().toString();
                mViewModel.signinUser(email, password);
            }
        });

        mViewModel.navigateToSignup.observe(getViewLifecycleOwner(), isNavigate -> {
            if (isNavigate) navigateToSignUp();
        });

        mViewModel.isEmailNotValid.observe(getViewLifecycleOwner(), isNotValid -> {
            if(isNotValid) showEmailError("Email syntax is incorrect");
        });

        mViewModel.isPasswordNotValid.observe(getViewLifecycleOwner(), isNotValid -> {
            if(isNotValid) showPasswordError("Password syntax is incorrect");
        });

        mViewModel.isSignedinSuccessed.observe(getViewLifecycleOwner(), isSignedin -> {
            if (isSignedin) getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        });

        mViewModel.isSignedinFailure.observe(getViewLifecycleOwner(), message -> {
            if(message == null) showToast(message);
        });

        mViewModel.isSignedinCanceled.observe(getViewLifecycleOwner(), isCanceled -> {
            if(isCanceled) showToast("Sign in Canceled!");
        });

        mViewModel.isSocialSuccessed.observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Log.i(TAG, user.toString());
                mViewModel.writeNewUser(user);
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        mViewModel.isSocialFailure.observe(getViewLifecycleOwner(), message -> {
            if(message !=  null) showToast(message);
        });

        mViewModel.isSocialCanceled.observe(getViewLifecycleOwner(), isCanceled -> {
            if(isCanceled) showToast("Sign in Canceled!");
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleRepo.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            mViewModel.handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void navigateToSignUp() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.auth_fragment_container_view,
                new RegisterFragment(), Constants.SIGNUP_FRAGMENT).addToBackStack(Constants.SIGNIN_FRAGMENT).commit();
        mViewModel.setNavigateToSignupComplete();
    }

    private void showEmailError(String msgText){
        binding.emailInputLayout.setError(msgText);
        binding.emailInputLayout.setErrorIconDrawable(R.drawable.ic_outline_error_outline_24);
    }

    private void showPasswordError(String message){
        binding.passwordInputLayout.setError(message);
        binding.passwordInputLayout.setErrorIconDrawable(R.drawable.ic_outline_error_outline_24);
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}

