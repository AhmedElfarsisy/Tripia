package com.iti.mad41.tripia.ui.fragment.signin;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.SigninFragmentBinding;

public class SigninFragment extends Fragment {

    private SigninViewModel mViewModel;
    SigninFragmentBinding binding;

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
        mViewModel = new ViewModelProvider(this).get(SigninViewModel.class);
        binding.setSigninViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        // TODO: Use the ViewModel
    }

}