package com.iti.mad41.tripia.ui.fragment.form;

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
import com.iti.mad41.tripia.databinding.FormFragmentBinding;
import com.iti.mad41.tripia.helper.Validations;
import com.iti.mad41.tripia.ui.fragment.notes.NotesFragment;

public class FormFragment extends Fragment {
    private FormViewModel mViewModel;
    FormFragmentBinding binding ;
    public static FormFragment newInstance() {
        return new FormFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.form_fragment, container, false);
        return binding.getRoot();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormViewModel.class);
        binding.setFormViewModel(mViewModel);
        mViewModel.setContext(getActivity());
        binding.setLifecycleOwner(this);
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(),isNavigate -> {
            if(isNavigate) {
//                if(Validations.isEmpty())
                        getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view,new NotesFragment())
                        .commit();
            }
        });



    }
}