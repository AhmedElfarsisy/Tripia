package com.iti.mad41.tripia.ui.fragment.more;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.MoreFragmentBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.facebook.FacebookRepo;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleRepo;
import com.iti.mad41.tripia.ui.activity.auth.AuthActivity;
import com.iti.mad41.tripia.ui.fragment.profile.ProfileFragment;
import com.iti.mad41.tripia.ui.fragment.register.RegisterFragment;

public class MoreFragment extends Fragment {

    private MoreViewModel mViewModel;
    private MoreFragmentBinding binding;

    private FirebaseRepo firebaseRepo;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.more_fragment, container, false);
        firebaseRepo = new FirebaseRepo(getActivity());
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new MoreViewModelFactory(firebaseRepo)).get(MoreViewModel.class);
        binding.setMViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        mViewModel.isNavigateToProfile.observe(getViewLifecycleOwner(), isNavigate -> {
            if (isNavigate)
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment_container_view,
                        new ProfileFragment(), Constants.PROFILE_FRAGMENT).addToBackStack(Constants.PROFILE_FRAGMENT).commit();
        });

        mViewModel.isSignedOut.observe(getViewLifecycleOwner(), isSignedOut -> {
            if (isSignedOut) {
                getActivity().startActivity(new Intent(getActivity(), AuthActivity.class));
                getActivity().finish();
            }

        });
    }

}