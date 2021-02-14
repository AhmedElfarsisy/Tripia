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

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.SigninFragmentBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.ui.activity.main.MainActivity;
import com.iti.mad41.tripia.ui.fragment.register.RegisterFragment;

public class SigninFragment extends Fragment {

    private SigninViewModel mViewModel;
    SigninFragmentBinding binding;
    String email;
    String password;

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
        FirebaseRepo firebaseRepo = new FirebaseRepo();
        mViewModel = new ViewModelProvider(this, new SiginViewModelFactory(firebaseRepo)).get(SigninViewModel.class);
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
            if (isNavigate) {
                navigateToSignUp();
            }
        });

        mViewModel.isSignedinSuccessed.observe(getViewLifecycleOwner(), isSignedin -> {
            if (isSignedin) {
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        mViewModel.isSignedinFailure.observe(getViewLifecycleOwner(), message -> {
            Log.i("myapp", "onActivityCreated: ====" + message);
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        });
    }


    private void navigateToSignUp() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.auth_fragment_container_view,
                new RegisterFragment(), Constants.SIGNUP_FRAGMENT).addToBackStack(Constants.SIGNIN_FRAGMENT).commit();
        mViewModel.setNavigateToSignupComplete();
    }


    private void showError() {
        binding.passwordInputLayout.setError("something, went wrong");
        binding.passwordInputLayout.setErrorIconDrawable(R.drawable.ic_outline_error_outline_24);
        binding.emailInputLayout.setError("please, input correct Email");
        binding.emailInputLayout.setErrorIconDrawable(R.drawable.ic_outline_error_outline_24);
    }


}

