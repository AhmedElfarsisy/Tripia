package com.iti.mad41.tripia.ui.activity.form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ActivityFormBinding;
import com.iti.mad41.tripia.model.Note;
import com.iti.mad41.tripia.model.Notes;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.ui.activity.main.MainViewModel;
import com.iti.mad41.tripia.ui.fragment.form.FormFragment;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    ActivityFormBinding binding ;
    FormActivityViewModel formActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if(getIntent()!=null)
        {

        }*/

        //Damey Data to validate update Trip
        Trip trip = new Trip(0, "HistTitle1", "31.00000", "131.1245", 1614092052590L, "124.12345");
        List<Note> noteList  = new ArrayList<>();
        noteList.add(new Note(0,"book"));
        noteList.add(new Note(1,"mobile"));
        Notes notes = new Notes(noteList);
        FormFragment formFragment = FormFragment.newInstance(trip,notes);




        binding= DataBindingUtil.setContentView(this,R.layout.activity_form);
        formActivityViewModel = new ViewModelProvider(this).get(FormActivityViewModel.class);
        binding.setFormViewModel(formActivityViewModel);
        binding.setLifecycleOwner(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,formFragment).commit();
    }
}