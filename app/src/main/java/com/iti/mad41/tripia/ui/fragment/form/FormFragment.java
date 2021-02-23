package com.iti.mad41.tripia.ui.fragment.form;

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
import android.widget.Toast;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.FormFragmentBinding;
import com.iti.mad41.tripia.helper.Validations;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.ui.activity.main.MainActivity;
import com.iti.mad41.tripia.ui.fragment.notes.NotesFragment;

public class FormFragment extends Fragment {

    private FormViewModel mViewModel;
    FormFragmentBinding binding;
    boolean isFormComplete = false;

    String startAddress ="s";
    String destinationAddress ;
    String startDate ;
    String startTime ;
    Trip trip ;
    private String title;
    long timeStampValue;

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
        binding.toolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
        });

        binding.textViewEndPoint.setOnClickListener(v -> binding.textViewEndPoint.setText("sss"));
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(), isNavigate -> {
            if (isNavigate) {

                title =binding.editTextTripTitle.getText().toString();
                isFormComplete =Validations.isFormComplete(startTime,startTime,new String("s"),new String("s"));
                if (!Validations.isEmpty(title)&&isFormComplete) {
                    trip=new Trip(22,title,"giza","haram", timeStampValue,"https://images.unsplash.com/photo-1563857256304-d943ffb54ca8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1234&q=80");
                    NotesFragment notesFragment = NotesFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Trip",trip);
                    notesFragment.setArguments(bundle);

                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view, notesFragment)
                            .commit();
                } else {
                    if(Validations.isEmpty(binding.editTextTripTitle.getText().toString()))
                        binding.editTextTripTitle.setError("Title field is empty");
                    else
                        Toast.makeText(getActivity(), "you should fill all form", Toast.LENGTH_SHORT).show();
                }
            }

        });

        mViewModel.startDate.observe(getViewLifecycleOwner(),s -> {
            if (!Validations.isEmpty(s))
                startDate= s;
        });

        mViewModel.startTime.observe(getViewLifecycleOwner(),s -> {
            if (!Validations.isEmpty(s))
                startTime= s;
        });

        mViewModel.timeStamp.observe(getViewLifecycleOwner(),aLong -> {

            timeStampValue = aLong;
        });


    }





}